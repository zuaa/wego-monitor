package com.qegoo.grab.onetime

import com.gmongo.GMongo

class DatasheetcatalogErrorFileToMongodb {

	static main(args) {
		def   mongo = new GMongo("192.168.3.140:27017")
		def    db = mongo.getDB("qegooPachong")
		def errorFile=new File("/home/xuping/temp/datasheetcatalog_error.txt");
		db.datasheetcataUrl.drop(); 
		errorFile.eachLine {
			db.datasheetcataUrl<<["url":it]
		}
	}
}
