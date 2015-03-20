package util

import com.rethinkdb.RethinkDB
import com.rethinkdb.RethinkDBConnection
import com.rethinkdb.model.MapObject



RethinkDB  r = RethinkDB.r;
RethinkDBConnection con = r.connect("192.168.3.242",28026);

//r.db("test").tableCreate("heros").run(con);
//con.use("test");
r.db("test").table("heros").run(con)
//r.table("heros").insert(
//		new MapObject().with("name", "Heman").with("age", 33).with("skill", "sword"),
//		new MapObject().with("name", "Spiderman").with("age", 27).with("skill", "jumping"),
//		new MapObject().with("name", "Superman").with("age", 133).with("skill", "flying"),
//		new MapObject().with("name", "Xena").with("age", 29).with("skill", "wowza")
//		).run(con);

//println "ok";


//List<Map<String,Object>> results = r.table("heros").run(con);
Map<String,Object> result = r.get("name", "Superman").run(con);

println result







con.close()
