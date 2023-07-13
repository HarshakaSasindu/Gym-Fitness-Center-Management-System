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

import javafx.event.ActionEvent;

import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;

public class packegeController implements Initializable {
	@FXML
	private AnchorPane addMember;
	@FXML
	private VBox packageLayout;
	@FXML
	private TitledPane tp1;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnEdit;
	@FXML
	private TextField tfPName;
	@FXML
	private TextField tfPrice;
	@FXML
	private TextField tfVPeriod;
	@FXML
	private TitledPane tp11;
	@FXML
	private Button btnSave1;
	@FXML
	private Button btnEdit1;
	@FXML
	private TextField tfPName1;
	@FXML
	private TextField tfPrice1;
	@FXML
	private TextField tfVPeriod1;
	@FXML
	private TitledPane tp12;
	@FXML
	private Button btnSave2;
	@FXML
	private Button btnEdit2;
	@FXML
	private TextField tfPName2;
	@FXML
	private TextField tfPrice2;
	@FXML
	private TextField tfVPeriod2;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			pk1();
			pk2();
			pk3();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Event Listener on Button[#btnSave].onAction
	@FXML
	public void save(ActionEvent event) throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		try {
			String sql= ("UPDATE Packege SET Name=\'"+tfPName.getText()+"\',Price=\'"+tfPrice.getText()+"\',Period=\'"+tfVPeriod.getText()+"\' WHERE ID=1");
			ps= con.prepareStatement(sql);
			ps.execute();
			System.out.println("Update ok");
			btnSave.setVisible(false);
			tfPName.setEditable(false);
			tfPrice.setEditable(false);
			tfVPeriod.setEditable(false);
			btnEdit.setVisible(true);
			
		}
			catch(Exception e) {
				System.out.println(e);
			}
	}
	// Event Listener on Button[#btnEdit].onAction
	@FXML
	public void edit(ActionEvent event) {
		btnSave.setVisible(true);
		tfPName.setEditable(true);
		tfPrice.setEditable(true);
		tfVPeriod.setEditable(true);
		btnEdit.setVisible(false);
	}
	// Event Listener on Button[#btnSave1].onAction
	@FXML
	public void save1(ActionEvent event) throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		try {
			String sql= ("UPDATE Packege SET Name=\'"+tfPName1.getText()+"\',Price=\'"+tfPrice1.getText()+"\',Period=\'"+tfVPeriod1.getText()+"\' WHERE ID=2");
			ps= con.prepareStatement(sql);
			ps.execute();
			System.out.println("Update ok");
			btnSave1.setVisible(false);
			tfPName1.setEditable(false);
			tfPrice1.setEditable(false);
			tfVPeriod1.setEditable(false);
			btnEdit1.setVisible(true);
			
		}
			catch(Exception e) {
				System.out.println(e);
			}
	}
	// Event Listener on Button[#btnEdit1].onAction
	@FXML
	public void edit1(ActionEvent event) {
		btnSave1.setVisible(true);
		tfPName1.setEditable(true);
		tfPrice1.setEditable(true);
		tfVPeriod1.setEditable(true);
		btnEdit1.setVisible(false);
	}
	// Event Listener on Button[#btnSave2].onAction
	@FXML
	public void save2(ActionEvent event) throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		try {
			String sql= ("UPDATE Packege SET Name=\'"+tfPName2.getText()+"\',Price=\'"+tfPrice2.getText()+"\',Period=\'"+tfVPeriod2.getText()+"\' WHERE ID=3");
			ps= con.prepareStatement(sql);
			ps.execute();
			System.out.println("Update ok");
			btnSave2.setVisible(false);
			tfPName2.setEditable(false);
			tfPrice2.setEditable(false);
			tfVPeriod2.setEditable(false);
			btnEdit2.setVisible(true);
			
		}
			catch(Exception e) {
				System.out.println(e);
			}
	}
	// Event Listener on Button[#btnEdit2].onAction
	@FXML
	public void edit2(ActionEvent event) {
		btnSave2.setVisible(true);
		tfPName2.setEditable(true);
		tfPrice2.setEditable(true);
		tfVPeriod2.setEditable(true);
		btnEdit2.setVisible(false);
	}
	// Event Listener on Button.onAction
	@FXML
	public void backToSetting(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("settings.fxml"));
		addMember.getChildren().setAll(pane);
	}
	public void pk1() throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			String sql = "Select Name,Price,Period from Packege where ID = 1";
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			tfPName.setText(rs.getString(1));
			tp1.setText(rs.getString(1));
			tfPrice.setText(rs.getString(2));
			tfVPeriod.setText(rs.getString(3));
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
	public void pk2() throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			String sql = "Select Name,Price,Period from Packege where ID = 2";
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			tfPName1.setText(rs.getString(1));
			tp11.setText(rs.getString(1));
			tfPrice1.setText(rs.getString(2));
			tfVPeriod1.setText(rs.getString(3));
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
	public void pk3() throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			String sql = "Select Name,Price,Period from Packege where ID = 3";
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			tfPName2.setText(rs.getString(1));
			tp12.setText(rs.getString(1));
			tfPrice2.setText(rs.getString(2));
			tfVPeriod2.setText(rs.getString(3));
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
