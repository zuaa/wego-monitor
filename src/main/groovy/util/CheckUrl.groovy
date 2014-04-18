package util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class CheckUrl {

	static main(args) {

		//		def t="http://www.youku.com/show_page/id_z20a394f4b7c111e296da.html".toURL().getText("utf-8");
		//		Document doc =Jsoup.parse(t);
		//		println  doc.select('.coll_10 li').size();

		println c("http://www.youku.com/show_page/id_z20a394f4b7c111e296da.html","x.select('.coll_10 li').size() >=20 ")
		//	println  c("http://www.baidu.com","x.select('form').size() ==2")
	}


	static boolean c(String url,String eval){
		def t=url.toURL().getText("utf-8");
		Document doc =Jsoup.parse(t);
		return Eval.x(doc,eval);
	}
}
