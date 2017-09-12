package com.mazaiting.mail;

import com.sun.mail.util.MailSSLSocketFactory;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;

/**
 * 1. 发送邮件需要使用的基本信息
 * @author mazaiting
 */
public class MailSenderInfo {
	/** 发送邮件的服务器ip */
	private String mailServerHost;
	/** 发送邮件的服务器端口 */
	private String mailServerPort = "25";
	/** 邮件发送者的地址 */
	private String fromAddress;
	/** 邮件接收者的地址 */
	private String toAddress;
	/** 登录邮件发送服务器的用户名 */
	private String username;
	/** 登录邮件发送服务器的密码 --授权密码*/
	private String password;
	/** 是否需要身份验证 */
	private boolean isValidate = false;
	/** 邮件主题 */
	private String title;
	/** 邮件文本内容 */
	private String content;
	/** 邮件附件的文件 */
	private List<File> fileList;

	/**
	 * 获得邮件会话属性
	 * @throws GeneralSecurityException  证书安全异常
	 */
	Properties getProperties() throws GeneralSecurityException {
		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mc);
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", isValidate ? "true" : "false");
		// 进行证书验证
		MailSSLSocketFactory mssf = new MailSSLSocketFactory();
		mssf.setTrustAllHosts(true);
		p.put("mail.smtp.ssl.enable", true);
		p.put("mail.smtp.ssl.socketFactory", mssf);
		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	boolean isValidate() {
		return isValidate;
	}

	public void setValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}
	
	String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

}
