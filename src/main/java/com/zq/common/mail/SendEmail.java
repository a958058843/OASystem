package com.zq.common.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.zq.utils.BasePropertyKeyValue;

public class SendEmail {

	private static BasePropertyKeyValue keyValue = com.zq.utils.BasePropertyKeyValue.getInstance();

	public static void SendEmailInfoUser163(String receiverAddress, String title, String content)
			throws MessagingException {
		Properties properties = new Properties();
		properties.setProperty("mail.host", keyValue.getProperty("mail.host"));
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		// 1.创建Session
		Session session = Session.getInstance(properties);
		// 2.开启session的debug模式，方便查看发送email的运行状态
		session.setDebug(true);
		// 3.通过session得到transport对象
		Transport transport = session.getTransport();

		// 4.使用用户名密码连接上邮箱服务器,此处的密码需要到邮箱开启服务设置
		transport.connect(keyValue.getProperty("mail.host"), keyValue.getProperty("mail.serverUser"),
				keyValue.getProperty("mail.serverPassword"));
		// 5.创建邮件
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress(keyValue.getProperty("mail.sender")));
		// 指明邮件的收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiverAddress));

		// 邮件的标题
		message.setSubject(title);
		// 邮件的内容
		message.setContent(content, "text/html;charset=UTF-8");
		// 6.发送邮件
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}

	/*public static void SendEmailInfoUserQQ(String receiverAddress, String title, String content)
			throws MessagingException {
		Properties properties = new Properties();

		properties.setProperty("mail.host", keyValue.getProperty("mail.host"));
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");

		// 使用qq邮箱服务器需要加入ssl验证
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(properties);
		Transport transport = session.getTransport();
		transport.connect(keyValue.getProperty("mail.host"), keyValue.getProperty("mail.serverUser"),
				keyValue.getProperty("mail.serverPassword"));

		session.setDebug(true); // 设置获取 debug 信息
		MimeMessage mimeMessage = new MimeMessage(session);

		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverAddress));

		mimeMessage.setSubject(title);

		mimeMessage.setSentDate(new Date());
		mimeMessage.setContent(content, "text/html; charset=UTF-8");

		mimeMessage.setFrom(new InternetAddress(keyValue.getProperty("mail.sender")));
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
	}*/

}
