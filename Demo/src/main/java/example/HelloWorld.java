package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;

@WebService
public class HelloWorld {

  private String code;
  private String target;

  @WebMethod
  public String sendMailTo(String targetAddress) throws Exception {
    target = targetAddress;
    Properties prop = new Properties();
    // 开启debug调试，以便在控制台查看
    prop.setProperty("mail.debug", "true");
    // 设置邮件服务器主机名
    prop.setProperty("mail.host", "smtp.qq.com");
    // 发送服务器需要身份验证
    prop.setProperty("mail.smtp.auth", "true");
    // 发送邮件协议名称
    prop.setProperty("mail.transport.protocol", "smtp");
    // 开启SSL加密，否则会失败
    MailSSLSocketFactory sf = new MailSSLSocketFactory();
    sf.setTrustAllHosts(true);
    prop.put("mail.smtp.ssl.enable", "true");
    prop.put("mail.smtp.ssl.socketFactory", sf);
    // 创建session
    Session session = Session.getInstance(prop);
    // 通过session得到transport对象
    Transport ts = session.getTransport();
    // 连接邮件服务器：邮箱类型，帐号，POP3/SMTP协议授权码
    ts.connect("smtp.qq.com", "1157982786@qq.com", "gsuikocyrzvfigcc");
    // 创建邮件
    Message message = createSimpleMail(session, targetAddress);
    // 发送邮件
    ts.sendMessage(message, message.getAllRecipients());
    ts.close();
    String result = "发送邮件到" + targetAddress + "成功";
    System.out.println(result);
    return result;
  }

  /**
   * @Method: createSimpleMail
   * @Description: 创建一封只包含文本的邮件
   */
  public MimeMessage createSimpleMail(Session session, String targetAddress) throws Exception {
    //  获取6为随机验证码
    String[] letters = new String[] {
            "q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m",
            "A","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M",
            "0","1","2","3","4","5","6","7","8","9"};
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      stringBuilder.append(letters[(int) Math.floor(Math.random() * letters.length)]);
    }
    code = String.valueOf(stringBuilder);
    // 创建邮件对象
    MimeMessage message = new MimeMessage(session);
    // 指明邮件的发件人
    message.setFrom(new InternetAddress("1157982786@qq.com"));
    // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(targetAddress));
    // 邮件的标题
    message.setSubject("JavaMail测试");
    // 邮件的文本内容
    message.setContent("验证码为: "+stringBuilder+ ",请勿回复此邮箱", "text/html;charset=UTF-8");
    // 返回创建好的邮件对象
    message.setDescription(String.valueOf(stringBuilder));
    return message;
  }

  @WebMethod
  public String checkCode(String inputCode){
    String result;
    if(code.equals(inputCode)){
      result = "验证成功";
      if(target.contains("smail")){
        result+=",身份为学生";
      }else {
        result+=",身份为教师";
      }
    }else{
      result = "验证失败";
    }
    System.out.println(result);
    return result;
  }
}
