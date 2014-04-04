package com.tools

import org.apache.commons.mail.HtmlEmail

import com.maid.autoCode.bestpricecn.net.BaseNet
class BaseEmail extends BaseNet {

	static main(args) {
		sendEmail "[TestEmailservice]", "TestEmailservice", "zuaa@163.com"
	}

	static String saveMessage(String subject, String massage) {
		sendEmail subject, massage, "zuaa@163.com"
	}

	static String sendEmail(String subject, String msg, String to) {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("113.106.92.173");
		email.setCharset("utf-8");
		email.addTo(to, "黑姬");
		email.setFrom("service@qic.com.cn", "xuping");
		email.setAuthentication("service@qic.com.cn", "qic2010");
		email.setSubject(subject);
		email.setMsg(msg);
		email.send();
	}
}