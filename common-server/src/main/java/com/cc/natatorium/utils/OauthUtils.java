package com.cc.natatorium.utils;


import com.cc.natatorium.model.LoginVal;
import com.cc.natatorium.model.RequestConstant;


public class OauthUtils {

    /**
     * 获取当前请求登录用户的详细信息
     */
    public static LoginVal getCurrentUser(){
        return (LoginVal) RequestContextUtils.getRequest().getAttribute(RequestConstant.LOGIN_VAL_ATTRIBUTE);
    }
}
