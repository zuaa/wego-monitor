package com.qegoo.grab.onetime

import groovy.sql.Sql

import com.gmongo.GMongo



def selectSql = Sql.newInstance("jdbc:postgresql://192.168.3.150:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")

def   mongo = new GMongo("192.168.3.140:27017")
def    db = mongo.getDB("qegooPachong")
db.alldatasheeturl.drop();



selectSql.eachRow("SELECT * from alldatasheeturl  "){
	db.alldatasheeturl<<["url":it.url]
}

//13544.0
//done

