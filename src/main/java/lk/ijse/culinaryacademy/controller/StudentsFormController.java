package lk.ijse.culinaryacademy.controller;

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
import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.dto.StudentDTO;
import lk.ijse.culinaryacademy.entity.Enrolment;
import lk.ijse.culinaryacademy.entity.Payment;
import lk.ijse.culinaryacademy.util.Regex;
import lk.ijse.culinaryacademy.util.TextField;
import lk.ijse.culinaryacademy.view.tdm.StudentTm;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentsFormController {

    @FXML
    private TableView<StudentTm> tblStudent;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colEnrolledDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtEnrolledDate;

    @FXML
    private JFXTextField txtStudentId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSearch;

    private List<StudentDTO> studentList = new ArrayList<>();

    // Objects
    StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STUDENT);

    Enrolment enrolment = new Enrolment();
    Payment payment = new Payment();


    // --------------------------------- Initialize Method ---------------------------------
    public void initialize() throws Exception {
        loadNextStudentId();
        setEnrolledDate();
        this.studentList = getAllStudents();
        loadStudentTable();
        setCellValueFactory();
    }


    // -------------------------------- CRUD OPERATIONS --------------------------------
    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        String studentId = txtStudentId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String enrolledDateText = txtEnrolledDate.getText();

        Date enrolledDate;

        try {
            enrolledDate = Date.valueOf(enrolledDateText);
        } catch (IllegalAccessError e) {
            new Alert(Alert.AlertType.ERROR, "Incorrect Enrolled Date.").show();
            return;
        }

        StudentDTO dto = new StudentDTO(studentId, name, email, contact, address, enrolledDate, enrolment, payment);

        String errorMessage = isValid();

        if (errorMessage != null) {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return;
        }

        try {
            boolean isAdded = studentBO.addStudent(dto);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Added Successfully.").show();
                clearField();
                refreshTable();
                loadNextStudentId();
                setEnrolledDate();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String studentId = txtStudentId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String enrolledDateText = txtEnrolledDate.getText();

        Date enrolledDate;

        try {
            enrolledDate = Date.valueOf(enrolledDateText);
        } catch (IllegalAccessError e) {
            new Alert(Alert.AlertType.ERROR, "Incorrect Enrolled Date.").show();
            return;
        }

        StudentDTO dto = new StudentDTO(studentId, name, email, contact, address, enrolledDate, enrolment, payment);

        String errorMessage = isValid();

        if (errorMessage != null) {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return;
        }

        try {
            boolean isUpdated = studentBO.updateStudent(dto);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Updated Successfully.").show();
                clearField();
                refreshTable();
                loadNextStudentId();
                setEnrolledDate();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        String studentId = txtStudentId.getText();

        try {
            boolean isDeleted = studentBO.deleteStudent(studentId);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted Successfully.").show();
                clearField();
                refreshTable();
                loadNextStudentId();
                setEnrolledDate();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) throws Exception {
        clearField();
    }

    private List<StudentDTO> getAllStudents() {
        List<StudentDTO> studentList = null;
        try {
            studentList = studentBO.getAllStudents();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }


    // -------------------------------- OTHER METHODS --------------------------------
    private void clearField() throws Exception {
        txtStudentId.clear();
        txtName.clear();
        txtEmail.clear();
        txtContact.clear();
        txtAddress.clear();
        txtEnrolledDate.clear();

        loadNextStudentId();
        setEnrolledDate();
    }

    private void refreshTable() {
        this.studentList = getAllStudents();
        loadStudentTable();
    }

    @FXML
    private void txtSearchOnAction(ActionEvent event) throws Exception {
        String studentId = txtSearch.getText();

        try {
            StudentDTO dto = studentBO.searchByStudentId(studentId);

            if (dto != null) {
                txtStudentId.setText(dto.getStudentId());
                txtName.setText(dto.getName());
                txtEmail.setText(dto.getEmail());
                txtContact.setText(dto.getContact());
                txtAddress.setText(dto.getAddress());
                txtEnrolledDate.setText(dto.getEnrolledDate().toString());

                txtSearch.clear();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Student not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadNextStudentId() throws Exception {
        try {
            String currentId = studentBO.currentStudentId();
            String nextId = nextId(currentId);

            txtStudentId.setText(nextId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("S");
            int id = Integer.parseInt(split[1]);
            return "S" + String.format("%03d", ++id);
        }
        return "S001";
    }

    private void setEnrolledDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtEnrolledDate.setText(LocalDate.now().format(formatter));
    }

    private void loadStudentTable() {
        ObservableList<StudentTm> tmList = FXCollections.observableArrayList();

        for (StudentDTO dto : studentList) {
            StudentTm studentTm = new StudentTm(
                    dto.getStudentId(),
                    dto.getName(),
                    dto.getEmail(),
                    dto.getContact(),
                    dto.getAddress(),
                    dto.getEnrolledDate()
            );

            tmList.add(studentTm);
        }

        tblStudent.setItems(tmList);
        tblStudent.getSelectionModel().getSelectedItem();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEnrolledDate.setCellValueFactory(new PropertyValueFactory<>("enrolledDate"));
    }


    // -------------------------------- ON KEY RELEASED METHODS --------------------------------
    @FXML
    void txtStudentIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.STUDENTID, txtStudentId);
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
    void txtContactOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.CONTACT, txtContact);
    }

    @FXML
    void txtAddressOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.ADDRESS, txtAddress);
    }

    @FXML
    void txtEnrolledDateOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextField.DATE, txtEnrolledDate);
    }


    // -------------------------------- VALIDATION --------------------------------
    public String isValid() {
        String message = "";

        if (!Regex.setTextColor(TextField.STUDENTID, txtStudentId))
            message += "Student ID must be starts with 'S' and exactly three digits.\n\n";

        if (!Regex.setTextColor(TextField.NAME, txtName))
            message += "Name must be at least 3 letters.\n\n";

        if (!Regex.setTextColor(TextField.EMAIL, txtEmail))
            message += "Enter valid email address.\n\n";

        if (!Regex.setTextColor(TextField.CONTACT, txtContact))
            message += "Contact must be starts with either +94, or 07\n\n";

        if (!Regex.setTextColor(TextField.ADDRESS, txtAddress))
            message += "Address must be at least 4 characters long.\n\n";

        if (!Regex.setTextColor(TextField.DATE, txtEnrolledDate))
            message += "Enter valid date (yyyy-MM-dd).\n\n";

        return message.isEmpty() ? null : message;
    }

}
