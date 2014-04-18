package com.qegoo.grab

import groovy.sql.Sql

import com.gmongo.GMongo

class InitDatasheetsComData {

	static main(args) {


		//		println doc.html()
		def   mongo = new GMongo("192.168.3.140:27017")
		def    db = mongo.getDB("qegooPachong") 
		 def allWords=[1,2,3,4,5,6,7,8,9,0,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
		 long t=System.currentTimeMillis();
		 allWords.each {x->
			 allWords.each {y->
				 allWords.each {z-> 
					 db.datasheets<<[modelname:"${x}${y}${z}",
						 url:"i am seed "
						 ,timer:t++
					 ]
				 }
			 }
		 }

	}
}

