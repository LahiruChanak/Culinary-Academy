module lk.ijse.culinaryacademy {
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires com.jfoenix;
    requires javafx.controls;

    opens lk.ijse.culinaryacademy to javafx.graphics;
    opens lk.ijse.culinaryacademy.controller to javafx.fxml;

    exports lk.ijse.culinaryacademy;
    exports lk.ijse.culinaryacademy.controller to javafx.fxml;
}
