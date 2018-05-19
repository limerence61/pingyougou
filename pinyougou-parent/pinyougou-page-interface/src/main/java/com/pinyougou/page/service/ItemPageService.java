package com.pinyougou.page.service;

/**
 * 商品详细页接口
 * 
 * @author zwp
 *
 */
public interface ItemPageService {

	/**
	 * 生成商品详情页
	 * 
	 * @param goodsId
	 * @return
	 */
	public boolean genItemHtml(Long goodsId);
}
