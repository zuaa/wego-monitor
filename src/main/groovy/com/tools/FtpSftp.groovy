package com.tools

class FtpSftp {
	
	static main(args) {
		//这里是增量
		def ant = new AntBuilder()
		ant.ftp( server:"ftp.rocelec.com",
		userid:"qegooFTP",
		password:"Hx1962&m5",
		passive:"yes",
		verbose:"yes",
		depends:"yes",
		timediffauto:true,
		action:"get",
		remotedir:"/",
		binary:"yes" ) {
			fileset( dir:"/home/xuping/temp/" ) { include( name:"**/*.csv" ) }
		}
	}
}
