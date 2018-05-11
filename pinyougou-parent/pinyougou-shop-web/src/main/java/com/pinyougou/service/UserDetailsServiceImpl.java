/**  
 * Project Name:pinyougou-shop-web
 * File Name: UserDetailsServiceImpl.java
 * Package Name:com.pinyougou.service
 * Date:2018年5月6日下午7:27:24 
 * Copyright (c) 2018, 1261473647@qq.com All Rights Reserved.  
 *  
 */
package com.pinyougou.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;

/**
 * 认证类
 * 
 * @author zwp
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    
    public SellerService sellerService;
    
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        System.out.println("UserDetailsServiceImpl....");
        TbSeller tbSeller = sellerService.findOne(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        if (tbSeller != null && "1".equals(tbSeller.getStatus())) {
            return new User(username, tbSeller.getPassword(), authorities);
        } else {
            return null;
        }
    }
    
}
