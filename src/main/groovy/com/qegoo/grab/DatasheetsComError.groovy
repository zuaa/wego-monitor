package com.qegoo.grab

/**
 * 处理抓取错误的modelName
 */
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import com.gmongo.GMongo

class DatasheetsComError {

	static main(args) {


		//		println doc.html()

		//从库用于查询
		def   mongo1 = new GMongo("192.168.3.140:27017")
		def   mongo2 = new GMongo("192.168.3.19:27017")
		def    db2 = mongo2.getDB("qegooPachong")
		def    db1 = mongo1.getDB("qegooPachong")

		def error=db2.datasheets.find(modelname:"AMC1200BDWVR").limit(1).sort(timer: 0)
		error.each{ println (" ${it}   now : ${System.currentTimeMillis()}") }
		//		db1.datasheets<<["timer":System.currentTimeMillis()];


		//1398050664223

	}

}

