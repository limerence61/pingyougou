package com.pinyougou.search.service;

import java.util.Map;

/**
 * 搜索服务接口
 * 
 * @author zwp
 *
 */
public interface ItemSearchService {

	/**
	 * 搜索
	 * 
	 * @param searchMap
	 * @return
	 */
	public Map search(Map searchMap);
}
