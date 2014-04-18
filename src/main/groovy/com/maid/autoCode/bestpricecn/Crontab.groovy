package com.maid.autoCode.bestpricecn

class Crontab {

	static main(args) {
		//检查基本演绎法有没有更新 
		def j=20;
		def youku_jibenyanyi="http://heiji13.duapp.com/jiankong-1.0-SNAPSHOT/Grab?url=http://www.youku.com/show_page/id_z20a394f4b7c111e296da.html&eval=x.select(%27.coll_10%20li%27).size()%20%3C=${j}"
		println youku_jibenyanyi.toURL().getText();
	}
}
