package pro.pro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class memberPaymentController implements Initializable {
	@FXML
	private AnchorPane memPay;
	@FXML
	private TextField tfName;
	@FXML
	private TextField tfPDate;
	@FXML
	private ComboBox tfPack;
	@FXML
	private TextField tfPrice;
	@FXML
	private TextField tfReg;
	@FXML
	private Button btnClear;
	@FXML
	private Button btnAdd;
	@FXML
	private TextField tfSearch;
	
	public LocalDate Today= java.time.LocalDate.now();
	public String pk1;
	public String pk2;
	public String pk3;

	public LocalDate ExDate;
	public int Months;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			list();
			ObservableList<String> list= FXCollections.observableArrayList(pk1,pk2,pk3);
			tfPack.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	// Event Listener on Button[#btnClear].onAction
	@FXML
	public void Clear(ActionEvent event) throws SQLException {
		tfReg.setText(null);
		tfName.setText(null);
		tfPDate.setText(null);
		tfPrice.setText(null);
		tfSearch .setText(null);
		tfPack.getSelectionModel().clearSelection();

	}
	// Event Listener on Button[#btnAdd].onAction
	@FXML
	public void  Pay(ActionEvent event) throws SQLException {
		//public void Pay( EventHandler ) throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		try {
			String sql= "INSERT INTO Payment(Reg,PayDate,EXDate,Price) VALUES(?,?,?,?)";
			ps= con.prepareStatement(sql);
			ps.setString(1, tfReg.getText());
			ps.setString(2, tfPDate.getText());
			ps.setString(3, ExDate.toString());
			ps.setString(4, tfPrice.getText());

			ps.execute();
 			//Image img = new Image("src\\main\\resources\\pro\\pro\\Icon\\icons8-checkmark-200.png");
			//Image img = new Image("src\\main\\resources\\pro\\pro\\Icon\\icons8-checkmark-200.png");
			Notifications notification1 = Notifications.create()
					.title("Successful")
					.text("Payment Successful!")
					//.graphic(new ImageView(img))
					.hideAfter(Duration.seconds(5))
					.position(Pos.BOTTOM_RIGHT);
			notification1.show();


		}
			catch(Exception e) {
				System.out.println(e);
				Notifications notification = Notifications.create()
						.title("Not Successful")
						.text("Payment Not Successful Please Try Again")
						.hideAfter(Duration.seconds(5))
						.position(Pos.BOTTOM_RIGHT);
				notification.showError();



			}
	}
	// Event Listener on Button.onAction
	@FXML
	public void Search(ActionEvent event) throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			String sql = "Select Reg,Name from Member where Reg = ?";
			ps= con.prepareStatement(sql);
			ps.setString(1, tfSearch.getText());
			rs = ps.executeQuery();
			tfReg.setText(rs.getString(1));
			tfName.setText(rs.getString(2));
			tfPDate.setText(Today.toString());
			
			
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
	
	public void list() throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			String sql = "Select Name from Packege  where ID=1";
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			pk1 =(rs.getString(1));
			String sql2 = "Select Name from Packege  where ID=2";
			ps= con.prepareStatement(sql2);
			rs = ps.executeQuery();
			pk2 =(rs.getString(1));
			String sql3 = "Select Name from Packege  where ID=3";
			ps= con.prepareStatement(sql3);
			rs = ps.executeQuery();
			pk3 =(rs.getString(1));
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
	public void price() throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		ResultSet rs = null;
		if(pk1==tfPack.getValue()) {
			try {
				String sql = "Select Price,Period from Packege  where ID=1";
				ps= con.prepareStatement(sql);
				rs = ps.executeQuery();
				tfPrice.setText(rs.getString(1));
				Months=(Integer.parseInt(rs.getString(2)));
			}finally {
				try {
					rs.close();
					ps.close();
					con.close();
				}catch(SQLException e){
					System.out.println(e);	
				
			}
		}
		}else if(pk2==tfPack.getValue()) {
			try {
				String sql = "Select Price,Period from Packege  where ID=2";
				ps= con.prepareStatement(sql);
				rs = ps.executeQuery();
				tfPrice.setText(rs.getString(1));
				Months=(Integer.parseInt(rs.getString(2)));
			}finally {
				try {
					rs.close();
					ps.close();
					con.close();
				}catch(SQLException e){
					System.out.println(e);	
				
			}
		}
		}else if(pk3==tfPack.getValue()) {
			try {
				String sql = "Select Price,Period from Packege  where ID=3";
				ps= con.prepareStatement(sql);
				rs = ps.executeQuery();
				tfPrice.setText(rs.getString(1));
				Months=(Integer.parseInt(rs.getString(2)));
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
	}
	@FXML
	public void Selectpack(ActionEvent event) throws SQLException {
		price();
		calExDate();
	}
	public void calExDate() {
		ExDate= Today.plusMonths(Months);
	}


}
