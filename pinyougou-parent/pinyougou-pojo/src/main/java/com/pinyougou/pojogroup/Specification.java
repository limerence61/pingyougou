/**  
 * Project Name:pinyougou-pojo
 * File Name: Specification.java
 * Package Name:com.pinyougou.pojogroup
 * Date:2018年5月4日下午11:45:34 
 * Copyright (c) 2018, 1261473647@qq.com All Rights Reserved.  
 *  
 */
package com.pinyougou.pojogroup;

import java.io.Serializable;
import java.util.List;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;

/**
 * 规格+规格选项组合实体类
 * 
 * @author zwp
 *
 */
public class Specification implements Serializable {
    private TbSpecification specification;
    
    private List<TbSpecificationOption> specificationOptionList;
    
    public TbSpecification getSpecification() {
        return specification;
    }
    
    public void setSpecification(TbSpecification specification) {
        this.specification = specification;
    }
    
    public List<TbSpecificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }
    
    public void setSpecificationOptionList(
        List<TbSpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }
    
}
