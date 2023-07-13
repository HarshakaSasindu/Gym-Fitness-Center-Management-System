package pro.pro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.TitledPane;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class reportController implements Initializable {
	@FXML
	private AnchorPane Report;
	@FXML
	private TitledPane tp11;
	@FXML
	private Button btnSave1;
	@FXML
	private Label LbTotal;
	@FXML
	private TitledPane tp111;
	@FXML
	private Button btnSave11;
	@FXML
	private Label lbTodayTotal;
	@FXML
	private Label lbTotalPay;
	public LocalDate Today= java.time.LocalDate.now();

	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			countMEM();
			SumTotal();
			SumTotalTODAY();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	// Event Listener on Button.onAction
	@FXML
	public void Download_MEM(ActionEvent event) throws JRException {
		MemberReportDown();
	}
	// Event Listener on Button.onAction
	@FXML
	public void Print_View_MEM(ActionEvent event) throws JRException {
		MemberReportView();
	}
	

	
	// Event Listener on Button.onAction
	@FXML
	public void Download_Pay(ActionEvent event) throws JRException {
		PaymentReportDown();
	}
	// Event Listener on Button.onAction
	@FXML
	public void Print_PAY(ActionEvent event) throws JRException {
		PaymentReportView();
	}
	public void MemberReportDown() throws JRException{
		Connection con;
		try {
			con = Dbconnecter.connect();
			String Srt =("SELECT * from Member");
			JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Nirmala Malshan\\Desktop\\Poject\\Me\\Pro\\src\\main\\resources\\pro\\pro\\MemberReport.jrxml");
			JRDesignQuery updateQuery =new JRDesignQuery();
			updateQuery.setText(Srt);
			jdesign.setQuery(updateQuery);
			JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
			JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
			//JasperViewer.viewReport(JasperPrint, false);
			JasperExportManager.exportReportToPdfFile(JasperPrint, "src\\main\\resources\\pro\\pro\\memreport\\Members-"+Today.toString()+".pdf" );
			
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select destination to save");
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
			fileChooser.setInitialFileName("Members-"+Today.toString());
			File memberFile = fileChooser.showSaveDialog(stage);
			if (memberFile != null) {
				Path path = Paths.get("src\\main\\resources\\pro\\pro\\memreport\\Members-"+Today.toString()+".pdf");
				try {
					Files.copy(path, memberFile.toPath());
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void MemberReportView() throws JRException{
		Connection con;
		try {
			con = Dbconnecter.connect();
			String Srt =("SELECT * from Member");
			JasperDesign jdesign = JRXmlLoader.load("src\\main\\resources\\pro\\pro\\MemberReport.jrxml");
			JRDesignQuery updateQuery =new JRDesignQuery();
			updateQuery.setText(Srt);
			jdesign.setQuery(updateQuery);
			JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
			JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
			JasperViewer.viewReport(JasperPrint, false);
			con.close();
			//JasperExportManager.exportReportToPdfFile(JasperPrint, "C:\\Users\\Nirmala Malshan\\Desktop\\Poject\\fg.pdf" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void PaymentReportView() throws JRException{
		Connection con;
		try {
			con = Dbconnecter.connect();
			String Srt =("SELECT * from Payment");
			JasperDesign jdesign = JRXmlLoader.load("src\\main\\resources\\pro\\pro\\PaymentsReports.jrxml");
			JRDesignQuery updateQuery =new JRDesignQuery();
			updateQuery.setText(Srt);
			jdesign.setQuery(updateQuery);
			JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
			JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
			JasperViewer.viewReport(JasperPrint, false);
		
			con.close();//JasperExportManager.exportReportToPdfFile(JasperPrint, "C:\\Users\\Nirmala Malshan\\Desktop\\Poject\\fg.pdf" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	public void PaymentReportDown() throws JRException{
		Connection con;
		try {
			con = Dbconnecter.connect();
			String Srt =("SELECT * from Payment");
			JasperDesign jdesign = JRXmlLoader.load("src\\main\\resources\\pro\\pro\\PaymentsReports.jrxml");
			JRDesignQuery updateQuery =new JRDesignQuery();
			updateQuery.setText(Srt);
			jdesign.setQuery(updateQuery);
			JasperReport Jreport =JasperCompileManager.compileReport(jdesign);
			JasperPrint JasperPrint =JasperFillManager.fillReport(Jreport,null,con);
			//JasperViewer.viewReport(JasperPrint, false);
			JasperExportManager.exportReportToPdfFile(JasperPrint, "src\\main\\resources\\pro\\pro\\payreport\\Payment-"+Today.toString()+".pdf" );
			
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select destination to save");
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
			fileChooser.setInitialFileName("Payment-"+Today.toString());
			File PaymentFile = fileChooser.showSaveDialog(stage);
			if (PaymentFile != null) {
				Path path = Paths.get("src\\main\\resources\\pro\\pro\\payreport\\Payment-"+Today.toString()+".pdf");
				try {
					Files.copy(path, PaymentFile.toPath());
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void countMEM() throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			String sql = "SELECT COUNT (Reg) FROM Member";
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			LbTotal.setText(rs.getString(1));
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
	public void SumTotal() throws SQLException {
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			String sql = "SELECT sum (Price) FROM Payment";
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			lbTotalPay.setText(rs.getString(1));
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
	public void SumTotalTODAY() throws SQLException {
		String to=Today.toString();
		Connection con= Dbconnecter.connect();
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			String sql = ("SELECT sum(Price) FROM Payment WHERE Payment.PayDate='"+to+"'");
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			lbTodayTotal.setText(rs.getString(1));
		
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
