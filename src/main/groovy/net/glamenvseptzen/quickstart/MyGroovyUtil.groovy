package net.glamenvseptzen.quickstart

class MyGroovyUtil {
	static String greed(String name) {
		return "Welcome to Groovy world, ${name} ?"
	}


	static String readUrl(String url){
		return  readUrlCode(url,"utf-8")
	}
	static String readUrlCode(String url,String code){
		return url.toURL().getText(code)
	}
	static main(args) {
		println	MyGroovyUtil.readUrl("http://www.baidu.com")
	}
}
