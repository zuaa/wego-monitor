package com.qegoo.groovy



import org.apache.commons.mail.EmailAttachment
import org.apache.commons.mail.MultiPartEmail

import com.gmongo.GMongo


class CopyMongodbToPgAndEmailTome {
	static runme(){ 
		def gmongo = new GMongo("192.168.3.113:27017")
		def db = gmongo.getDB("qegoo")
		def path="/home/java/.groovy"
		def sendername="qegoologs@qegoo.cn"
		def psw="qegoo!@#2014"
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("113.106.92.173");
		email.setCharset("utf-8");
		email.addTo("xuping@gecpp.com");
		email.addTo("robinwu@gecpp.com"); 
		email.setFrom("${sendername}", "qegoo report robot");
		email.setAuthentication("${sendername}", psw);
		email.setSubject(" ${new Date()} 报告");
		def javascript=[
			"part_day_report",
			"part_month_report",
			"part_report",
			"part_year_report",
			"shop_day_report",
			"shop_month_report",
			"shop_report",
			"shop_year_report",
			"user_day_report",
			"user_month_report",
			"user_report",
			"user_year_report"
		]
		def message=""
		javascript.each{js->
			EmailAttachment attachment = new EmailAttachment();
			message=message+ "js:${js}() 执行完成，\n"
			db.eval("${js}()")
			def report=     db."${js}".find(year:[$ne:2000]).toArray()
			def out = new File("${path}/${js}.csv")
			def head=null
			report.each {
				if(head==null){
					head=[]
					it.each{ head<<it.key }
					out.write head.join(',')//write   保证删除原来的内容
					out.append '\n'
				}
				def row = []
				it.each{ row<<it.value }
				out.append row.join(',')
				out.append '\n'
			}
			attachment.setPath(out.getPath());
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			email.attach(attachment);
		}
		message=message+ "出现中文乱码，请将csv用记事本打开，另存为windows（ansi格式）格式的文件，然后用excel打开，就可以解决乱码问题，\n"
		email.setMsg("${message}\n 请查看附件");
		println("send email")
		email.send();
	}
}


