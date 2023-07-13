module pro.pro {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires jasperreports;
	requires com.google.zxing;
	requires com.google.zxing.javase;
	requires java.mail;
	requires java.desktop;
	requires javafx.graphics;
   //requires controlsfx;
    requires org.controlsfx.controls;


    opens pro.pro to javafx.fxml;
    exports pro.pro;
}