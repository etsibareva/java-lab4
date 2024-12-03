module com.example.j2lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;
    requires org.xerial.sqlitejdbc;

    opens com.example.j2lab4 to javafx.fxml;
    exports com.example.j2lab4;
    exports com.example.j2lab4.controllers;
    opens com.example.j2lab4.controllers to javafx.fxml;
}