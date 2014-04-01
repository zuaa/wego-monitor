package com.qegoo

class MakeCode {

	static main(args) {
	
		
		19.times {
			println """
					Thread.start {
			sql.eachRow("SELECT * from content_digikey  limit ${it*50000} ,50000 "){
				try{
					save(it.ͼƬ)
				}catch(Exception e){
				}
			}
		}
"""
		}
		
		
		
	}

}
