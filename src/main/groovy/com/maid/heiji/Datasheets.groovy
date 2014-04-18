package com.maid.heiji

import com.gmongo.GMongo

class Datasheets {

	static main(args) {

		def   mongo = new GMongo("192.168.3.140:27017")
		def    db = mongo.getDB("zuaa")

		println	 db.img.find().count();
		
		
	
		
		
		
		
		
		
		
			
		
		
	}
}
