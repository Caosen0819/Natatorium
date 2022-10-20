package com.cc.natatorium.dao;

import com.cc.natatorium.model.po.UmsPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPermissionRepository extends JpaRepository<UmsPermission,Long> {
}
