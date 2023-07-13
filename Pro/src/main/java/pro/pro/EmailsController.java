package pro.pro;

import javafx.fxml.FXML;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;

import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class EmailsController {
	@FXML
	private AnchorPane addMember;
	@FXML
	private TextField tfSearch;
	@FXML
	private TextArea taMessg;
	@FXML
	private TextField tfTo;
	public String attachfile;

	// Event Listener on Button.onAction
	@FXML
	public void Cancel(ActionEvent event) {
		tfSearch.setText(null);
		taMessg.setText(null);
		 tfTo.setText(null);
	}
	// Event Listener on Button.onAction
	@FXML
	public void send(ActionEvent event) {
		 String to = tfTo.getText();

	        String from = "solofitness.group.1@gmail.com";

	        Properties properties = System.getProperties();

	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "465");
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.auth", "true");

	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("solofitness.group.1@gmail.com", "sjxluszbbeswovuq");

	            }
	        });

	        session.setDebug(true);

	        try {
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(from));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            message.setSubject("SOLO fitness");


	            String msg = taMessg.getText();

	            MimeBodyPart mimeBodyPart = new MimeBodyPart();
	            mimeBodyPart.setContent(msg, "text/html");

	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(mimeBodyPart);

	            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
	            attachmentBodyPart.attachFile(new File(attachfile));
	            multipart.addBodyPart(attachmentBodyPart);
	            message.setContent(multipart);


	            System.out.println("Sending...");
	            Transport.send(message);

	            System.out.println("sent messge successfully....");
				Notifications notification3 = Notifications.create()
						.title("Sending")
						.text("Sent Message Successfully.... ")
						.hideAfter(Duration.seconds(5))
						.position(Pos.BOTTOM_RIGHT);
				notification3.show();

	        } catch (MessagingException mex) {

	            System.out.println("not Sending...");
				Notifications notification3 = Notifications.create()
						.title("Not Sending")
						.text(" Not Sending..Please Try Again")
						.hideAfter(Duration.seconds(5))
						.position(Pos.BOTTOM_RIGHT);
				notification3.showError();
	            mex.printStackTrace();

	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }
	
	// Event Listener on Button.onAction
	@FXML
	public void search(ActionEvent event) throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			String sql = "Select Mail from Member where Reg = ?";
			ps= con.prepareStatement(sql);
			ps.setString(1, tfSearch.getText());
			
			rs = ps.executeQuery();
			
			tfTo.setText(rs.getString(1));	
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();	
			}catch(SQLException e){
				System.out.println(e);	
		}
	}
	}
	// Event Listener on Button.onAction
	@FXML
	public void Attach(ActionEvent event) {
		JFileChooser filechooser = new JFileChooser();
		filechooser.showOpenDialog(null);
		File file = new File(filechooser.getSelectedFile().getAbsolutePath());
		attachfile= file.getPath();
		Notifications notification1 = Notifications.create()
				.title("Attchment")
				.text("Attch Successfully....!")
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT);
		notification1.show();
		
	}
}
