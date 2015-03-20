package net.glamenvseptzen.quickstart

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class Jsoup1 {

	public Jsoup1() {
		// TODO Auto-generated constructor stub
	}

	static main(args) {
		def url="http://www.onlinecomponents.com/te-connectivity---amp-brand_5-1393229-4.html?p=10397437"
		Document doc = Jsoup.connect(url).get()
		print doc.html(); 
	}
}
