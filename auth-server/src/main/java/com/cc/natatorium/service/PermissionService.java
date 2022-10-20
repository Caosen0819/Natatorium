package com.cc.natatorium.service;

import com.cc.natatorium.model.vo.SysRolePermissionVO;

import java.util.List;

/**
 * @Author caosen
 * @data 2022/10/8--17:47
 */
public interface PermissionService {

    public List<SysRolePermissionVO> listRolePermission();
}
