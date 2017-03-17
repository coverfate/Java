/**   
 * 
 * @Title: TestMail.java 
 * @Prject: HCCRM2
 * @Package: com.hc.crm.mail 
 * @Description: TODO
 * @author: chenchao   
 * @date: 2016年6月8日 上午11:14:56 
 * @version: V1.0   
 */
package com.cover.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class CFMailUtil {
	private static Logger logger = Logger.getLogger(CFMailUtil.class);
	private static CFPropertiesUtil personalUtils = CFPropertiesUtil.getCommonConfig();
	public static final String mailUsername = personalUtils.getProperties().getProperty("mailUsername_send");
	public static final String mailPassword = personalUtils.getProperties().getProperty("mailPassword_send");
	public static final String mailServer = personalUtils.getProperties().getProperty("mailServer_send");
	public static final String mailAddress = personalUtils.getProperties().getProperty("mailAddress_receive");

	public static void sends(String str_from, String str_to, String str_title, String str_content) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", mailServer);
			props.put("mail.smtp.auth", "true");
			Session s = Session.getInstance(props, null);
			s.setDebug(true);
			MimeMessage message = new MimeMessage(s);
			InternetAddress from = new InternetAddress(str_from);
			message.setFrom(from); // 设置发件人的地址

			// 设置收件人,并设置其接收类型为TO
			// InternetAddress to = new InternetAddress(str_to);

			String[] toStr = str_to.split(",");
			InternetAddress[] tos = null;

			if (toStr == null || toStr.length <= 0) {
				return;
			}

			tos = new InternetAddress[toStr.length];

			for (int i = 0; i < toStr.length; i++) {
				tos[i] = new InternetAddress(toStr[i]);// 设置每一个接收邮件的地址
			}

			message.setRecipients(Message.RecipientType.BCC, tos);

			// 设置标题
			message.setSubject(str_title);

			// 设置信件内容
			// message.setText(str_content); //发送文本邮件,下面一行为发送html邮件
			message.setContent(str_content, "text/html;charset=gbk");

			// 设置发信时间
			message.setSentDate(new Date());

			// 存储邮件信息
			message.saveChanges();

			Transport transport = s.getTransport("smtp");

			// 以smtp方式登录邮箱,第一个参数是发送邮件用的邮件服务器SMTP地址,第二个参数为用户名,第三个参数为密码
			transport.connect(mailServer, mailUsername, mailPassword);

			// 发送邮件,其中第二个参数是所有已设好的收件人地址

			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (Exception e) {
			logger.info("" + e);
		}

	}

	public static void sends(String str_title, String str_content) {
		sends(mailUsername, mailAddress, str_title, str_content);
	}

	public static void main(String[] args) {
		CFMailUtil.sends(mailUsername, "chenchao@hcfortune.com", "测试2", "测试正文");

	}

}
