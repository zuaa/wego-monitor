package util

import java.util.Properties;
class Init {
	static main(args) {
		println	config().zuaa1
	}

	static  def config={
		def config1=[:]
		config1<<[zuaa1:111]
		config1<<[zuaa11:111]
		config1<<[zuaa2:111111] 
	}
	 
}
