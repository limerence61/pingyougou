/**  
 * Project Name:pinyougou-shop-web
 * File Name: LoginController.java
 * Package Name:com.pinyougou.shop.controller
 * Date:2018年5月7日上午8:53:19 
 * Copyright (c) 2018, 1261473647@qq.com All Rights Reserved.  
 *  
 */
package com.pinyougou.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录首页
 * 
 * @author zwp
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    
    /**
     * 显示登录用户名
     * 
     * @return Map<String,String>
     */
    @RequestMapping("/name")
    public Map<String, String> name() {
        Map<String, String> map = new HashMap<>();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        map.put("loginName", name);
        return map;
    }
}
