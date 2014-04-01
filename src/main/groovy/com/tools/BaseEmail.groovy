package com.tools

import com.maid.autoCode.bestpricecn.net.BaseNet;
/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-30
 * Time: 上午10:26
 * To change this template use File | Settings | File Templates.
 */
import org.apache.commons.mail.HtmlEmail;
class BaseEmail extends BaseNet {

	static main(args) {
		sendEmail "[QQ账户信息]", "1502382668", "zuaa@163.com"
	}

	static String saveMessage(String subject, String massage) {
		sendEmail subject, massage, "zuaa@163.com"
	}

	static String sendEmail(String subject, String msg, String to) {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.163.com");
		email.setCharset("utf-8");
		email.addTo(to, "黑姬");
		email.setFrom("zuaa-q@163.com", "xuping");
		email.setAuthentication("zuaa-q", "seedcat");
		email.setSubject(subject);
		email.setMsg(msg);
		email.send();
	}
}