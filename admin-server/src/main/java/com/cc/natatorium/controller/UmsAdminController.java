package com.cc.natatorium.controller;

import cn.hutool.core.collection.CollUtil;
import com.cc.natatorium.api.CommonResult;
import com.cc.natatorium.dto.UmsAdminLoginParam;
import com.cc.natatorium.model.ResultMsg;
import com.cc.natatorium.model.UmsAdmin;
import com.cc.natatorium.model.UmsRole;
import com.cc.natatorium.service.AuthService;
import com.cc.natatorium.service.UmsAdminService;
import com.cc.natatorium.service.UmsRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author caosen
 * @Date 2022/10/16 15:10
 */
@RestController
@RequestMapping("/mall-admin/admin")
public class UmsAdminController {

    @Resource
    private UmsAdminService adminService;

    @Resource
    private UmsRoleService roleService;


//    @Resource
//    private Ums

    @Resource
    private AuthService authService;

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        return adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
    }


    @PostMapping(value = "/login2")
    @ResponseBody
    public ResultMsg login2() {
        System.out.println("asdfas");
        return ResultMsg.builder().build();
    }
    @GetMapping(value = "/login3")
    @ResponseBody
    public ResultMsg login3() {
        System.out.println("asdfas");
        return ResultMsg.builder().build();
    }
    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo() {
        System.out.println("进入info");
        UmsAdmin umsAdmin = adminService.getCurrentAdmin();
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        System.out.println("data:" + data);
        return CommonResult.success(data);
    }

}
