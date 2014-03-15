package util
import org.apache.commons.mail.EmailAttachment
import org.apache.commons.mail.HtmlEmail
import org.apache.commons.mail.MultiPartEmail
class Email {

	static main(args) {
		//sendEmail "111", "1502382668", "zuaa@163.com"
		sendEmailWithAttachment "�ʼ����и���Ԥ��", "1502382668���ӣ���", "zuaa@163.com","c://1.txt"  
	}

	static String saveMessage(String subject, String massage) {
		sendEmail subject, massage, "zuaa@163.com"
	}
	static String sendEmail(String subject, String msg, String to) {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.163.com");
		email.setCharset("utf-8");
		email.addTo(to, "�ڼ�");
		email.setFrom("zuaa-q@163.com", "xuping");
		email.setAuthentication("zuaa-q", "seedcat");
		email.setSubject(subject);
		email.setMsg(msg);
		email.send();
	}
	
	
	static String sendEmailWithAttachment(String subject, String msg, String to,String filePath ) {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(filePath);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
//		 attachment.setDescription(fileInfo); 
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.163.com");
		email.setCharset("utf-8");
		email.addTo(to, "�ڼ�");
		email.setFrom("zuaa-q@163.com", "xuping");
		email.setAuthentication("zuaa-q", "seedcat");
		email.setSubject(subject); 
		email.attach(attachment); 
		email.setMsg(msg);
		email.send();
	}
	
	
}
