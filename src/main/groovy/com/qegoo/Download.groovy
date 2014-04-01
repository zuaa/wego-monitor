package com.qegoo

class Download {
	static main(args) {
		download("http://media.digikey.com/Photos/Hirose%20Elect%20Photos/DF9-41BG-SERIES.jpg")
	}
	static public void download(def address) {
		new File("e://${address.tokenize('/')[-1]}").withOutputStream { out ->
			out << new URL(address).openStream()
		}
	}
}
