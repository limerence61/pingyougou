/**  
 * Project Name:pinyougou-sellergoods-interface
 * File Name: BrandService.java
 * Package Name:com.pinyougou.sellergoods.service
 * Date:2018年5月4日上午9:52:10 
 * Copyright (c) 2018, 1261473647@qq.com All Rights Reserved.  
 *  
*/
package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbBrand;

import entity.PageResult;

/**
 * 品牌业务层接口
 * 
 * @author zwp
 *
 */
public interface BrandService {

	/**
	 * findAll:返回全部品牌列表. <br/>
	 * 
	 * @author zwp
	 * @return
	 */
	public List<TbBrand> findAll();

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 *            当前页
	 * @param pageSize
	 *            每页记录数
	 * @return PageResult<TbBrand>
	 */
	public PageResult findPage(int pageNum, int pageSize);

	/**
	 * 新增品牌
	 * 
	 * @param tbBrand
	 *            品牌实体
	 * @return Result 返回结果
	 */
	public void add(TbBrand tbBrand);

	/**
	 * 根据id查询品牌
	 * 
	 * @param id
	 * @return TbBrand
	 */
	public TbBrand findOne(Long id);

	/**
	 * 跟新品牌
	 * 
	 * @param tbBrand
	 * @return void
	 */
	public void update(TbBrand tbBrand);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 *            void
	 */
	public void delete(Long[] ids);

	/**
	 * 查询+分页
	 * 
	 * @param tbBrand
	 * @param pageNum
	 * @param pageSize
	 * @return PageResult<TbBrand>
	 */
	public PageResult findPage(TbBrand tbBrand, int pageNum, int pageSize);

	/**
	 * 返回下拉列表
	 * 
	 * @return List<Map>
	 */
	public List<Map> selectBrandList();
}
