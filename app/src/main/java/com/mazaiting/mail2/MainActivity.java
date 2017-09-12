package com.mazaiting.mail2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.mazaiting.MailBuilder;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    new Thread(new Runnable() {
      @Override public void run() {
        new MailBuilder()
            .setServerHost("smtp.qq.com")// 设置服务器主机
            .setServerPort("465") // 设置服务器端口
            .setValidate(true) // 设置是否需要验证
            .setUserName("1449689807@qq.com") // 设置用户名
            .setPassWord("aegstpjlwlsxicdf") // 设置验证需要的密码
            .setFromAddress("1449689807@qq.com") // 设置发送邮件的地址
            .setToAddress("1425941077@qq.com") // 设置接收邮件的地址
            .setTitle("邮件标题") // 设置标题
            .setContent("邮件内容") // 设置内容
            .setFileList(null) // 设置附件
            .build(); // 发送
      }
    }).start();
  }
}
