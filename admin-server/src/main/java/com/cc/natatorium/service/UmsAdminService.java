package com.cc.natatorium.service;

import com.cc.natatorium.api.CommonResult;
import com.cc.natatorium.dto.UmsAdminParam;
import com.cc.natatorium.model.ResultMsg;
import com.cc.natatorium.model.UmsAdmin;
import com.cc.natatorium.model.UmsRole;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author caosen
 * @Date 2022/10/16 15:17
 */
public interface UmsAdminService {

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 调用认证中心返回结果
     */
    CommonResult login(String username, String password);

    ResultMsg login(@RequestBody UmsAdminParam umsAdminParam);
    /**
     * 获取当前登录后台用户
     */
    UmsAdmin getCurrentAdmin();

    /**
     * 获取缓存服务
     */
    UmsAdminCacheService getCacheService();

    /**
     * 获取用户对于角色
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

}
