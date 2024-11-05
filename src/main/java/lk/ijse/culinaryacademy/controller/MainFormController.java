package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class MainFormController {

    @FXML
    private JFXButton btnCourse;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnSettings;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnCoordinator;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Pane subPane;

    public void initialize() throws IOException {
        setButtonActive(btnHome);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashboardForm.fxml"));

        Pane registerPane = fxmlLoader.load();
        subPane.getChildren().clear();
        subPane.getChildren().add(registerPane);
    }

    @FXML
    void btnCourseOnAction(ActionEvent event) throws IOException {
        setButtonActive(btnCourse);

        URL resource = getClass().getResource("/view/coursesForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        subPane.getChildren().clear();
        subPane.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), subPane);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        setButtonActive(btnHome);

        URL resource = getClass().getResource("/view/dashboardForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        subPane.getChildren().clear();
        subPane.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), subPane);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnSettingsOnAction(ActionEvent event) throws IOException {
        setButtonActive(btnSettings);

        URL resource = getClass().getResource("/view/settingsForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        subPane.getChildren().clear();
        subPane.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), subPane);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        setButtonActive(btnStudent);

        URL resource = getClass().getResource("/view/studentsForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        subPane.getChildren().clear();
        subPane.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), subPane);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnCoordinatorOnAction(ActionEvent actionEvent) throws IOException {
        setButtonActive(btnCoordinator);

        URL resource = getClass().getResource("/view/coordinatorsForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        subPane.getChildren().clear();
        subPane.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), subPane);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/credentialForm.fxml"));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    private void setButtonActive(JFXButton button) {

        btnHome.getStyleClass().removeAll("jfx-active-button");
        btnCourse.getStyleClass().removeAll("jfx-active-button");
        btnStudent.getStyleClass().removeAll("jfx-active-button");
        btnSettings.getStyleClass().removeAll("jfx-active-button");
        btnLogOut.getStyleClass().removeAll("jfx-active-button");

        // Add Style
        button.getStyleClass().add("jfx-active-button");
    }


}
