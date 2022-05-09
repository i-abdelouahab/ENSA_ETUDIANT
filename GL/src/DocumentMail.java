
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class DocumentMail {
	

	
		public static void email(String c,String file) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port","587");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.transport.protocol","smtp");
		
		Session session = Session.getInstance(props ,new javax.mail.Authenticator() {
		    protected 	PasswordAuthentication getPasswordAuthentication( ) {
		    	
		    
			    return new PasswordAuthentication("kallylinux03@gmail.com","issam2014" );
	                             	} }
		) ;
		
		Message message = new MimeMessage(session);
		
		try {
			message.setSubject("Réponse à votre demande");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Address addressTo = null;
		try {
			addressTo = new InternetAddress(c);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			message.setRecipient(Message.RecipientType.TO, addressTo);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MimeMultipart multipart = new MimeMultipart();
		MimeBodyPart attachment = new MimeBodyPart();
		try {
			attachment.attachFile(new File(file));
		} catch (IOException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		try {
			messageBodyPart.setContent(" Bonjour,votre demande a été acceptée et vous trouverez ci-joint le document contenant la demande " ,  "text/html");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			multipart.addBodyPart(attachment);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			multipart.addBodyPart(messageBodyPart);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			message.setContent(multipart);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		/*public static void main(String[] args)  {
			String c="derouachkaoutar@gmail.com";
			email(c);

		
		}*/

}
