package com.baiji

import com.gmongo.GMongo
import com.google.gson.Gson
import com.heiji.fenling.Ling
import com.heiji.fenling.TumblrShengling
import com.mongodb.gridfs.GridFS

class TumblrBaiji extends Baiji {

	static main(args) {
	 
		def   mongo = new GMongo("127.0.0.1:27017")
		def    db = mongo.getDB("zuaamaid")
		def bname="femalesofdesire"
		def GridFS myFS = new GridFS(db,"tumblr/${bname}");
		
		Gson _gson = new Gson();

		TumblrShengling _LofterShengling=new TumblrShengling();

		//init   http://femalesofdesire.tumblr.com/

		 db.tumblrList.insert([link:"http://${bname}.tumblr.com/"])


		def  tumbleListOne = null;
		tumbleListOne =db.tumblrList.findOne([state:null ]);

		while(tumbleListOne!=null){
			print "."
			tumbleListOne.state=1
			db.tumblrList.save tumbleListOne
			Ling ling =new Ling()
			ling.setLink("${tumbleListOne.link}")
			ling.setLevel(1);
			_LofterShengling.getAllUrl(ling).each{
				if(it.level==-1){
				 
					if(saveListLink(db,"tumblrContent",it)){
						print "!"
						getFileAndSave(it.link,myFS ,_gson.toJson(it))
						saveListLink(db,"tumblrListMark",tumbleListOne)
						//当一个连接存在很多图片可以抓的时候将此url写入mark表 下次专题更新
					}
				}else{

					saveListLink(db,"tumblrList",it)
				}
			};


			tumbleListOne = null;
			tumbleListOne =db.tumblrList.findOne([state:null ]);
		}
	}
}
