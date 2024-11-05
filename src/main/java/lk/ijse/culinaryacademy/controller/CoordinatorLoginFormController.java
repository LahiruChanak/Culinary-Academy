package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class CoordinatorLoginFormController {
    @FXML
    private AnchorPane coordinatorLoginPane;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private Hyperlink linkAdmin;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();

//        try {
//            if (credentialBO.checkLoginCredential(email, password)) {
//                CredentialBOImpl.userName = credentialBO.getUsrName(email);
//                navigateToDashboard();
//            }
//        } catch (SQLException | IOException e) {
//            new Alert(Alert.AlertType.ERROR, "An error occurred while checking login details.").show();
//        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/coordinatorRegisterForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        coordinatorLoginPane.getChildren().clear();
        coordinatorLoginPane.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), coordinatorLoginPane);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void linkAdminOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/adminLoginForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        coordinatorLoginPane.getChildren().clear();
        coordinatorLoginPane.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), coordinatorLoginPane);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    private void navigateToDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.coordinatorLoginPane.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtPWOnAction(ActionEvent event) {

    }

    @FXML
    void txtPWOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtUsernameOnKeyReleased(KeyEvent event) {

    }

}
