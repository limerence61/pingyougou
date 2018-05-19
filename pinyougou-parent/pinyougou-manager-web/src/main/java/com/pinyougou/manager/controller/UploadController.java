/**  
 * Project Name:pinyougou-shop-web
 * File Name: UploadController.java
 * Package Name:com.pinyougou.shop.controller
 * Date:2018年5月8日下午2:16:24 
 * Copyright (c) 2018, 1261473647@qq.com All Rights Reserved.  
 *  
 */
package com.pinyougou.manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import entity.Result;
import utils.FastDFSClient;

/**
 * 图片上传
 * 
 * @author zwp
 *
 */
@RestController
public class UploadController {
    
    @Value("${FILE_SERVER_URL}")
    private String file_server_url;
    
    /**
     * 上传图片
     * 
     * @param file
     * @return Result
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {
        try {
            FastDFSClient dfsClient = new FastDFSClient("classpath:config/fdfs_client.conf");
            String originalFilename = file.getOriginalFilename();
            // 扩展名不要"."
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String path = dfsClient.uploadFile(file.getBytes(), extName);
            String url = file_server_url + path;
            return new Result(true, url);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Result(false, "上传失败");
        }
    }
}
