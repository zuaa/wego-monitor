package com.heiji

import javax.imageio.ImageIO

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import com.gmongo.GMongo
import com.mongodb.gridfs.GridFS
import com.mongodb.gridfs.GridFSInputFile
import com.tools.HttpUtils

class Base {

	static main(args) {
		def   mongo = new GMongo("127.0.0.1:27017")
		def    db = mongo.getDB("zuaamaid")
		def GridFS myFS = new GridFS(db);
		def path="C:\\AAAAAA_pic"

		for(int it=1011;it<1037;it++){
			def url="http://jandan.net/ooxx/page-${it}#comments"
			println url
			readUrlImage( url,path,myFS);
		}
	}

	
	
	
	
	
	
	
	

	static getMongodb(GridFS myFS ,root){ 
		def   mongo = new GMongo("127.0.0.1:27017")
		def    db = mongo.getDB("zuaamaid")
		if(myFS==null){ 
			if(root==null){
				myFS = new GridFS(db);
			}else{
				myFS = new GridFS(db,root);
			}
		} 
	}
	static readUrlImage(url,path,myFS){
		try{
			String html = HttpUtils.get(url.toString());
			Document  doc =   Jsoup.parse(html); 
			doc.getElementsByTag("img").each{
				if(it.attr("abs:src")!=""){
					def name ="${System.currentTimeMillis()}.jpg"
					getFileAndSave(path,name,it.attr("abs:src"),myFS);
					new File("${path}/${name}").delete();
				}
			}
		}catch(e){
			println e
		}
	}
	
	
	
	static downloadFile(path,name,address){
		new File("${path}/${name}").withOutputStream { out ->
			out << new URL(address).openStream()
		} 
	}
	static getFile(path,name,address){
		new File("${path}/${name}").withOutputStream { out ->
			out << new URL(address).openStream()
		}
		if(!getImageInfo(path,name,200,200)){
			new File("${path}/${name}").delete();
		}
	}

	static getFileAndSave(path,name,address,myFS,w,h){
		new File("${path}/${name}").withOutputStream { out ->
			out << new URL(address).openStream()
		}
		if(!getImageInfo(path,name,w,h)){
			new File("${path}/${name}").delete();
		}else{
			saveFileToMongodb(path,name,myFS)
		}
	}
	static getFileAndSave(path,name,address,myFS ){
		getFileAndSave(path,name,address,myFS ,200,200) 
	}
	/** 
	 * @param path
	 * @param name
	 * @param myFS
	 * @return
	 */
	static saveFileToMongodb(path,name,myFS){
		if(myFS.find(name).size()>0){
		}else{
			File f =new File("${path}\\${name}");
			if(f.isFile()){
				GridFSInputFile inputFile = myFS.createFile(f); 
				inputFile.setFilename(name);
				inputFile.save();
			}
		}
	}

	static getImageInfo(path,name,w,h){

		def img = ImageIO.read(new File("${path}\\${name}"));

		//println img.getWidth() +"|||" +img.getHeight()
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
