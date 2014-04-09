#!/usr/bin/groovy
import groovy.sql.Sql
def readBaseProduct = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver") 
def insertNewtable = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")
println "truncate TABLE pm_product_property";
insertNewtable.execute("truncate TABLE pm_product_property");
println "pm_product_property truncated";
def a=System.currentTimeMillis();
insertNewtable.withBatch(100,""" insert into pm_product_property(id, model_name, propertyname , propertyvalue , productId) 
values (:id, :model_name, :catalog_id , :propertyname , :propertyvalue,:productId) """){ insertIt->
			1.times { t->
				println t
				readBaseProduct.eachRow("SELECT * from pm_product_base  limit 10000 offset ${t*10000}"){ it1->
					//分析 it1.property
					
					
					//如果是{开头，解析json数据
					
					//其他的那就是一行一组属性
					
					
					
					//insertIt.addBatch([K=v,k1=v1]) ;
					
					
				}

			}
		} 
println "time:${System.currentTimeMillis()-a}" 
println "time:${(System.currentTimeMillis()-a)*300/(1000*60*60)}"
