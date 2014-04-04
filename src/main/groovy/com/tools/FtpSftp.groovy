package com.tools

class FtpSftp {

	static main(args) {
		def ant = new AntBuilder()
		ant.ftp( server:"ftp.rocelec.com",
		userid:"qegooFTP",
		password:"Hx1962&m5",
		passive:"yes",
		verbose:"yes",
		action:"get",
		remotedir:"/",
		binary:"yes" ) {
			fileset( dir:"/home/xuping/temp/" ) { include( name:"**/*.csv" ) }
		}
	}
}
