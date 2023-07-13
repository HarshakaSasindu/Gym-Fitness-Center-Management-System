package pro.pro;

import Model.Attendance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.*;

public class AttendanceItemController implements Initializable {

    @FXML
    private Button btnNotPaid;

    @FXML
    private Button btnPaid;
    @FXML
    private HBox hBoxMain;

    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Label regNo;

    @FXML
    private Label time;

    public String regNo1;

    public AttendanceItemController() throws ParseException {
    }

    @FXML
    void showInformation(ActionEvent event) {

    }

    @FXML
    void showPayments(ActionEvent event) {

    }

    public  void setData (Attendance attendance){


        name.setText(attendance.getName());
        //regNo.setText(String.valueOf(attendance.getRegisterNumber()));
        time.setText(attendance.getTime());
        regNo.setText(attendance.getRegisterNumber());
        regNo1 = (String.valueOf(attendance.getRegisterNumber()));
        Image imgProfile = new Image(getClass().getResourceAsStream(attendance.getImgSrc()));
        img.setImage(imgProfile);
        System.out.println(regNo1);


        //--------------------------------------------------------------------------------------------------------------------

        Connection con = null;
        try {
            con = Dbconnecter.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        String ExDate;
        try {
           // String sql = "Select EXDate from Payment  where Reg = ?";
            String sql = "Select MAX(EXDate) from Payment WHERE Reg = ?";
            ps = con.prepareStatement(sql);
            //ps.setString(1, regNo.getText());
            ps.setString(1, regNo.getText());
            rs = ps.executeQuery();
            ExDate = (rs.getString(1));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e);

            }
        }

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        try {
            d1 = sdformat.parse(String.valueOf(Today));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date d2 = null;
        try {
            d2 = sdformat.parse(ExDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (d1.compareTo(d2) > 0) {
            System.out.println("Date 1 occurs after Date 2");
            //hBoxMain.setBackground(new Background(new BackgroundFill(RED, CornerRadii.EMPTY, Insets.EMPTY)));
            //hBoxMain.setBackground(CHOCOLATE);
            btnNotPaid.setVisible(true);
        } else if (d1.compareTo(d2) < 0) {
            System.out.println("Date 1 occurs before Date 2");
        } else if (d1.compareTo(d2) == 0) {
            System.out.println("Both dates are equal");
        }

    }

    {
        System.out.println(regNo1);
    }
        public LocalDate Today = java.time.LocalDate.now();


  /*  {
        Connection con = null;
        try {
            con = Dbconnecter.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        String ExDate;
        try {
            String sql = "Select EXDate from Payment where Reg = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, regNo.getText());
            rs = ps.executeQuery();
            ExDate = (rs.getString("EXDate"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e);

            }
        }

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdformat.parse(String.valueOf(Today));
        Date d2 = sdformat.parse(ExDate);

        if (d1.compareTo(d2) > 0) {
            System.out.println("Date 1 occurs after Date 2");
        } else if (d1.compareTo(d2) < 0) {
            System.out.println("Date 1 occurs before Date 2");
        } else if (d1.compareTo(d2) == 0) {
            System.out.println("Both dates are equal");
        }

    }

   /* {
        Connection con = Dbconnecter.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String ExDate;
        try {
            String sql = "Select EXDate from Payment where Reg = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, regNo.getText());
            rs = ps.executeQuery();
            ExDate = (rs.getString("EXDate"));


        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e);

            }
        }

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdformat.parse(String.valueOf(Today));
        Date d2 = sdformat.parse(ExDate);

        if (d1.compareTo(d2) > 0) {
            System.out.println("Date 1 occurs after Date 2");
        } else if (d1.compareTo(d2) < 0) {
            System.out.println("Date 1 occurs before Date 2");
        } else if (d1.compareTo(d2) == 0) {
            System.out.println("Both dates are equal");
        }
    }

    */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     /*   Connection con = null;
        try {
            con = Dbconnecter.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        String ExDate;
        try {
            String sql = "Select EXDate from Payment where Reg = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, regNo.getText());
            rs = ps.executeQuery();
            ExDate = (rs.getString("EXDate"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e);

            }
        }

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        try {
            d1 = sdformat.parse(String.valueOf(Today));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date d2 = null;
        try {
            d2 = sdformat.parse(ExDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (d1.compareTo(d2) > 0) {
            System.out.println("Date 1 occurs after Date 2");
        } else if (d1.compareTo(d2) < 0) {
            System.out.println("Date 1 occurs before Date 2");
        } else if (d1.compareTo(d2) == 0) {
            System.out.println("Both dates are equal");
        }

      */


    }
}
