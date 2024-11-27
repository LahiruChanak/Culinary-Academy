package lk.ijse.culinaryacademy.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.UserBO;
import lk.ijse.culinaryacademy.util.Regex;
import lk.ijse.culinaryacademy.util.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class RegisterFormController {

    @FXML
    private AnchorPane adminRegisterPane;

    @FXML
    private MFXTextField txtUsername;

    @FXML
    private MFXTextField txtEmail; ;

    @FXML
    private MFXPasswordField txtConfirmPassword;

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtName;


    // Objects
    UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);


    // --------------------------------- MAIN FUNCTIONS ---------------------------------
    @FXML
    void btnSignUpOnAction(ActionEvent event) throws Exception {
        String username = txtUsername.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        String role = "Admin";

        String errorMessage = isValid();

        if (errorMessage != null) {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return;
        }

        try {
            boolean isTrue = userBO.checkRegisterCredential(username, name, email, password, confirmPassword, role);
            if (isTrue) {
                new Alert(Alert.AlertType.INFORMATION, "Registration Successfully.").show();
                clearField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Incorrect Register Details.").show();
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/loginForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        adminRegisterPane.getChildren().clear();
        adminRegisterPane.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), adminRegisterPane);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }


    // --------------------------------- OTHER METHODS ---------------------------------
    private void clearField() {
        txtUsername.clear();
        txtName.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
    }


    // --------------------------------- ON ACTION ---------------------------------
    @FXML
    void txUsernameOnAction(ActionEvent event) { txtName.requestFocus(); }

    @FXML
    void txtNameOnAction(ActionEvent event) { txtEmail.requestFocus(); }

    @FXML
    void txtEmailOnAction(ActionEvent event) { txtPassword.requestFocus(); }

    @FXML
    void txtPasswordOnAction(ActionEvent event) { txtConfirmPassword.requestFocus(); }

    @FXML
    void txtConfirmPasswordOnAction(ActionEvent event) throws Exception { btnSignUpOnAction(event); }


    // --------------------------------- ON KEY RELEASED ---------------------------------
    @FXML
    void txtUsernameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.USERNAME, txtUsername);
    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.NAME, txtName);
    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.EMAIL, txtEmail);
    }

    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.PASSWORD, txtPassword);
    }

    @FXML
    void txtConfirmPasswordOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.PASSWORD, txtConfirmPassword);
    }


    // --------------------------------- VALIDATION ---------------------------------
    public String isValid() {
        String message = "";

        if (!Regex.setTextColor(TextField.USERNAME,txtUsername))
            message += "Username must be between 3 and 16 characters long.\n\n";

        if (!Regex.setTextColor(TextField.NAME,txtName))
            message += "Name must be at least 3 letters.\n\n";

        if (!Regex.setTextColor(TextField.EMAIL,txtEmail))
            message += "Enter valid email address.\n\n";

        if (!Regex.setTextColor(TextField.PASSWORD,txtPassword))
            message += """
                    Please enter password following type,
                    \t* Contains at least one alphabetic character and one digit.
                    \t* Include special characters such as @$!%*?&.
                    \t* Password at least 8 characters long.""";

        return message.isEmpty() ? null : message;
    }
}
