package com.qegoo
import java.security.MessageDigest

import com.gmongo.GMongo
class CopyOfMongodb {

	static main(args) {

		def   mongo = new GMongo("192.168.3.103:27017")
		def    db = mongo.getDB("img")

		println	 db.img.find().count();
	}
}
