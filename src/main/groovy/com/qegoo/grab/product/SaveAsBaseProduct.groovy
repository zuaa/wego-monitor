package com.qegoo.grab.product

import com.gmongo.GMongo

class SaveAsBaseProduct{
	static def   mongo = new GMongo("192.168.3.140:27017")
	static def    db = mongo.getDB("qegooPachong")


	static def   readmongo = new GMongo("192.168.3.163:27017")
	static def    readdb = readmongo.getDB("qegooPachong")


	static main (args){ 

		println saveBaseProduct("ECEP2DP682FA")
		println saveBaseProduct("ECEP2DP682FA")
		println saveBaseProduct("ECEP2DP682FA")
		println saveBaseProduct("ECEP2DP682FA")
		println saveBaseProduct("ECEP2DP682FA")
		println saveBaseProduct("ECEP2DP682FA")
		println saveBaseProduct("ECEP2DP682FA")
		println saveBaseProduct("ECEP2DP682FA")
		println saveBaseProduct("ECEP2DP682FA")
		println saveBaseProduct("ECEP2DP682FA")
		println saveBaseProduct("ECEP2DP682FA")
	}



	static saveBaseProduct(modelName,packages){ 
		if(readdb.pm_product_base.find (modelName:modelName).count()>0){
			println "要保存的modelName ${modelName} 存在XXXXXXXXXXXXXXXXX"
			return false
		}else{
		//println "要保存的modelName ${modelName} 成功VVVVVVVVVVVVVVVVV"
			return db.pm_product_base<<[modelName:modelName,packages:packages]
		}
	}


	static saveBaseProduct(modelName ){
		
		return saveBaseProduct(modelName,null)
	}
}