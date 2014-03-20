package util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class ReadItInMyKindle {

	static String   emailKindle(url){


		//read list
		Document doc =Jsoup.connect(url).get();

		if(url.toString().contains("jianshu.io")){
			//这里是简书

			doc.select(".list-group a").each {
				def turl=it.attr("abs:href");
				Document doct =Jsoup.connect(turl).get();
				def title=doct.select("h1").first().text()
				def content = doct.select(".show-content").first().html();
				try {
					println "${title}"
					util.Email.sendEmail title, content, "firesoulzu.a900de8@m.evernote.com"
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		} 
		return "'"
	}

	static main(args) {
		emailKindle("http://jianshu.io/p/70007d39874d")
	}
}
