package com.cc.natatorium.service.Impl;

import com.cc.natatorium.model.UmsAdmin;
import com.cc.natatorium.service.RedisService;
import com.cc.natatorium.service.UmsAdminCacheService;
import com.cc.natatorium.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author caosen
 * @Date 2022/10/18 10:28
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    @Autowired
    private UmsAdminService adminService;

    @Resource
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;

    @Override
    public void delAdmin(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + adminId;
        redisService.del(key);
    }

    /**
     * 得到管理
     *
     * @param adminId 管理员id
     * @return {@link UmsAdmin }
     * @Author caosen
     * @Date 2022/10/18 10:30
     */
    @Override
    public UmsAdmin getAdmin(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + adminId;
        return (UmsAdmin) redisService.get(key);
    }

    /**
     * 设置管理
     *
     * @param admin 管理
     * @Author caosen
     * @Date 2022/10/18 10:29
     */
    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getId();
        redisService.set(key, admin, REDIS_EXPIRE);
    }
}
