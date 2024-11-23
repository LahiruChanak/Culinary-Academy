module lk.ijse.culinaryacademy {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires com.jfoenix;
    requires static lombok;

    opens lk.ijse.culinaryacademy to javafx.fxml, javafx.graphics;
    opens lk.ijse.culinaryacademy.controller to javafx.fxml;

    exports lk.ijse.culinaryacademy;
    opens lk.ijse.culinaryacademy.bo.custom to javafx.fxml;
}
