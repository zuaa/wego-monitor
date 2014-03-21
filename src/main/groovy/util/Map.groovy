package util

class Map {

	static main(args) {
		def map=[:]
		map["zuaa"]="aaaaa"
		map["zuaa1"]="aaaaa"
		map["zuaa2"]="aaaaa"
		map["zuaa3"]="aaaaa"
		map["zuaa4"]="aaaaa"
		map["zuaa5"]="aaaaa"
		
		map.each {
			println it.key
			println it.value
		}
		
		
	}
}
