package cutocode

class Times {

	static main(args) {
		61.times {
			println """cp -rf ./${it}/*   /media/xuping/"Seagate Expansion Drive"/qegoo_2014_4_1_image/"""
			println "echo ${it}"
		}
	}
}
