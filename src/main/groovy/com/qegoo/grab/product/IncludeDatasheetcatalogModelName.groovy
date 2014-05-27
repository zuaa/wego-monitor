package com.qegoo.grab.product

import groovy.sql.Sql

import com.gmongo.GMongo

def selectSql = Sql.newInstance("jdbc:postgresql://192.168.3.150:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")

 

def alldatasheetcount
selectSql.eachRow("select count(*) as c  from datasheetcatalog"){ println  it.c }
println "datasheetcatalog开始导入base表数据："

def cnum=10000;
int it=0
while(cnum==10000){
	cnum=0;
	println "select *  from datasheetcatalog limit 10000 offset ${10000*(it)} "
	selectSql.eachRow("select partname as model_number  from datasheetcatalog  limit 10000 offset ${10000*(it+1)} "){row->

		SaveAsBaseProduct.saveBaseProduct(row.model_number)
		cnum++
	}
	println ("datasheetcatalog=========cnum:${cnum} it:${it}")
	it++;
} 
 
println "datasheetcatalog完成导入base表"
selectSql.close()

//alldatasheet