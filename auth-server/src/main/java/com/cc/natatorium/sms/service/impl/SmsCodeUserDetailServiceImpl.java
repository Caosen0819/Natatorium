package com.cc.natatorium.sms.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.cc.natatorium.dao.SysRoleRepository;
import com.cc.natatorium.dao.SysUserRepository;
import com.cc.natatorium.dao.SysUserRoleRepository;
import com.cc.natatorium.model.SecurityUser;
import com.cc.natatorium.model.SysConstant;
import com.cc.natatorium.model.po.UmsAdmin;
import com.cc.natatorium.model.po.UmsAdminRoleRelation;
import com.cc.natatorium.sms.service.SmsCodeUserDetailService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author caosen
 * @data 2022/10/10--14:18
 */
@Service
public class SmsCodeUserDetailServiceImpl implements SmsCodeUserDetailService {

    @Resource
    private SysUserRepository sysUserRepository;

    @Resource
    private SysUserRoleRepository sysUserRoleRepository;

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        UmsAdmin user = sysUserRepository.findByMobileAndStatus(mobile, 1);
        System.out.println("成功通过mobile找到user，user信息为：" + user);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("没有这个mobile" + mobile + "的用户");
        }

        List<UmsAdminRoleRelation> sysUserRoles = sysUserRoleRepository.findByAdminId(user.getId());

        List<String> roles = new ArrayList<>();
        for (UmsAdminRoleRelation sysUserRole : sysUserRoles) {
            sysRoleRepository.findById(sysUserRole.getRoleId()).ifPresent(o -> roles.add(SysConstant.ROLE_PREFIX + o.getCode()));
        }
        return SecurityUser.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(AuthorityUtils.createAuthorityList(ArrayUtil.toArray(roles, String.class)))
                .build();

    }
}
