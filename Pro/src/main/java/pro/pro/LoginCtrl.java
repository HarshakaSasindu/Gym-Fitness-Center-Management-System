package pro.pro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class LoginCtrl {

    @FXML
    public Button BtnCancel;

    @FXML
    private Label LblLoginMsg;

    @FXML
    private TextField TxtUsername;

    @FXML
    private PasswordField TxtPassword;


    public void BtnLoginOnAction (ActionEvent e) throws SQLException {


        if(TxtUsername.getText().isBlank() == false && TxtPassword.getText().isBlank() == false){
            validateLogin();
        } else {
            LblLoginMsg.setText("Please enter username and password.");
        }
    }

    public void BtnCancelOnAction(ActionEvent e) {
    	close();
    }



    public void  validateLogin() throws SQLException {
    	Connection con= Dbconnecter.connect();
        

        try {
        	

            PreparedStatement stmt = con.prepareStatement("SELECT count (User_Name) AS cnt FROM User_Acount WHERE User_Name = ?  AND Password = ?" );
            stmt.setString(1, TxtUsername.getText());
            stmt.setInt(2, Integer.parseInt(TxtPassword.getText()));

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                //welcomeText.setText(res.getString("username"));
                if (res.getInt("cnt")==1) {
                    LblLoginMsg.setText("Congrats !");
                    mainpal();
                    close();
                    
                } else {
                    LblLoginMsg.setText("Invalid login. Please try again");
                }
            }

            //new FadeIn(datePicker).play();
        }
        catch (Exception ex) {
            LblLoginMsg.setText("Invalid Password"); ;
        }
        finally {
            try {
                if (con != null)
                    con.close();
                	
            }
            catch (SQLException ex) {

            }
        }
    }
    public void mainpal() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Successful");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    public void close() {
    	Stage stage = (Stage) BtnCancel.getScene().getWindow();
        stage.close();
    }
   public String userName;
    public String password;
    @FXML
    void BtnForgetPasswordOnAction(ActionEvent event) throws SQLException {


        Connection con= Dbconnecter.connect();
        PreparedStatement ps =null;
        ResultSet rs = null;
        try {
            String sql = "Select* from User_Acount where User_Id = 1";
            ps= con.prepareStatement(sql);

            rs = ps.executeQuery();

            userName = (rs.getString("User_Name"));
            password = (rs.getString("Password"));
      

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();


            }catch(SQLException e){
                System.out.println(e);

            }
        }


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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("solofitness.group.1@gmail.com"));
            message.setSubject("SOLO fitness");


            String msg = "Your Username : " + userName + "/n Password :"+ password;

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            //MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            //attachmentBodyPart.attachFile(new File(attachfile));
            //multipart.addBodyPart(attachmentBodyPart);


            message.setContent(multipart);


            System.out.println("Sending...");
            Transport.send(message);
            Notifications notification = Notifications.create()
					.title("Send Maill")
					.text("sent messge successfully....")
					.hideAfter(Duration.seconds(5))
					.position(Pos.BOTTOM_RIGHT);
			notification.showConfirm();
            //System.out.println("sent messge successfully....");

        } catch (MessagingException mex) {
        	Notifications notification = Notifications.create()
					.title("Not Send Maill")
					.text("Check The Connection... ")
					.hideAfter(Duration.seconds(5))
					.position(Pos.BOTTOM_RIGHT);
			notification.showWarning();
            //System.out.println("sent messge successfully....");
        	
            System.out.println("not Sending...");
            mex.printStackTrace();

        }

    }

    }



