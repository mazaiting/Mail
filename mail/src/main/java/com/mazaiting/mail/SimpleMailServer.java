package com.mazaiting.mail;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 3. 简单邮件
 * @author mazaiting
 */
public class SimpleMailServer {
	/**
	 * 以文本格式发送邮件
	 * @param mailInfo 待发送的邮件信息
	 * @return true 发送成功; false 发送不成功
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo) {
		try {
			// 判断是否需要身份验证
			SimpleAuthenticator authenticator = null;
			Properties properties = mailInfo.getProperties();
			if (mailInfo.isValidate()) {
				// 如果需要身份验证， 则创建一个密码验证器
				authenticator = new SimpleAuthenticator(mailInfo.getUsername(), mailInfo.getPassword());
			}

			// 根据邮件会话属性和密码验证器构造一个发送邮件的Session
			Session sendMailSession = Session.getDefaultInstance(properties, authenticator);

			// 根据Session创建一个邮件信息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getTitle());
			// 设置邮件消息发送时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件
	 * @return true 发送成功; false 发送失败。
	 */
	public boolean sendHtmlMail(MailSenderInfo mailInfo) {
		try {
			// 判断是否需要身份认证
			SimpleAuthenticator authenticator = null;
			Properties properties = mailInfo.getProperties();
			// 如果需要身份认证， 则创建一个密码验证器
			if (mailInfo.isValidate()) {
				authenticator = new SimpleAuthenticator(mailInfo.getUsername(), mailInfo.getPassword());
			}
			// 根据邮件会话属性和密码验证器构造一个发送邮件的Session
			Session sendMailSession = Session.getDefaultInstance(properties, authenticator);

			// 根据Session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址， 并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getTitle());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			
			if (null != mailInfo.getContent()) {
				setContent(mailInfo, mainPart);
			}
			if (null != mailInfo.getFileList() && mailInfo.getFileList().size() > 0) {
				addAttachment(mailInfo.getFileList(), mainPart);
			}
			
			
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 设置内容
	 * @param mailInfo 邮件信息
	 * @param mainPart 容器
	 * @throws MessagingException 消息异常
	 */
	public void setContent(MailSenderInfo mailInfo, Multipart mainPart) throws MessagingException {
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart textContent = new MimeBodyPart();
		// 设置HTML内容
		textContent.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
		mainPart.addBodyPart(textContent);

	}
	
	/**
	 * 添加附件
	 * @param fileList 文件列表
	 * @param mainPart 容器
	 * @throws MessagingException 消息异常
	 */
	public void addAttachment(List<File> fileList, Multipart mainPart) throws MessagingException{
		for (int i = 0; i < fileList.size(); i++){
			File file = fileList.get(i);
			BodyPart fileContent = new MimeBodyPart();
			DataSource source = new FileDataSource(file.getAbsolutePath());
			fileContent.setDataHandler(new DataHandler(source));
			fileContent.setFileName(file.getName());
			mainPart.addBodyPart(fileContent);
		}
	}
}
