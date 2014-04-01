package com.baiji

import com.gmongo.GMongo
import com.google.gson.Gson
import com.heiji.fenling.Ling 
import com.mongodb.gridfs.GridFS

class www58picBaiji extends Baiji {

	static main(args) {

		def   mongo = new GMongo("127.0.0.1:27017")
		def    db = mongo.getDB("zuaamaid")
		def bname="www.58pic.comshiliangtu"
		def GridFS myFS = new GridFS(db,"${bname}");

		Gson _gson = new Gson();

		com.heiji.fenling.Shengling _shengling=new com.heiji.fenling.Www58picShengling();

		db."${bname}List".drop()
		200.times {
			db."${bname}List".insert([link:"http://www.58pic.com/shiliangtu/0/id-1${it+1}.html"])
		}
		def  tumbleListOne = null;
		tumbleListOne =db."${bname}List".findOne([state:null ]);

		while(tumbleListOne!=null){
			print "${tumbleListOne}"
			tumbleListOne.state=1
			db."${bname}List".save tumbleListOne
			Ling ling =new Ling()
			ling.setLink("${tumbleListOne.link}")
			ling.setLevel(1);
			_shengling.getAllUrl(ling).each{
				if(it.level==-1){
					if(saveListLink(db,"tumblrContent",it)){
						print "!"
						getFileAndSave(it.link,myFS ,_gson.toJson(it))
						saveListLink(db,"tumblrListMark",tumbleListOne)
						//当一个连接存在很多图片可以抓的时候将此url写入mark表 下次专题更新
					}
				}else{

					saveListLink(db,"${bname}List",it)
				}
			};
			tumbleListOne = null;
			tumbleListOne =db."${bname}List".findOne([state:null ]);
		}
	}
}
