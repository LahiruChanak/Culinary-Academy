package lk.ijse.culinaryacademy.controller;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.UserBO;
import lk.ijse.culinaryacademy.dto.UserDTO;
import lk.ijse.culinaryacademy.util.CustomException;
import lk.ijse.culinaryacademy.util.Regex;
import lk.ijse.culinaryacademy.util.TextField;
import lk.ijse.culinaryacademy.view.tdm.UserTm;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFormController {

    @FXML
    private MFXComboBox<String> cmbRole;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private MFXPasswordField txtConfirmPassword;

    @FXML
    private MFXTextField txtEmail;

    @FXML
    private MFXTextField txtName;

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;

    @FXML
    private MFXTextField txtSearch;

    private List<UserDTO> userList = new ArrayList<>();

    // Objects
    UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);


    // ---------------------------- Initialize Method ----------------------------
    @FXML
    void initialize() throws Exception {
        loadRoles();
        this.userList = getAllUsers();
        loadUserTable();
        setCellValueFactory();
    }


    // ---------------------------- CRUD OPERATIONS ----------------------------
    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        String userId = txtUsername.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String role = cmbRole.getValue();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (!password.equals(confirmPassword)) {
            throw new CustomException("Password Mismatched.");
        }

        String errorMessage = isValid();

        if (errorMessage != null) {
            throw new CustomException(errorMessage);
        }

        // Hash the password before creating the UserDTO
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        UserDTO dto = new UserDTO(userId, name, email, role, hashedPassword);

        try {
            boolean isAdded = userBO.addUser(dto);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Added Successfully.").show();
                clearField();
                refreshTable();
            }
        } catch (SQLException e) {
            throw new CustomException(e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String userId = txtUsername.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String role = cmbRole.getValue();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (!password.equals(confirmPassword)) {
            throw new CustomException("Password Mismatched.");
        }

        UserDTO dto = new UserDTO(userId, name, email, role);  // Pass the hashed password

        String errorMessage = isValidUpdate();

        if (errorMessage != null) {
            throw new CustomException(errorMessage);
        }

        try {
            boolean isUpdated = userBO.updateUser(dto);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "User Updated Successfully.").show();
                clearField();
                refreshTable();
            }
        } catch (SQLException e) {
            throw new CustomException(e.getMessage());
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        String userId = txtUsername.getText();

        try {
            boolean isDeleted = userBO.deleteUser(userId);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted Successfully.").show();
                clearField();
                refreshTable();
            }
        } catch (SQLException e) {
            throw new CustomException(e.getMessage());
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) throws Exception {
        clearField();
    }

    private List<UserDTO> getAllUsers() {
        List<UserDTO> userList = null;
        try {
            userList = userBO.getAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userList;
    }


    // ---------------------------- OTHER OPERATIONS ----------------------------
    private void clearField() throws Exception {
        txtUsername.clear();
        txtName.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();

        txtPassword.setDisable(false);
        txtConfirmPassword.setDisable(false);
    }

    private void refreshTable() {
        this.userList = getAllUsers();
        loadUserTable();
    }

    @FXML
    private void txtSearchOnAction(ActionEvent event) throws Exception {
        String username = txtSearch.getText();

        try {
            UserDTO dto = userBO.searchByUsername(username);

            if (dto == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Search Result");
                alert.setHeaderText("Search Field Empty");
                alert.setContentText("Please enter a Username to search.");
                alert.showAndWait();
                return;
            }

            if (dto != null) {
                txtUsername.setText(dto.getUsername());
                txtName.setText(dto.getName());
                txtEmail.setText(dto.getEmail());
                cmbRole.setValue(dto.getRole());

                txtPassword.setDisable(true);
                txtConfirmPassword.setDisable(true);

                txtSearch.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Result");
                alert.setHeaderText("User Not Found");
                alert.setContentText("No user with ID " + username + " was found.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            throw new CustomException("User Not Found in the Database");
        }
    }


    private void loadUserTable() {
        ObservableList<UserTm> tmList = FXCollections.observableArrayList();

        for (UserDTO dto : userList) {
            UserTm userTm = new UserTm(
                    dto.getUsername(),
                    dto.getName(),
                    dto.getEmail(),
                    dto.getRole()
            );

            tmList.add(userTm);
        }

        tblUser.setItems(tmList);
        tblUser.getSelectionModel().getSelectedItem();
    }

    private void setCellValueFactory() {
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    private void loadRoles() {
        ObservableList<String> roles = FXCollections.observableArrayList("Coordinator");
        cmbRole.setItems(roles);
    }


    // ---------------------------- ON KEY RELEASE ----------------------------
    @FXML
    void txtConfirmPasswordOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.PASSWORD, txtConfirmPassword);
    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.EMAIL, txtEmail);
    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.NAME, txtName);
    }

    @FXML
    void txtUsernameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.USERNAME, txtUsername);
    }

    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.PASSWORD, txtPassword);
    }

    // ---------------------------- VALIDATION ----------------------------
    public String isValid() {
        String message = "";

        if (!Regex.setTextColor(TextField.USERNAME, txtUsername))
            message += "Username must be between 3 and 16 characters long.\n\n";

        if (!Regex.setTextColor(TextField.NAME, txtName))
            message += "Name must be at least 3 letters.\n\n";

        if (!Regex.setTextColor(TextField.EMAIL, txtEmail))
            message += "Enter valid email address.\n\n";

        if (!Regex.setTextColor(TextField.PASSWORD, txtPassword))
            message += """
                    Please enter password following type,
                    \t* Contains at least one alphabetic character and one digit.
                    \t* Include special characters such as @$!%*?&.
                    \t* Password at least 8 characters long.""";

        return message.isEmpty() ? null : message;
    }

    public String isValidUpdate() {
        String message = "";

        if (!Regex.setTextColor(TextField.USERNAME, txtUsername))
            message += "Username must be between 3 and 16 characters long.\n\n";

        if (!Regex.setTextColor(TextField.NAME, txtName))
            message += "Name must be at least 3 letters.\n\n";

        if (!Regex.setTextColor(TextField.EMAIL, txtEmail))
            message += "Enter valid email address.\n\n";

        return message.isEmpty() ? null : message;
    }
}