package com.cc.natatorium.service;

import com.cc.natatorium.model.UmsAdmin;

/**
 * @Author caosen
 * @Date 2022/10/18 10:28
 */
public interface UmsAdminCacheService {
    /**
     * 删除后台用户缓存
     */
    void delAdmin(Long adminId);

    /**
     * 获取缓存后台用户信息
     */
    UmsAdmin getAdmin(Long adminId);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(UmsAdmin admin);
}
