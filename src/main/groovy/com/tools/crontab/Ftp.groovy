//#!/bin/bash
//PATH=/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin
//source /home/java/.profile
//groovy /home/java/.groovy/CopyMongodbToEmailTome.groovy



def ant = new AntBuilder()
ant.ftp( server:"115.28.190.139",
userid:"zuaa",
password:"zuaa@428",
passive:"yes",
verbose:"yes",
depends:"yes",
timediffauto:true,
action:"get",
remotedir:"/",
binary:"yes" ) {
	fileset( dir:"/home/xuping/backup/www/wego" ) { include( name:"**/*" ) }
}