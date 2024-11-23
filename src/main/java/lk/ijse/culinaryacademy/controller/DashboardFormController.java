package lk.ijse.culinaryacademy.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {

    @FXML
    private Label lblCoordinatorsCount;

    @FXML
    private Label lblCoursesCount;

    @FXML
    private Label lblStudentsCount;

    @FXML
    private Text txtDate;

    @FXML
    private Text txtTime;

    @FXML
    private Circle userImage;

    @FXML
    private Label userName;


    public void initialize() {
        updateTime();
        updateDate();
    }

    private void updateTime() {
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            txtTime.setText(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
        }),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void updateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(formatter);
        txtDate.setText(formattedDate);
    }
}
