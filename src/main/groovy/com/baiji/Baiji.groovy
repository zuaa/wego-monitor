package com.baiji

import javax.imageio.ImageIO

import com.gmongo.GMongo
import com.mongodb.gridfs.GridFS
import com.mongodb.gridfs.GridFSInputFile
import com.tools.Json
import com.tools.Md5

class Baiji {

	static main(args) {
		def   mongo = new GMongo("127.0.0.1:27017")
		def    db = mongo.getDB("zuaamaid")
		def GridFS myFS = new GridFS(db,"tumblr/femalesofdesire");


		getFileAndSave("http://31.media.tumblr.com/c8d4ba302b8be71b178c61b6ded0b150/tumblr_mzkiygayQO1snyla4o1_1280.jpg",myFS ,[:])
	}
	static saveListLink(db,table,message){
		if(db."${table}".find(Json.toMap(message)).count()>0){
			print "-"
			return false;
		}else{

			try{
				db."${table}".insert(Json.toMap(message))
				return true;
			}catch(e){
				return false;
			}
		}
	}
	static getFileAndSave(path,name,address,myFS,w,h,message){
		try{
			new File("${path}/${name}").withOutputStream { out ->
				out << new URL(address).openStream()
			}
			if(!getImageInfo(path,name,w,h)){
				new File("${path}/${name}").delete();
			}else{
				saveFileToMongodb(path,name,myFS,message)
			}
		}catch(e){
		}finally{
			new File("${path}/${name}").delete();
		}
	}
	static getFileAndSave(address,myFS,message){
		getFileAndSave("c:\\",Md5.generateMD5(address)+".gif",address,myFS,200,200,message)
	}

	static saveFileToMongodb(path,name,myFS,message){
		if(myFS.find(name).size()>0){
		}else{
			File f =new File("${path}\\${name}");
			if(f.isFile()){
				try{
					GridFSInputFile inputFile = myFS.createFile(f);
					inputFile.setFilename(name);
					inputFile.put("info", message)
					inputFile.save();
				}catch(e){
				}finally{
					f.delete();
				}
			}
		}
	}
	static getImageInfo(path,name,w,h){

		def img = ImageIO.read(new File("${path}\\${name}"));
		if(w!=0){
			if(h!=0){
				return img.getWidth()>w && img.getHeight()>h
			}else{
				return img.getWidth()>w
			}
		}else{
			return false;
		}
	}
}
