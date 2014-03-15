package net.glamenvseptzen.quickstart

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
 

class TestEval {

	static main(args) {
		int z=1000
		def m=[:]
		m["ma"]=111111111
		m["zzzzz"]=77777
		m["ma1"]="a"
		m["ma2"]="aa"
		m["ma3"]="a"
	//	println Eval.x(m,' x.ma*2 * 4 >2+x.zzzzz')


	//	println Eval.x(m,' x.ma1 ==x.ma2')
	//	println Eval.x(m,' x.ma1 ==x.ma3')
		def t=("http://www.baidu.com").toURL().getText("utf-8");
		Document doc =Jsoup.parse(t);
	 
		m["doc"]=doc
		
		println Eval.x(m,'x.doc.select("form").size() ==2')
		
		
		
	}
}
