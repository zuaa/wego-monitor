package com.tools

import java.security.MessageDigest

class Md5 {

	static main(args) {
//		println (generateMD5("aaa"))
		
		61.times {
			new File("C://Users/zu/${it}").mkdirs();
		}
	}
	static def generateMD5(String s) {
		MessageDigest digest = MessageDigest.getInstance("MD5")
		digest.update(s.bytes);
		new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
	}
}
