package util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class CheckUrl {

	static main(args) {
		
		def t="http://www.qegoo.cn".toURL().getText("utf-8");
		Document doc =Jsoup.parse(t);
		println  doc.select('a').size();
	 
	println c("http://www.qegoo.cn","x.select('a').size() ==345")
		//	println  c("http://www.baidu.com","x.select('form').size() ==2")
	}


	static boolean c(String url,String eval){
		def t=url.toURL().getText("utf-8");
		Document doc =Jsoup.parse(t);
		return Eval.x(doc,eval);
	}
}
