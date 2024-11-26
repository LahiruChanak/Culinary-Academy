package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import lk.ijse.culinaryacademy.dto.CourseDTO;
import lk.ijse.culinaryacademy.dto.UserDTO;
import lk.ijse.culinaryacademy.view.tdm.UserTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFormController {

    @FXML
    private JFXComboBox<String> cmbRole;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserId;

    @FXML
    private JFXTextField txtSearch;

    private List<UserDTO> userList = new ArrayList<>();

    // Objects
    UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);


    // ---------------------------- Initialize Method ----------------------------
    @FXML
    void initialize() throws Exception {
        loadNextUserId();
        this.userList = getAllUsers();
        loadUserTable();
        setCellValueFactory();
    }


    // ---------------------------- CRUD OPERATIONS ----------------------------
    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        String userId = txtUserId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String role = cmbRole.getValue();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        UserDTO dto = new UserDTO(userId, name, email, role, password);

        if (!password.equals(confirmPassword)) {
            new Alert(Alert.AlertType.ERROR, "Password Mismatched.").show();
            return;
        }

        String errorMessage = isValid();

        if (errorMessage != null) {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return;
        }

        try {
            boolean isAdded = userBO.addUser(dto);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Added Successfully.").show();
                clearField();
                refreshTable();
                loadNextUserId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String userId = txtUserId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String role = cmbRole.getValue();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        UserDTO dto = new UserDTO(userId, name, email, role, password);

        if (!password.equals(confirmPassword)) {
            new Alert(Alert.AlertType.ERROR, "Password Mismatched.").show();
            return;
        }

        String errorMessage = isValid();

        if (errorMessage != null) {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return;
        }

        try {
            boolean isAdded = userBO.updateUser(dto);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Updated Successfully.").show();
                clearField();
                refreshTable();
                loadNextUserId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        String userId = txtUserId.getText();

        try {
            boolean isDeleted = userBO.deleteUser(userId);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted Successfully.").show();
                clearField();
                refreshTable();
                loadNextUserId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
        txtUserId.clear();
        txtName.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();

        loadNextUserId();
    }

    private void refreshTable() {
        this.userList = getAllUsers();
        loadUserTable();
    }

    @FXML
    private void txtSearchOnAction(ActionEvent event) throws Exception {
        String userId = txtSearch.getText();

        try {
            UserDTO dto = userBO.searchByUserId(userId);

            if (dto != null) {
                txtUserId.setText(dto.getUserId());
                txtName.setText(dto.getName());
                txtEmail.setText(dto.getEmail());
                cmbRole.setValue(dto.getRole());

                txtSearch.clear();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "User not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadNextUserId() throws Exception {
        try {
            String currentId = userBO.currentUserId();
            String nextId = nextId(currentId);

            txtUserId.setText(nextId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
//        if (currentId != null) {
//            String[] split = currentId.split("U");
//            int id = Integer.parseInt(split[1]);
//            return "U" + String.format("%03d", ++id);
//        }
        return "U001";
    }

    private void loadUserTable() {
        ObservableList<UserTm> tmList = FXCollections.observableArrayList();

        for (UserDTO dto : userList) {
            UserTm userTm = new UserTm(
                    dto.getUserId(),
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
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }


    // ---------------------------- ON KEY RELEASE ----------------------------
    @FXML
    void cmbRoleOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtConfirmPasswordOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtUserIdOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event) {

    }

    // ---------------------------- VALIDATION ----------------------------
    public String isValid() {
        return null;
    }
}
