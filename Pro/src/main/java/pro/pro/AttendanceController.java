package pro.pro;

import Model.Attendance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable {
    @FXML
    private VBox attendanceLayout;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private TextField txtName;

    @FXML
    void addAttendanceOnAction(ActionEvent event) {


        loadToDbOnBtnClick();
        loadOnBtnClick();


    }
    List<Attendance> ls;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String time = dtf.format(now);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ls = new ArrayList<>();
        loadFromDb();


    }



    private void loadFromDb(){
        Connection con = null;

        try {
            con = Dbconnecter.connect();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Attendance");
            //stmt.setInt(1, Integer.parseInt(txtName.getText()));

            
            ResultSet res = stmt.executeQuery();
            //ResultSetMetaData rsmd = res.getMetaData();
            //int columnsNumber = rsmd.getColumnCount();
            while (res.next()) {
                //packs = new ArrayList<>();
                //private List<Attendance> attendance() {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                Attendance attendance = new Attendance();
                attendance.setName(res.getString("attendanceName"));
                attendance.setRegisterNumber(res.getString("attendanceReg"));
                attendance.setTime(res.getString("attendanceTime"));
                attendance.setImgSrc("Back.png");
                ls.add(attendance);

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("attendanceItem.fxml"));

                try {
                    HBox hBox = fxmlLoader.load();
                    AttendanceItemController cic = fxmlLoader.getController();
                    cic.setData (attendance);
                    attendanceLayout.getChildren().add(0, hBox);
                } catch (Exception e) {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }

            }

            System.out.println("Update ok " + res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    private void loadToDbOnBtnClick(){
        Connection con = null;

        try {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);

            con = Dbconnecter.connect();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Attendance(attendanceName, attendanceReg,attendanceTime)" +
                    "SELECT Name, Reg,? FROM Member WHERE Reg = ?");
            stmt.setString(1, date);
            stmt.setInt(2, Integer.parseInt(txtName.getText()));

            stmt.executeUpdate();

            System.out.println("Update ok " );
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    private void loadOnBtnClick() {
        Connection con = null;

        try {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            con = Dbconnecter.connect();
            PreparedStatement stmt = con.prepareStatement("SELECT Name, Reg FROM Member WHERE Reg = ?");
            //SELECT Name, Reg,? FROM Member WHERE Reg = ?

            stmt.setInt(1, Integer.parseInt(txtName.getText()));

            ResultSet res = stmt.executeQuery();



            while (res.next()) {

                //packs = new ArrayList<>();
                //private List<Attendance> attendance() {
                DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now1 = LocalDateTime.now();

                Attendance attendance = new Attendance();
                attendance.setName(res.getString("Name"));
                attendance.setRegisterNumber(res.getString("Reg"));
                attendance.setTime(dtf1.format(now1));
                attendance.setImgSrc("Back.png");
                ls.add(attendance);

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("attendanceItem.fxml"));

                try {
                    HBox hBox = fxmlLoader.load();
                    AttendanceItemController cic = fxmlLoader.getController();
                    cic.setData(attendance);
                    attendanceLayout.getChildren().add(0, hBox);
                } catch (Exception e) {
                    System.out.println("ep"+e);
                    throw new RuntimeException(e);
                }

            }


            System.out.println("Update ok ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException ex) {

            }
        }
    }



    }