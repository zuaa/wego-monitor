package com.heiji

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import com.gmongo.GMongo
import com.mongodb.gridfs.GridFS
import com.mongodb.gridfs.GridFSInputFile
import com.tools.HttpUtils

class Koudaiyaoguai extends Base {

	static main(args) {


		def   mongo = new GMongo("127.0.0.1:27017")
		def    db = mongo.getDB("zuaamaid")
		GridFS myFS = new GridFS(db,"lushichuanshuo");

		493.times { tims->
			def l=readUrlImage("http://db.tgbus.com/Pokemon/Monster_${tims}.aspx");
			l.each {
				def name="${it["name"]}.jpg";
				def address="${it["url"].trim()}"
				if(myFS.find(name).size()>0){
				}else{
					if(address.length()>0){
						try{
							InputStream a=null;
							a=new URL(address).openStream();
							if(a!=null){
								GridFSInputFile inputFile = myFS.createFile(a, name);
								inputFile.setFilename(name);
								inputFile.put("info",it)
								inputFile.save();
							}
						}
						catch(e){
						}
					}
				}
			}
		}
	}

	//http://hs.tgbus.com/db/?classname=&Page=37

	static readUrlImage(url){
		try{
			def reList=[]
			String html = HttpUtils.get(url.toString());
			Document  doc =   Jsoup.parse(html);
			
			
			
			
			
			
			 
			return reList
		}catch(e){
			println e;
		}
	}
}
