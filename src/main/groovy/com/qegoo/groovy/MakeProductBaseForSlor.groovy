#!/usr/bin/groovy
import groovy.sql.Sql
def readBaseProduct = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")
def selectProduct = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")

def insertNewtable = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")

println "truncate TABLE zuaa_pm_product_base";
insertNewtable.execute("truncate TABLE zuaa_pm_product_base");
println "truncated";
def a=System.currentTimeMillis();
insertNewtable.withBatch(100,""" insert into zuaa_pm_product_base(id, model_name, catalog_id , packages , batch_no) 
values (:id, :model_name, :catalog_id , :packages , :batch_no) """){ insertIt->
			1.times { t->
				println t
				readBaseProduct.eachRow("SELECT * from pm_product_base  limit 10000 offset ${t*10000}"){ it1->
					def other="";
					//def a1=System.currentTimeMillis();
					//这里要查询其他家的信息，用于处理
					selectProduct.eachRow("select * from pm_product where base_id=${it1.id}") {it2->
						other=other+it2.toRowResult();
					}
					//println "timea1:${System.currentTimeMillis()-a1}"
					insertIt.addBatch((it1.toRowResult())<<[other:other]) ;
				}

			}
		}




println "time:${System.currentTimeMillis()-a}"

println "time:${(System.currentTimeMillis()-a)*300/(1000*60*60)}"
