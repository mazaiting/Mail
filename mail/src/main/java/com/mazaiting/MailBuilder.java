package com.mazaiting;

import com.mazaiting.mail.MailSenderInfo;
import com.mazaiting.mail.SimpleMailServer;
import java.io.File;
import java.util.List;

/**
 *  使用方法, 默认实在主线程运行
 *     new MailBuilder()
           .setServerHost("smtp.qq.com")// 设置服务器主机
           .setServerPort("465") // 设置服务器端口
           .setValidate(true) // 设置是否需要验证
           .setUserName("1449689807@qq.com") // 设置用户名
           .setPassWord("****************") // 设置验证需要的密码
           .setFromAddress("1449689807@qq.com") // 设置发送邮件的地址
           .setToAddress(mToAddress) // 设置接收邮件的地址
           .setTitle(title) // 设置标题
           .setContent(body) // 设置内容
           .setFileList(files) // 设置附件
           .build(); // 发送
 */
public class MailBuilder {
  /**服务器地址, 默认为QQ邮箱服务器*/
  private String serverHost = "smtp.qq.com";
  /**服务器端口*/
  private String serverPort = "465";
  /**是否需要验证密码*/
  private boolean isValidate = true;
  /**用户名*/
  private String userName = "1449689807@qq.com";
  /**密码*/
  private String passWord = "";
  /**发送者地址*/
  private String fromAddress = "1449689807@qq.com";
  /** 接收者地址 */
  private String toAddress = "1425941077@qq.com";
  /** 邮件标题 */
  private String title = "邮件标题";
  /** 邮件内容 */
  private String content = "邮件内容";
  /** 附件列表 */
  private List<File> fileList = null;

  /**设置服务器地址, 默认值为"smtp.qq.com"*/
  public MailBuilder setServerHost(String serverHost) {
    this.serverHost = serverHost;
    return this;
  }

  /**设置服务器端口, 默认值为"465"*/
  public MailBuilder setServerPort(String serverPort) {
    this.serverPort = serverPort;
    return this;
  }

  /**设置是否需要验证密码, 默认值为true,即为需要验证.*/
  public MailBuilder setValidate(boolean validate) {
    isValidate = validate;
    return this;
  }

  /**设置用户名, 默认值为"1449689807@qq.com"*/
  public MailBuilder setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  /**设置验证需要的密码, 默认为空*/
  public MailBuilder setPassWord(String passWord) {
    this.passWord = passWord;
    return this;
  }

  /**设置发送者地址, 默认值为"1449689807@qq.com"*/
  public MailBuilder setFromAddress(String fromAddress) {
    this.fromAddress = fromAddress;
    return this;
  }

  /**设置接收者地址, 默认值为"1425941077@qq.com"*/
  public MailBuilder setToAddress(String toAddress) {
    this.toAddress = toAddress;
    return this;
  }

  /**设置标题, 默认值为"邮件标题"*/
  public MailBuilder setTitle(String title) {
    this.title = title;
    return this;
  }

  /**设置内容, 默认值为"邮件内容"*/
  public MailBuilder setContent(String content) {
    this.content = content;
    return this;
  }

  /**设置附件列表, 默认值为null*/
  public MailBuilder setFileList(List<File> fileList) {
    this.fileList = fileList;
    return this;
  }

  /**构建MailBuilder对象，并发送邮件*/
  public boolean build() {
    // 设置邮件
    MailSenderInfo mailInfo = new MailSenderInfo();
    // 设置服务器主机名
    mailInfo.setMailServerHost(serverHost);
    // 设置服务器端口
    mailInfo.setMailServerPort(serverPort);
    // 设置密码验证
    mailInfo.setValidate(isValidate);
    // 设置用户名
    mailInfo.setUsername(userName);
    // 设置登录验证需要的密码
    mailInfo.setPassword(passWord);
    // 设置发送者地址
    mailInfo.setFromAddress(fromAddress);
    // 设置接收者地址
    mailInfo.setToAddress(toAddress);
    // 设置邮件标题
    mailInfo.setTitle(title);
    // 设置邮件内容
    mailInfo.setContent(content);
    // 设置邮件携带的附件
    //		List<File> fileList = new ArrayList<>();
    //		fileList.add(new File("C:\\Users\\Administrator\\Desktop\\password.png"));
    if (null != fileList) {
      mailInfo.setFileList(fileList);
    }
    // 发送邮件
    SimpleMailServer sms = new SimpleMailServer();
    //		sms.sendTextMail(mailInfo);// 发送文本格式
    return sms.sendHtmlMail(mailInfo);// 发送html格式
  }
}
