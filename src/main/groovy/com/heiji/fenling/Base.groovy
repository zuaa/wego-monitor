package com.heiji.fenling

import java.util.List;

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import com.google.gson.Gson
import com.tools.HttpUtils
import com.tools.Json

class Base {



	public List<Ling> getAllUrl(Ling ling) {
		def re=[]
		if(ling==null){
			return null;
		}
		def allList=readUrlForA(ling?.link).unique()+readUrlForImage(ling?.link).unique()
		allList.each{
			Ling _Ling=new Ling();
			if(isLink(it)){
				_Ling.setLing(true)
				_Ling.setLevel(-1);
				_Ling.setLink(it);
				re<<_Ling;
			}else if(isList(it)){
				//级别没有超出范围
				if(level()>ling.getLevel()){
					//是列表
					_Ling.setLing(false)
					//升级level
					_Ling.setLevel(ling.getLevel()+1);
					_Ling.setLink(it);
					re<<_Ling;
				}
			}
		}
		
		
		
		
		return re;
	}



	public boolean isList(String url) {

		return false;
	}

	public boolean isLink(String url) {

		return false;

	}

	public int level() {
		return 10;
	}
	def readUrl(String url){
		return HttpUtils.get(url.toString().replace(" ", ""));
	}

	def readUrlForImage(url){
		Document  doc =   Jsoup.parse(readUrl(url));
		def srcList=[]
		doc.getElementsByTag("img").each{
			if(it.attr("abs:src")!=""){
				srcList<<it.attr("abs:src")
			}
		}
		return srcList;
	}

	def readUrlForA(url){
		Document  doc =   Jsoup.parse(readUrl(url));
		def srcList=[]
		doc.getElementsByTag("a").each{
			if(it.attr("abs:href")!=""){
				srcList<<it.attr("abs:href")
			}
		}
		return srcList;
	}

	def Object2Map(object){
		Gson _gson = new Gson();
		String json=_gson.toJson(object);
		return  Json.parseData(json);
	}

	static main(args) {
		Base _Base =new Base();
		println _Base.readUrlForImage("http://moody1988.lofter.com/")

		println _Base.readUrlForA("http://moody1988.lofter.com/")
	}
}
