module lk.ijse.culinaryacademy {
    requires javafx.fxml;
    requires javafx.controls;
    requires org.hibernate.orm.core;
    requires com.jfoenix;
    requires java.sql;
    requires static lombok;
    requires java.naming;
    requires java.persistence;

    opens lk.ijse.culinaryacademy to javafx.fxml, javafx.graphics;
    opens lk.ijse.culinaryacademy.controller to javafx.fxml;
    opens lk.ijse.culinaryacademy.entity to org.hibernate.orm.core;
    opens lk.ijse.culinaryacademy.view.tdm to javafx.base;

    exports lk.ijse.culinaryacademy;
    opens lk.ijse.culinaryacademy.bo.custom to javafx.fxml;
}
