package util

import java.security.MessageDigest

class MD5 {

	static main(args) {
		println generateMD5("aaaa")
	}



	static def generateMD5(String s) {
		MessageDigest digest = MessageDigest.getInstance("MD5")
		digest.update(s.bytes);
		new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
	}
}
