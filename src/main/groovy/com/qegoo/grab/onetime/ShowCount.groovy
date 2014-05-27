package com.qegoo.grab.onetime

import com.gmongo.GMongo

def alldatasheetcn=0;
def datasheets=0
def db=new GMongo("192.168.3.140:27017").getDB("qegooPachong");
while(true){
	 
	int a=db.alldatasheetcn.find().count()
	
	int b=db.datasheets.find().count() 
	
	println "alldatasheetcn:总数="+ a ;
	println "datasheets:总数"+b; 
	
	println "alldatasheetcn:增量（10秒）"+(a-alldatasheetcn);
	println "datasheets:增量（10秒）"+(b-datasheets); 
	alldatasheetcn=a 
	datasheets=b
	
	Thread.sleep(1000*10);
}



