package com.mazaiting.mail;

import javax.mail.*;

/**
 * 2. 简单的验证管理
 * @author mazaiting
 */
public class SimpleAuthenticator extends Authenticator{
	/**用户名*/
	private String username = null;
	/**密码*/
	private String password = null;
	public SimpleAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
