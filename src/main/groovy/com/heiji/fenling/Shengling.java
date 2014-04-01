package com.heiji.fenling;

import java.util.List;
/**
 * 用来剥离网络资源
 * @author zu
 *
 */
public interface   Shengling {
 
	//获取所有URL
	public   List<Ling> getAllUrl(Ling ling);	
	//限制抓取多少级的链接
	public   int level();
	//是否是列表链接
	public   boolean isList(String url);
	//是否是最终找打的链接
	public   boolean isLink(String url);
	
 
	
}
