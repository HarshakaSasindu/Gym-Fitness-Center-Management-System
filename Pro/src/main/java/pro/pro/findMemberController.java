package pro.pro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class findMemberController {
	@FXML
	private AnchorPane addMember;
	@FXML
	private TextField tfReg;
	@FXML
	private TextField tfName;
	@FXML
	private TextField tfAddress;
	@FXML
	private TextField tfTell;
	@FXML
	private TextField tfMail;
	@FXML
	private TextField tfGender;
	@FXML
	private TextField tfDOB;
	@FXML
	private TextField tfHeight;
	@FXML
	private TextField tfWeight;
	@FXML
	private TextField tfAim;
	@FXML
	private Button btnSave;

	@FXML
	private Button btnCancel;
	@FXML
	private TextField tfSearch;
	@FXML
	private ImageView ImageQR;

	@FXML
	private Button btnDietCancel;

	@FXML
	private Button btnDietSave;
	@FXML
	private Button btnSchedulSave;

	@FXML
	private Button btnScheduleCancel;

	@FXML
	private AnchorPane schedulePane;
	@FXML
	private TextArea txtAreaDiat;

	@FXML
	private TextArea txtAreaWorkOut;
	@FXML
	private Label lblDietPlanUpdate;

	@FXML
	private Label lblWorkoutLastUpdate;
	
	@FXML
	private Button btnRemove;

	// Event Listener on Button[#btnSave].onAction
	@FXML
	public void Save(ActionEvent event) throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		try {
			String sql= ("UPDATE Member SET Name=\'"+tfName.getText()+"\',Address=\'"+tfAddress.getText()+"\',Tell=\'"+tfTell.getText()+"\',Mail=\'"+tfMail.getText()+"\',Gender=\'"+tfGender.getText()+"\',DOB=\'"+tfDOB.getText()+"\',Hight=\'"+ tfHeight.getText()+"\',Weight=\'"+tfWeight.getText()+"\',Aim=\'"+tfAim.getText()+"\' WHERE Reg="+tfSearch.getText());
			ps= con.prepareStatement(sql);
			ps.execute();
			System.out.println("Update ok");
			btnSave.setVisible(false);
			btnCancel.setVisible(false);
			tfName.setEditable(false);
			tfAddress.setEditable(false);
			tfTell.setEditable(false);
			tfMail.setEditable(false);
			tfGender.setEditable(false);
			tfDOB.setEditable(false);
			tfHeight.setEditable(false);
			tfWeight.setEditable(false);
			tfAim.setEditable(false);
			btnRemove.setVisible(true);
		}
			catch(Exception e) {
				System.out.println(e);
			}
	}
	// Event Listener on Button.onAction
	@FXML
	public void payment(ActionEvent event) throws JRException {
		PaymentReport();
	}
	@FXML
	public void Remove(ActionEvent event) throws JRException {
		try {
			Connection con= Dbconnecter.connect();
			PreparedStatement ps =null;
			String sql= ("delete from Member where Reg = ?");
			ps= con.prepareStatement(sql);
			ps.setString(1, tfSearch.getText());
			ps.execute();
			ps.close();
			con.close();
		
			
		}catch(Exception e) {
			Notifications notification1 = Notifications.create()
					.title("Remove Status")
					.text("Can't remove this. Try again..!")
					.hideAfter(Duration.seconds(5))
					.position(Pos.BOTTOM_RIGHT);
			notification1.show();
		}
		
		try {
			Connection con= Dbconnecter.connect();
			PreparedStatement ps =null;
			String sql= ("delete from Fitness where Reg = ?");
			ps= con.prepareStatement(sql);
			ps.setString(1, tfSearch.getText());
			ps.execute();
			ps.close();
			con.close();
			
		}catch(Exception e) {
			Notifications notification1 = Notifications.create()
					.title("Remove Status")
					.text("Can't remove this. Try again..!")
					.hideAfter(Duration.seconds(5))
					.position(Pos.BOTTOM_RIGHT);
			notification1.show();
		}
		
		try {
			Connection con= Dbconnecter.connect();
			PreparedStatement ps =null;
			String sql= ("delete from Schedule where scheduleRegNo = ?");
			ps= con.prepareStatement(sql);
			ps.setString(1, tfSearch.getText());
			ps.execute();
			ps.close();
			con.close();
			Notifications notification1 = Notifications.create()
					.title("Remove Status")
					.text("Remove Successful..!")
					.hideAfter(Duration.seconds(5))
					.position(Pos.BOTTOM_RIGHT);
			notification1.show();
			
		}catch(Exception e) {
			Notifications notification1 = Notifications.create()
					.title("Remove Status")
					.text("Can't remove this. Try again..!")
					.hideAfter(Duration.seconds(5))
					.position(Pos.BOTTOM_RIGHT);
			notification1.show();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void Edit(ActionEvent event) {
		btnSave.setVisible(true);
		btnCancel.setVisible(true);
		tfName.setEditable(true);
		tfAddress.setEditable(true);
		tfTell.setEditable(true);
		tfMail.setEditable(true);
		tfGender.setEditable(true);
		tfDOB.setEditable(true);
		tfHeight.setEditable(true);
		tfWeight.setEditable(true);
		tfAim.setEditable(true);
		btnRemove.setVisible(false);
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void Search(ActionEvent event) throws SQLException {
		
		try {
			Connection con= Dbconnecter.connect();
			PreparedStatement ps =null;
			ResultSet rs = null;
			String sql = "Select Reg,Name,Address,Tell,Mail,Gender,DOB,Hight,Weight,Aim from Member where Reg = ?";
			ps= con.prepareStatement(sql);
			ps.setString(1, tfSearch.getText());
			
			rs = ps.executeQuery();
			
			tfReg.setText(rs.getString(1));
			tfName.setText(rs.getString(2));
			tfAddress.setText(rs.getString(3));
			tfTell.setText(rs.getString(4));
			tfMail.setText(rs.getString(5));
			tfGender.setText(rs.getString(6));
			tfDOB.setText(rs.getString(7));
			tfHeight.setText(rs.getString(8));
			tfWeight.setText(rs.getString(9));
			tfAim.setText(rs.getString(10));
			Image QR =new Image(getClass().getResourceAsStream("QR/"+tfSearch.getText()+".jpg"));
			ImageQR.setImage(QR);
			rs.close();
			ps.close();
			con.close();
			}catch(SQLException e){
				System.out.println(e);
			
		}
	

		try {
			Connection con= Dbconnecter.connect();
			PreparedStatement ps =null;
			ResultSet rs = null;
			String sql = "SELECT * FROM Schedule WHERE scheduleRegNo = ?";
			ps= con.prepareStatement(sql);
			ps.setString(1, tfSearch.getText());

			rs = ps.executeQuery();

			lblWorkoutLastUpdate.setText(rs.getString("workoutScheduleDate"));
			txtAreaWorkOut.setText(rs.getString("workoutSchedule"));
			lblDietPlanUpdate.setText(rs.getString("dietPlanDate"));
			txtAreaDiat.setText(rs.getString("dietPlan"));
			rs.close();
			ps.close();
			con.close();
			}catch(SQLException e){
				System.out.println(e);

			}
		}


	
	// Event Listener on Button.onAction
	@FXML
	public void Schedule(ActionEvent event) {
		// TODO Autogenerated
		schedulePane.setVisible(true);
		btnRemove.setVisible(false);
	}
	@FXML
	void back(ActionEvent event) {
		schedulePane.setVisible(false);
		btnRemove.setVisible(true);
	}

	// Event Listener on Button.onAction
	@FXML
	public void cancel(ActionEvent event) {
		// TODO Autogenerated
		btnSave.setVisible(false);
		btnCancel.setVisible(false);
		tfName.setEditable(false);
		tfAddress.setEditable(false);
		tfTell.setEditable(false);
		tfMail.setEditable(false);
		tfGender.setEditable(false);
		tfDOB.setEditable(false);
		tfHeight.setEditable(false);
		tfWeight.setEditable(false);
		tfAim.setEditable(false);
		btnRemove.setVisible(true);
	}
	public void PaymentReport() throws JRException{
		
		try {
			Connection con;
			con = Dbconnecter.connect();
			String Srt =("SELECT * from Member,Payment where Member.Reg ="+ tfReg.getText() +" AND Payment.Reg="+ tfReg.getText());
			JasperDesign jdesign = JRXmlLoader.load("src\\main\\resources\\pro\\pro\\MemPaments.jrxml");
			JRDesignQuery updateQuery =new JRDesignQuery();
			updateQuery.setText(Srt);
			jdesign.setQuery(updateQuery);
			JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
			JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
			JasperViewer.viewReport(JasperPrint, false);
			con.close();
			//JasperExportManager.exportReportToPdfFile(JasperPrint, "C:\\Users\\Nirmala Malshan\\Desktop\\Poject\\"+tfReg.getText()+".pdf" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@FXML
	void btnDietCancelOnAction(ActionEvent event) {
		btnDietCancel.setVisible(false);
		btnDietSave.setVisible(false);
	}

	@FXML
	void btnDietEditOnAction(ActionEvent event) {
		btnDietCancel.setVisible(true);
		btnDietSave.setVisible(true);
		

	}

	@FXML
	void btnDietSaveOnAction(ActionEvent event) {
		btnDietCancel.setVisible(false);
		btnDietSave.setVisible(false);
	

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		//String date = dtf.format(now);

		Connection con = null;

		try {
			con = Dbconnecter.connect();//DatabaseConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE Schedule SET dietPlan=?,dietPlanDate=? WHERE scheduleRegNo = ?");

			stmt.setString(1,txtAreaDiat.getText());
			stmt.setString(2,dtf.format(now));
			stmt.setString(3,tfSearch.getText());

			stmt.executeUpdate();

			System.out.println("Update done " );
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
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

	@FXML

	void btnScheduleCancelOnAction(ActionEvent event) {
		btnSchedulSave.setVisible(false);
		btnScheduleCancel.setVisible(false);

	}

	@FXML
	void btnSchedulSaveOnAction(ActionEvent event) {
		btnSchedulSave.setVisible(false);
		btnScheduleCancel.setVisible(false);
	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		//String date = dtf.format(now);

		Connection con = null;

		try {
			//con = Dbconnecter.connect();
			con = Dbconnecter.connect();//DatabaseConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE Schedule SET workoutSchedule=?,workoutScheduleDate=? WHERE scheduleRegNo = ?");

			stmt.setString(1,txtAreaWorkOut.getText());
			stmt.setString(2,dtf.format(now));
			stmt.setString(3,tfSearch.getText());

			stmt.executeUpdate();

			System.out.println("Update done " );
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
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

	@FXML
	void btnScheduleEditOnAction(ActionEvent event) {
		btnSchedulSave.setVisible(true);
		btnScheduleCancel.setVisible(true);


	}
}
