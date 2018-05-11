/**  
 * Project Name:pinyougou-pojo
 * File Name: Goods.java
 * Package Name:com.pinyougou.pojogroup
 * Date:2018年5月8日上午9:37:55 
 * Copyright (c) 2018, 1261473647@qq.com All Rights Reserved.  
 *  
 */
package com.pinyougou.pojogroup;

import java.io.Serializable;
import java.util.List;

import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.pojo.TbItem;

/**
 * 商品和商品扩展的组合实体类
 * 
 * @author zwp
 *
 */
public class Goods implements Serializable {
    
    private TbGoods goods;// 商品spu
    
    private TbGoodsDesc goodsDesc;// 商品扩展
    
    private List<TbItem> itemList;// 商品sku列表

    public TbGoods getGoods() {
        return goods;
    }

    public void setGoods(TbGoods goods) {
        this.goods = goods;
    }

    public TbGoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(TbGoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<TbItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<TbItem> itemList) {
        this.itemList = itemList;
    }
    
}
