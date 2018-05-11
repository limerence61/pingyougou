/**  
 * Project Name:pinyougou-manager-web
 * File Name: LoginController.java
 * Package Name:com.pinyougou.manager.controller
 * Date:2018年5月5日下午4:10:48 
 * Copyright (c) 2018, 1261473647@qq.com All Rights Reserved.  
 *  
 */
package com.pinyougou.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录controller
 * 
 * @author zwp
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    
    /**
     * 返回登录名
     * 
     * @return Map
     */
    @RequestMapping("/name")
    public Map<String, Object> name() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> map = new HashMap<>();
        map.put("loginName", name);
        return map;
    }
}
