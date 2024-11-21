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
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.CredentialBO;
import lk.ijse.culinaryacademy.bo.custom.impl.CredentialBOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;

public class LoginFormController {

    @FXML
    private AnchorPane adminLoginPane;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private Hyperlink linkCoordinator;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private Text txtGreeting;

    @FXML
    private JFXPasswordField txtPassword;

    CredentialBO credentialBO = (CredentialBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CREDENTIAL);

    public void initialize() {
        setGreeting();
        txtEmail.requestFocus();
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        navigateToDashboard(); // This line should be removed after testing

        try {
            if (credentialBO.checkLoginCredential(email, password)) {
                CredentialBOImpl.userName = credentialBO.getUsrName(email);
                navigateToDashboard();
            }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while checking login details.").show();
        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/registerForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        adminLoginPane.getChildren().clear();
        adminLoginPane.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), adminLoginPane);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    private void navigateToDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/adminMainForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.adminLoginPane.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    private void setGreeting() {
        LocalTime time = LocalTime.now();
        String greeting = (time.getHour() < 12) ? "Good Morning !" : (time.getHour() < 16) ? "Good Afternoon !" : "Good Evening !";
        txtGreeting.setText(greeting);
    }

    @FXML
    void linkCoordinatorOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/coordinatorLoginForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        adminLoginPane.getChildren().clear();
        adminLoginPane.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), adminLoginPane);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
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
