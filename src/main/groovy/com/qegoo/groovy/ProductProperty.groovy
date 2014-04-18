#!/usr/bin/groovy
import groovy.sql.Sql

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
def readBaseProduct = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")
def insertNewtable = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")
println "truncate TABLE pm_product_property";
insertNewtable.execute("truncate TABLE pm_product_property");
println "pm_product_property truncated";
def a=System.currentTimeMillis();
insertNewtable.withBatch(100,""" insert into pm_product_property(  model_name, propertyname , propertyvalue , productId) 
values (  :model_name,   :propertyname , :propertyvalue,:productId) """){ insertIt->
			300.times { t->
				println t
				readBaseProduct.eachRow("SELECT * from pm_product_base  limit 10000 offset ${t*10000}"){ it1->
					//分析 it1.property
					println "=================${it1.id}============================="
					def p=it1.property
					if(p!=null){
						p=p.trim()
						if(p[0]=="{"){
							//如果是{开头，解析json数据
							GsonBuilder gb = new GsonBuilder();
							Gson g = gb.create();
							Map<String, String> map = g.fromJson(p, new TypeToken<Map<String, String>>() {}.getType());

							map.each {mapone->
								insertIt.addBatch([model_name:it1.model_name,propertyname:mapone.key,propertyvalue:mapone.value,productId:it1.id]) ;
							}

						}else{
							//其他的那就是一行一组属性
							def propertys=p.toString().tokenize('\n')
							propertys.each{one->
								def onep= one.trim().tokenize(':');
								if(onep.size()==2){
									insertIt.addBatch([model_name:it1.model_name,propertyname:onep[0],propertyvalue:onep[1],productId:it1.id]) ;
								}

							}
						}
					}


 

				}

			}
		}
println "time:${System.currentTimeMillis()-a}"
println "time:${(System.currentTimeMillis()-a)*300/(1000*60*60)}"
