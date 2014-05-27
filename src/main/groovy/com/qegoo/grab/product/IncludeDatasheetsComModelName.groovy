package com.qegoo.grab.product

import groovy.sql.Sql

import com.gmongo.GMongo

def selectSql = Sql.newInstance("jdbc:postgresql://192.168.3.150:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")



def alldatasheetcount
selectSql.eachRow("select count(*) as c  from qic_model"){ println  it.c }
println "qic_model开始导入base表数据："

def cnum=10000;
int it=0
while(cnum==10000){
	cnum=0;
	println "select *  from qic_model limit 10000 offset ${10000*(it)} "
	selectSql.eachRow("select model_number  from qic_model  limit 10000 offset ${10000*(it+1)} "){row->

		SaveAsBaseProduct.saveBaseProduct(row.model_number)
		cnum++
	}
	println ("cnum:${cnum} it:${it}")
	it++;
}

 
println "qic_model完成导入base表"
selectSql.close()

//alldatasheet