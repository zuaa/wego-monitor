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
insertNewtable.withBatch(100,""" insert into zuaa_pm_product_base(id, model_name, catalog_id , packages , batch_no,other) 
values (:id, :model_name, :catalog_id , :packages , :batch_no,:other) """){ insertIt-> 
			400.times { t->
				println t
				readBaseProduct.eachRow("SELECT * from pm_product_base  limit 10000 offset ${t*10000}"){ it1->
					def other="";
					//def a1=System.currentTimeMillis();
					//这里要查询其他家的信息，用于处理
					selectProduct.eachRow("select * from pm_product where base_id=${it1.id}") {it2->
						other=other+it2.toRowResult();
					}
					//println "timea1:${System.currentTimeMillis()-a1}"
					def it11= it1.toRowResult();
					it11.other=other.toString() 
					//println it11;
				 	insertIt.addBatch(it11) ;
				}

			}
		}




println "time:${System.currentTimeMillis()-a}"

println "time:${(System.currentTimeMillis()-a)*300/(1000*60*60)}"
//
//
//insertNewtable.withBatch(20, """update zuaa_pm_product_base
//                        set other = ?
//                      where id = ? """) { ps ->
//			20000.times {time->
//				sql.eachRow("""select * from pm_product LIMIT 100 offset ${1000000+time*100}"""){
//					def s="SELECT * from pm_product_base  where   id=${it.base_id}"
//					def other="";
//
//					ps.addBatch([other, it.base_id])
//					println it.base_id ;
//				}
//			}
