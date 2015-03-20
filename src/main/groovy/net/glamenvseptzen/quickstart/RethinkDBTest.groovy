package net.glamenvseptzen.quickstart

import groovy.sql.Sql

import com.rethinkdb.RethinkDB
import com.rethinkdb.RethinkDBConnection
import com.rethinkdb.model.MapObject

class RethinkDBTest {

	static main(args) { 
		1000.times {
			getPort(28017,319911+10000*it)
		}
	}
	static def getPort(port,offset){
		RethinkDB r = RethinkDB.r;
		RethinkDBConnection conn = r.connect("192.168.3.237",port);
		conn.use("zuaa");
		def sql = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm150", "qic_pm","", "org.postgresql.Driver");
		List<MapObject>  batch=[]
		sql.eachRow("select * from pm_product limit 10000 offset ${offset}" , {
			r.table("pm_product").insert(  new MapObject().with("pn", it.pn).with("mfs", it.mfs).with("url", it.grab_url).with("param", it.param)).run(conn);
		})
	}
}
