/**  
 * Project Name:pinyougou-sellergoods-service
 * File Name: BrandServiceImpl.java
 * Package Name:com.pinyougou.sellergoods.service.impl
 * Date:2018年5月4日上午9:52:10 
 * Copyright (c) 2018, 1261473647@qq.com All Rights Reserved.  
 *  
*/
package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;
import entity.Result;

/**
 * ClassName:BrandServiceImpl <br/>
 * Function: 品牌服务层实现类. <br/>
 * Date: 2018年5月3日 下午3:39:41 <br/>
 * 
 * @author zwp
 * @version
 */
@Service
public class BrandServiceImpl implements BrandService {
    
    @Autowired
    private TbBrandMapper tbBrandMapper;
    
    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }
    
    /**
     * 使用MyBatis分页插件:pagehelper
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> page = (Page<TbBrand>)tbBrandMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }
    
    /**
     * 添加品牌
     */
    @Override
    public void add(TbBrand tbBrand) {
        // TODO Auto-generated method stub
        tbBrandMapper.insert(tbBrand);
    }
    
    /**
     * 根据id查询品牌
     */
    @Override
    public TbBrand findOne(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 更新品牌
     */
    @Override
    public void update(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKey(tbBrand);
    }
    
    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tbBrandMapper.deleteByPrimaryKey(id);
        }
        
    }
    
    /**
     * 分页+查询
     * 
     * @see com.pinyougou.sellergoods.service.BrandService#findPage(com.pinyougou.pojo.TbBrand,
     *      int, int)
     */
    @Override
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
        // 分页插件
        PageHelper.startPage(pageNum, pageSize);
        // 条件查询
        TbBrandExample example = new TbBrandExample();
        Criteria criteria = example.createCriteria();
        if (brand != null) {
            if (brand.getName() != null && brand.getName().length() > 0) {
                criteria.andNameLike("%" + brand.getName() + "%");
            }
            if (brand.getFirstChar() != null && brand.getFirstChar().length() > 0) {
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }
        Page<TbBrand> page = (Page<TbBrand>)tbBrandMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }
    
    /**
     * 返回下拉列表
     * 
     * @see com.pinyougou.sellergoods.service.BrandService#selectBrandList()
     */
    @Override
    public List<Map> selectBrandList() {
        List<Map> brandList = tbBrandMapper.selectBrandList();
        return brandList;
    }
    
}
