package com.qegoo.base

import java.security.MessageDigest

class BaseTools {

	static main(args) {
	
	} 
	static def generateMD5(String s) {
		MessageDigest digest = MessageDigest.getInstance("MD5")
		digest.update(s.bytes);
		new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
	}
}
