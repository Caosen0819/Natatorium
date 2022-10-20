package com.cc.natatorium.service.Impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.cc.natatorium.api.CommonResult;
import com.cc.natatorium.api.ResultCode;
import com.cc.natatorium.constant.AuthConstant;
import com.cc.natatorium.dao.UmsAdminRoleRelationDao;
import com.cc.natatorium.domain.UserDto2;
import com.cc.natatorium.dto.UmsAdminParam;
import com.cc.natatorium.exception.Asserts;
import com.cc.natatorium.mapper.UmsAdminLoginLogMapper;
import com.cc.natatorium.mapper.UmsAdminMapper;
import com.cc.natatorium.mapper.UmsAdminRoleRelationMapper;
import com.cc.natatorium.model.*;
import com.cc.natatorium.service.AuthService;
import com.cc.natatorium.service.UmsAdminCacheService;
import com.cc.natatorium.service.UmsAdminService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author caosen
 * @Date 2022/10/16 15:17
 */
@Service
public class AdminServiceImpl implements UmsAdminService {

    @Resource
    private UmsAdminMapper adminMapper;
    @Resource
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Resource
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Resource
    private UmsAdminLoginLogMapper loginLogMapper;
    @Resource
    private AuthService authService;
    @Resource
    private HttpServletRequest request;

    @Override
    public CommonResult login(String username, String password) {
        System.out.println("进入login");
        if(StrUtil.isEmpty(username)||StrUtil.isEmpty(password)){
            Asserts.fail("用户名或密码不能为空！");
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", "myjszl");
        params.put("client_secret","123");
        params.put("grant_type","password");
        params.put("username",username);
        params.put("password",password);
        CommonResult restResult = authService.getAccessToken(params);
        if(ResultCode.SUCCESS.getCode()==restResult.getCode()&&restResult.getData()!=null){
//            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        }
        System.out.println(restResult.getData());
        return restResult;
    }
    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if(admin==null) return;
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public ResultMsg login(UmsAdminParam umsAdminParam) {
        return ResultMsg.builder().msg("right").code(200).data(null).build();
    }

    @Override
    public UmsAdmin getCurrentAdmin() {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if(StrUtil.isEmpty(userStr)){
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        UserDto2 userDto = JSONUtil.toBean(userStr, UserDto2.class);
        UmsAdmin admin = getCacheService().getAdmin(userDto.getUser_id());
        if(admin!=null){
            return admin;
        }else{
            admin = adminMapper.selectByPrimaryKey(userDto.getUser_id());
            getCacheService().setAdmin(admin);
            return admin;
        }
    }
    @Override
    public UmsAdminCacheService getCacheService() {
        return SpringUtil.getBean(UmsAdminCacheService.class);
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }
}
