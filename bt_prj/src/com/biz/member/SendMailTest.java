package com.biz.member;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//------------------------------------------------------------------------
//https://www.google.com/settings/security/lesssecureapp
//https://support.google.com/accounts/answer/6009563
//https://accounts.google.com/DisplayUnlockCaptcha
//534-5.7.14
//------------------------------------------------------------------------


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMailTest
{
	public final String HOST = "smtp.gmail.com";
	public final String HOST_ID = "com.korea@gmail.com"; //보내는사람 메일주소
	public final String HOST_PW = "d"; //보내는 사람 메일 패스워드
	public final String HOST_PORT = "587";
	public final String HOST_AUTH = "true";
	public final String HOST_STARTTLS = "true";

//   public static void main(String [] args)
//   { 
//	   SendMailTest g = new SendMailTest();
//	   g.SendMail();
//   }
   						 //받는사람           //메일제목	    //메일내용
   public void SendMail(String to, String subject, String body) {
	   
      // Sender's email ID needs to be mentioned
     // String from = "coms.korea@gmail.com";
    //  String pass ="dd";
      // Recipient's email ID needs to be mentioned.
      //String to = "pontea1123@google.com"; //받는사람 e-mail


      // Get system properties
      Properties properties = System.getProperties();
      // Setup mail server
      properties.put("mail.smtp.starttls.enable", HOST_STARTTLS);
      properties.put("mail.smtp.host", HOST);
      properties.put("mail.smtp.user", HOST_ID);
      properties.put("mail.smtp.password", HOST_PW);
      properties.put("mail.smtp.port", HOST_PORT);
      properties.put("mail.smtp.auth", HOST_AUTH);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(HOST_ID));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
               new InternetAddress(to));

         // Set Subject: header field
         message.setSubject(subject); //제목
         
         
         
         //pw 랜덤설정----------------------------
         //0~9까지 생성 *10
//         int rnum1 = (int)(Math.random() *10);
//         int rnum2 = (int)(Math.random() *10);
//         int rnum3 = (int)(Math.random() *10);
//         int rnum4 = (int)(Math.random() *10);
//         
//         String newPw = "!@"+rnum1+rnum2+rnum3+rnum4;
//         System.out.println(newPw);
         
         //DB Pw변경----------------------------
         //update member
         
         
//         StringBuffer buffer = new StringBuffer();
//         buffer.append("<html>");
//         buffer.append("<head>");
//         buffer.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
//         buffer.append("<title>Insert title here</title>");
//         buffer.append("</head>");
//         buffer.append("<body>");
//         buffer.append("<table width='200'>");
//         buffer.append("<tr><td><img src='http://www.ikosmo.co.kr/userfile/main_list/1523413562121866.jpg'></td></tr>")         <tr><td>비밀번호 확인</td></tr>
//         buffer.append("</table>");
//         buffer.append("</body>");
//         buffer.append("</html>");

         String htmlStr = "<font color='blue'> Test </font>"; //내용
         message.setContent(htmlStr, "text/html"); //html 형식으로 보내기
         //message.setText(htmlStr); //일반 문장으로 보내기
         
         // Send message
         Transport transport = session.getTransport("smtp");
         transport.connect(HOST, HOST_ID, HOST_PW);
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}


//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class SendMailGoogleTest {
//
//   public static void main(String[] args) {
//
//      final String username = "coms.korea@gmail.com";
//      final String password = "dd";
//
//      Properties props = new Properties();
//      props.put("mail.smtp.auth", "true");
//      props.put("mail.smtp.starttls.enable", "true");
//      props.put("mail.smtp.host", "smtp.gmail.com");
//      props.put("mail.smtp.port", "587");
//
//      Session session = Session.getInstance(props,
//        new javax.mail.Authenticator() {
//         protected PasswordAuthentication getPasswordAuthentication() {
//            return new PasswordAuthentication(username, password);
//         }
//        });
//
//      try {
//
//         Message message = new MimeMessage(session);
//         message.setFrom(new InternetAddress("koms.korea@gmail.com"));
//         message.setRecipients(Message.RecipientType.TO,
//            InternetAddress.parse("koms.korea@gmail.com"));
//         message.setSubject("Testing Subject");
//         message.setText("Dear Mail Crawler No spam to my email, please!");
//
//         Transport.send(message);
//
//         System.out.println("Done");
//
//      } catch (MessagingException e) {
//         throw new RuntimeException(e);
//      }
//   }
//}

//import java.util.Properties;  
//import javax.mail.*;  
//import javax.mail.internet.*;  
//
//public class SendMailGoogleTest {
//   //http://blog.naver.com/PostView.nhn?blogId=choda100&logNo=220848908601
//   
//   
//   public static void main(String[] args) {  
//
//
//      /* Outgoing Mail (SMTP) Server
//             requires TLS : smtp.gmail.com (use authentication)
//             Use Authentication: Yes
//             Use STARTTLS: Yes(some clients call this SSL)
//       Port: 465 or 587
//       Account Name : admin@xxx.co.kr
//       Email Address : admin@xxx.co.kr
//       Password : xxxxxx
//       */
//      final String user="coms.korea@gmail.com";  
//      final String password="dd";  
//
//      String from = "coms.korea@gmail.com";
//      String to = "coms.korea@gmail.com";   
//
//      //Get the session object  
//      Properties props = new Properties();  
//      props.put("mail.smtp.host", "smtp.gmail.com");
//      props.put("mail.smtp.auth", "true");  
//      props.put("mail.smtp.starttls.enable", "true");
//      props.put("mail.smtp.port", "587");
//      
//
//      Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {  
//         protected PasswordAuthentication getPasswordAuthentication() {  
//            return new PasswordAuthentication(user,password);  
//         }  
//      });  
//
//      //Compose the message  15*25  
//      try {  
//         MimeMessage message = new MimeMessage(session);  
//         message.setFrom(new InternetAddress(from));  
//         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
//         message.setSubject("Send MAIL TEST......kosmo jp5");  
//         message.setText("This is simple program of sending email using JavaMail API");  
//
//         //send the message  
//         Transport.send(message);  
//
//         System.out.println("======================");  
//
//      } catch (MessagingException e) {e.printStackTrace();}  
//   }  
//}  