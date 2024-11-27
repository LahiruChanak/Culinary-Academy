package lk.ijse.culinaryacademy.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.UserBO;
import lk.ijse.culinaryacademy.bo.custom.impl.UserBOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;

public class LoginFormController {

    @FXML
    private AnchorPane adminLoginPane;

    @FXML
    private Text txtGreeting;

    @FXML
    private MFXTextField txtUsername;

    @FXML
    private MFXPasswordField txtPassword;


    UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);


    public void initialize() {
        setGreeting();
        txtUsername.requestFocus();
    }

    // ---------------------------- MAIN FUNCTIONS ----------------------------
    @FXML
    void btnLoginOnAction(ActionEvent event) throws Exception {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields.").show();
            return;
        }

        String errorMessage = isValid();
        if (errorMessage != null) {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return;
        }

        try {
            if (userBO.checkLoginCredential(username, password)) {
                UserBOImpl.userName = userBO.getUsrName(username);
                navigateToDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password. Please try again.").show();
            }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while checking login details: " + e.getMessage()).show();
            e.printStackTrace();
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


    // ---------------------------- OTHER METHODS ----------------------------
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


    // ---------------------------- ON ACTION ----------------------------
    @FXML
    void txUsernameOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    // ---------------------------- ON KEY RELEASED ----------------------------
    @FXML
    void txtUsernameOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event) {

    }


    // ---------------------------- VALIDATION ----------------------------
    public String isValid() {
        return null;
    }

}
