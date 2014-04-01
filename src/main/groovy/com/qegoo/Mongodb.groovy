package com.qegoo
import com.gmongo.GMongo
import com.mongodb.gridfs.GridFS
import com.mongodb.gridfs.GridFSInputFile
class Mongodb {

	static main(args) {

		def   mongo = new GMongo("192.168.3.103:27017")
		def    db = mongo.getDB("img")
		def GridFS myFS = new GridFS(db);
		int i=0; 
		60.times{
				def path="C:\\AAAAAA_pic\\2014-1-15\\${it}";
			new File(path).eachFileRecurse {
				i=i+1
				//这里的 File 表示的是一个路径
				def name="${it.name}" ;
			//	println myFS.find(it.name)
				if(myFS.find(it.name).size()>0){
					 println "已经存在 ${it.name}"
				}else{
					File f =new File("${path}\\${it.name}");
					if(f.isFile()){
						println "save ${path}\\${it.name}"
						GridFSInputFile inputFile = myFS.createFile(f);
						inputFile.setFilename(it.name);
						inputFile.save();
					}
				}
			}
		} 
		
		
		
		
		
	}






}
