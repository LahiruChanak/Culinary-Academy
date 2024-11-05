package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AdminRegisterFormController {

    @FXML
    private AnchorPane adminRegisterPane;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private JFXPasswordField txtConfirmPW;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/adminLoginForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        adminRegisterPane.getChildren().clear();
        adminRegisterPane.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), adminRegisterPane);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPW.getText();

//        try {
//            boolean isTrue = credentialBO.checkRegisterCredential(name, email, password, confirmPassword);
//            if (isTrue) {
//                new Alert(Alert.AlertType.INFORMATION, "Registration Successfully.").show();
//                clearField();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "Incorrect Register Details.").show();
//        }
    }

    private void clearField() {
        txtName.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtConfirmPW.clear();
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }



    @FXML
    void txtNameOnAction(ActionEvent event) {

    }


    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtConfirmPWOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtPWOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void setTxtConfirmPWOnKeyReleased(KeyEvent event) {

    }
}
