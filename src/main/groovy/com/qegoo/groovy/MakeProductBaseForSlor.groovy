package com.qegoo.groovy
import groovy.sql.Sql


def sql = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")
def sql2 = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")

def sql3 = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")


sql3.withBatch(20, """update zuaa_pm_product_base
                        set other = ? 
                      where id = ? """) { ps ->
			20000.times {time->
				sql.eachRow("""select * from pm_product LIMIT 100 offset ${1000000+time*100}"""){
					def s="SELECT * from zuaa_pm_product_base  where   id=${it.base_id}"
					def other="";
					sql.eachRow(s){ it1->
						other=other+it1.model_name+" "
						other=other+it1.packages+" "
						other=other+(it1.batch_no!=null?it1.batch_no:" ")
						other=other+it1.description+" "
						other=other+it1.property+" "
					}
					ps.addBatch([other, it.base_id])
					println it.base_id ;
				}
			}
		}
