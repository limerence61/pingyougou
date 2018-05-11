package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojo.TbSpecificationOptionExample.Criteria;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.PageResult;

/**
 * 服务实现层
 * 
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {
    
    @Autowired
    private TbSpecificationMapper specificationMapper;
    
    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;
    
    /**
     * 查询全部
     */
    @Override
    public List<TbSpecification> findAll() {
        return specificationMapper.selectByExample(null);
    }
    
    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecification> page =
            (Page<TbSpecification>)specificationMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }
    
    /**
     * 增加
     */
    @Override
    public void add(Specification specification) {
        TbSpecification tbSpecification = specification.getSpecification();
        specificationMapper.insert(tbSpecification);
        List<TbSpecificationOption> specificationOptionList =
            specification.getSpecificationOptionList();
        for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
            tbSpecificationOption.setSpecId(tbSpecification.getId());
            specificationOptionMapper.insert(tbSpecificationOption);
        }
    }
    
    /**
     * 修改
     */
    @Override
    public void update(Specification specification) {
        TbSpecification tbSpecification = specification.getSpecification();
        // 更新规格
        specificationMapper.updateByPrimaryKey(tbSpecification);
        // 删除原来的规格选项
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(tbSpecification.getId());
        specificationOptionMapper.deleteByExample(example);
        // 添加新的规格选项
        List<TbSpecificationOption> specificationOptionList =
            specification.getSpecificationOptionList();
        for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
            tbSpecificationOption.setSpecId(tbSpecification.getId());
            specificationOptionMapper.insert(tbSpecificationOption);
        }
    }
    
    /**
     * 根据ID获取实体
     * 
     * @param id
     * @return
     */
    @Override
    public Specification findOne(Long id) {
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> tbSpecificationOptionList =
            specificationOptionMapper.selectByExample(example);
        Specification specification = new Specification();
        specification.setSpecification(tbSpecification);
        specification.setSpecificationOptionList(tbSpecificationOptionList);
        return specification;
    }
    
    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            specificationMapper.deleteByPrimaryKey(id);
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();// 根据specId删除规格选项
            Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);// 指定id
            specificationOptionMapper.deleteByExample(example);// 删除
        }
    }
    
    @Override
    public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        
        TbSpecificationExample example = new TbSpecificationExample();
        com.pinyougou.pojo.TbSpecificationExample.Criteria criteria = example.createCriteria();
        
        if (specification != null) {
            if (specification.getSpecName() != null && specification.getSpecName().length() > 0) {
                criteria.andSpecNameLike("%" + specification.getSpecName() + "%");
            }
            
        }
        
        Page<TbSpecification> page =
            (Page<TbSpecification>)specificationMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }
    
    /**
     * 返回规格列表
     * 
     * @see com.pinyougou.sellergoods.service.SpecificationService#selectSpecList()
     */
    @Override
    public List<Map> selectSpecList() {
        return specificationMapper.selectSpecList();
    }
    
}
