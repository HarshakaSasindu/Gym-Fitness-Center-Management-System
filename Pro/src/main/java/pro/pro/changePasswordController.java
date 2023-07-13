package pro.pro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;

public class changePasswordController implements Initializable {
	@FXML
	private AnchorPane addMember;
	@FXML
	private TextField tfCPassword;
	@FXML
	private TextField tfCNPassword;
	@FXML
	private TextField tfUsername;
	@FXML
	private Button btnAdd;
	@FXML
	private TextField tfNpassword;
	
	public String UserName,Password;

	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			getuserinfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#btnAdd].onAction
	@FXML
	public void save(ActionEvent event) throws SQLException {
		System.out.println(UserName);
		System.out.println(Password);
		
		if(UserName.equals(tfUsername.getText()) && Password.equals(tfCPassword.getText())){
			if(tfNpassword.getText().equals(tfCNPassword.getText())) {
				Update();
			
			}else {
				JOptionPane.showMessageDialog(null, "Your passwords do not match");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Sorry, your password or User Name was incorrect");
		
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void back(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("settings.fxml"));
		addMember.getChildren().setAll(pane);
	}
	 
	public void getuserinfo() throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			String sql = "Select User_Name,Password from User_Acount where User_Id = 1";
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			UserName=(rs.getString(1));
			Password=(rs.getString(2));
			
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
	public void Update() throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		try {
			String sql= ("UPDATE User_Acount SET User_Name=\'"+tfUsername.getText()+"\',Password=\'"+tfNpassword.getText()+"\' WHERE User_Id = 1");
			ps= con.prepareStatement(sql);
			ps.execute();
			System.out.println("Update ok");
			
			
		}
			catch(Exception e) {
				System.out.println(e);
			}
	}
}
