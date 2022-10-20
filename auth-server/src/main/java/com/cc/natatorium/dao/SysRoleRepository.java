package com.cc.natatorium.dao;

import com.cc.natatorium.model.po.UmsRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleRepository  extends JpaRepository<UmsRole,Long> {
}
