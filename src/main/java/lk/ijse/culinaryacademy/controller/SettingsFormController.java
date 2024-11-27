package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.UserBO;
import lk.ijse.culinaryacademy.util.Regex;
import lk.ijse.culinaryacademy.util.TextField;

import java.sql.SQLException;
import java.util.Optional;

public class SettingsFormController {

    @FXML
    private TitledPane titledPaneEmail;

    @FXML
    private TitledPane titledPanePassword;

    @FXML
    private JFXTextField txtCurrentEmail;

    @FXML
    private JFXTextField txtNewEmail;

    @FXML
    private JFXTextField txtConfirmEmail;

    @FXML
    private JFXPasswordField txtCurrentPassword;

    @FXML
    private JFXPasswordField txtNewPassword;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);


    // ------------------------------------ Initialize Method ------------------------------------
    @FXML
    public void initialize() {
        // Set the expanded property for both TitledPanes
        titledPaneEmail.setExpanded(false);
        titledPanePassword.setExpanded(false);

        // Add listeners to the expanded property of both TitledPanes
        titledPaneEmail.expandedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                titledPanePassword.setExpanded(false);
            }
        });

        titledPanePassword.expandedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                titledPaneEmail.setExpanded(false);
            }
        });
    }


    // ------------------------------------ EMAIL & PASSWORD CHANGE BUTTONS ------------------------------------
    @FXML
    void btnEmailChangeOnAction(ActionEvent event) {
        String currentEmail = txtCurrentEmail.getText();
        String newEmail = txtNewEmail.getText();
        String confirmEmail = txtConfirmEmail.getText();

        String errorMessage = isValid();

        if (errorMessage != null) {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return;
        }

        // Confirmation Alert
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Email Change");
        confirmationAlert.setHeaderText("Are you sure you want to change your email?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isAdded = userBO.changeEmail(currentEmail, newEmail, confirmEmail);

                if (isAdded) {
                    new Alert(Alert.AlertType.INFORMATION, "Email Changed Successfully.").show();
                    clearField();
                }
            } catch (IllegalArgumentException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "An error occurred while changing the email.").show();
                e.printStackTrace();
            }
        } else {
            // User chose CANCEL or closed the dialog
            new Alert(Alert.AlertType.INFORMATION, "Email change cancelled.").show();
        }
    }

    @FXML
    void btnPWChangeOnAction(ActionEvent event) throws Exception {
        String currentPassword = txtCurrentPassword.getText();
        String newPassword = txtNewPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        String errorMessage = isValid();

        if (errorMessage != null) {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return;
        }

        // Confirmation Alert
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Password Change");
        confirmationAlert.setHeaderText("Are you sure you want to change your password?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isAdded = userBO.changePassword(currentPassword, newPassword, confirmPassword);

                if (isAdded) {
                    new Alert(Alert.AlertType.INFORMATION, "Password Changed Successfully.").show();
                    clearField();
                }
            } catch (IllegalArgumentException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "An error occurred while changing the password.").show();
                e.printStackTrace();
            }
        } else {
            // User chose CANCEL or closed the dialog
            new Alert(Alert.AlertType.INFORMATION, "Password change cancelled.").show();
        }
    }


    // ------------------------------------ OTHER METHODS ------------------------------------
    private void clearField() {
        txtCurrentEmail.clear();
        txtNewEmail.clear();
        txtConfirmEmail.clear();
        txtCurrentPassword.clear();
        txtNewPassword.clear();
        txtConfirmPassword.clear();
    }

    // ------------------------------------ ON KEY RELEASE ------------------------------------
    @FXML
    void txtCurrentEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.EMAIL, txtCurrentEmail);
    }

    @FXML
    void txtNewEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.EMAIL, txtNewEmail);
    }

    @FXML
    void txtConfirmEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.EMAIL, txtConfirmEmail);
    }

    @FXML
    void txtCurrentPasswordOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.PASSWORD, txtCurrentPassword);
    }

    @FXML
    void txtNewPasswordOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.PASSWORD, txtNewPassword);
    }

    @FXML
    void txtConfirmPasswordOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.PASSWORD, txtConfirmPassword);
    }

    // ------------------------------------ VALIDATION ------------------------------------
    public String isValid() {
        String message = "";

        if (!Regex.setTextColor(TextField.EMAIL, txtCurrentEmail))
            message += "Enter valid email address.\n\n";

        if (!Regex.setTextColor(TextField.PASSWORD, txtCurrentPassword))
            message += """
                    Please enter password following type,
                    \t* Contains at least one alphabetic character and one digit.
                    \t* Include special characters such as @$!%*?&.
                    \t* Password at least 8 characters long.""";

        return message.isEmpty() ? null : message;
    }

}
