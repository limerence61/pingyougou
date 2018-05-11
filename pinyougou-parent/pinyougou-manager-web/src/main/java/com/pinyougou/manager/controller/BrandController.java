/**  
 * Project Name:pinyougou-manager-web
 * File Name: BrandController.java
 * Package Name:com.pinyougou.manager.controller
 * Date:2018年5月4日上午9:52:10 
 * Copyright (c) 2018, 1261473647@qq.com All Rights Reserved.  
 *  
*/
package com.pinyougou.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;
import entity.Result;

/**
 * 品牌控制层
 * 
 * @author zwp
 *
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    
    @Reference
    private BrandService brandService;
    
    /**
     * 返回所有列表
     * 
     * @return List<TbBrand>
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }
    
    /**
     * 分页查询
     * 
     * @param pageNum 当前页
     * @param pageSize 每页记录数
     * @return PageResult<TbBrand>
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int size) {
        return brandService.findPage(page, size);
    }
    
    /**
     * 新增品牌
     * 
     * @param tbBrand
     * @return Result
     */
    @RequestMapping("/add")
    public Result add(@RequestBody() TbBrand tbBrand) {
        try {
            brandService.add(tbBrand);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
    }
    
    /**
     * 根据id查询品牌F
     * 
     * @param id
     * @return TbBrand
     */
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id) {
        return brandService.findOne(id);
    }
    
    /**
     * 更新品牌
     * 
     * @param tbBrand
     * @return Result
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand tbBrand) {
        try {
            brandService.update(tbBrand);
            return new Result(true, "编辑成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "编辑失败!");
        }
    }
    
    /**
     * 批量删除
     * 
     * @param ids
     * @return Result
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            brandService.delete(ids);
            return new Result(true, "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败!");
        }
    }
    
    /**
     * 查询+分页
     * 
     * @param tbBrand
     * @param page
     * @param size
     * @return PageResult<TbBrand>
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand tbBrand, int page, int size) {
        return brandService.findPage(tbBrand, page, size);
    }
    
    /**
     * 品牌下拉列表数据
     * 
     * @return List<Map>
     */
    @RequestMapping("/selectBrandList")
    public List<Map> selectBrandList() {
        return brandService.selectBrandList();
    }
}
