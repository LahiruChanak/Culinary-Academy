package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.text.Text;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.CourseBO;
import lk.ijse.culinaryacademy.bo.custom.EnrolmentBO;
import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.dto.EnrolmentDTO;
import lk.ijse.culinaryacademy.entity.Course;
import lk.ijse.culinaryacademy.entity.Student;
import lk.ijse.culinaryacademy.view.tdm.EnrolmentTm;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EnrolmentFormController {

    @FXML
    private JFXComboBox<?> cmbCourseId;

    @FXML
    private JFXComboBox<?> cmbStudentId;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colEnrolledDate;

    @FXML
    private TableColumn<?, ?> colEnrolmentId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableView<EnrolmentTm> tblEnrolment;

    @FXML
    private Text txtCourseName;

    @FXML
    private JFXTextField txtEnrolledDate;

    @FXML
    private JFXTextField txtEnrolmentId;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private Text txtStudentName;

    private List<EnrolmentDTO> enrolmentList = new ArrayList<>();

    // Objects
    EnrolmentBO enrolmentBO = (EnrolmentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ENROLMENT);
    CourseBO courseBO = (CourseBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.COURSE);
    StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STUDENT);

    Student student = new Student();
    Course course = new Course();

    // ---------------------------------------- Initialize Method ----------------------------------------
    @FXML
    void initialize() throws Exception {
        loadNextEnrolmentId();
        setEnrolledDate();
        this.enrolmentList = getAllEnrolments();
        loadEnrolmentTable();
        setCellValueFactory();
    }


    // ---------------------------------------- CRUD OPERATIONS ----------------------------------------
    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        String enrolmentId = txtEnrolmentId.getText();
        String studentId = cmbStudentId.getSelectionModel().getSelectedItem().toString();
        String courseId = cmbCourseId.getSelectionModel().getSelectedItem().toString();
        String enrolledDateText = txtEnrolledDate.getText();

        Date enrolledDate;

        try {
            enrolledDate = Date.valueOf(enrolledDateText);
        } catch (IllegalAccessError e) {
            new Alert(Alert.AlertType.ERROR, "Incorrect Enrolled Date.").show();
            return;
        }

        EnrolmentDTO dto = new EnrolmentDTO(enrolmentId, studentId, courseId, enrolledDate);

        String errorMessage = isValid();

        if (errorMessage != null) {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return;
        }

        try {
            boolean isAdded = enrolmentBO.addEnrolment(dto);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Enrolment Added Successfully.").show();
                clearField();
                refreshTable();
                loadNextEnrolmentId();
                setEnrolledDate();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String enrolmentId = txtEnrolmentId.getText();
        String studentId = cmbStudentId.getSelectionModel().getSelectedItem().toString();
        String courseId = cmbCourseId.getSelectionModel().getSelectedItem().toString();
        String enrolledDateText = txtEnrolledDate.getText();

        Date enrolledDate;

        try {
            enrolledDate = Date.valueOf(enrolledDateText);
        } catch (IllegalAccessError e) {
            new Alert(Alert.AlertType.ERROR, "Incorrect Enrolled Date.").show();
            return;
        }

        EnrolmentDTO dto = new EnrolmentDTO(enrolmentId, studentId, courseId, enrolledDate);

        String errorMessage = isValid();

        if (errorMessage != null) {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
            return;
        }

        try {
            boolean isAdded = enrolmentBO.updateEnrolment(dto);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Enrolment Updated Successfully.").show();
                clearField();
                refreshTable();
                loadNextEnrolmentId();
                setEnrolledDate();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        String enrolmentId = txtEnrolmentId.getText();

        try {
            boolean isDeleted = enrolmentBO.deleteEnrolment(enrolmentId);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Enrolment Deleted Successfully.").show();
                clearField();
                refreshTable();
                loadNextEnrolmentId();
                setEnrolledDate();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearField();
    }

    private List<EnrolmentDTO> getAllEnrolments() {
        List<EnrolmentDTO> enrolmentsList = null;
        try {
            enrolmentsList = enrolmentBO.getAllEnrolments();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return enrolmentsList;
    }

    // ---------------------------------------- OTHER METHODS ----------------------------------------
    private void clearField() {
        txtEnrolmentId.clear();
        cmbStudentId.getSelectionModel().clearSelection();
        cmbCourseId.getSelectionModel().clearSelection();
        txtEnrolledDate.clear();
    }

    private void refreshTable() {
        this.enrolmentList = getAllEnrolments();
        loadEnrolmentTable();
    }

    @FXML
    private void txtSearchOnAction(ActionEvent event) throws Exception {
        String enrolmentId = txtSearch.getText();

        try {
            EnrolmentDTO dto = enrolmentBO.searchByEnrolmentId(enrolmentId);

            if (dto != null) {
                txtEnrolmentId.setText(dto.getEnrolmentId());
                cmbStudentId.getSelectionModel().select(Integer.parseInt(dto.getStudentId()));
                cmbCourseId.getSelectionModel().select(Integer.parseInt(dto.getCourseId()));
                txtEnrolledDate.setText(dto.getEnrolledDate().toString());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Enrolment not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadNextEnrolmentId() throws Exception {
        try {
            String currentId = enrolmentBO.currentEnrolmentId();
            String nextId = nextId(currentId);

            txtEnrolmentId.setText(nextId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
//        if (currentId != null) {
//            String[] split = currentId.split("E");
//            int id = Integer.parseInt(split[1]);
//            return "E" + String.format("%03d", ++id);
//        }
        return "E001";
    }

    private void setEnrolledDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtEnrolledDate.setText(LocalDate.now().format(formatter));
    }

    private void loadEnrolmentTable() {
        ObservableList<EnrolmentTm> tmList = FXCollections.observableArrayList();

        for (EnrolmentDTO dto : enrolmentList) {
            EnrolmentTm enrolmentTm = new EnrolmentTm(
                    dto.getEnrolmentId(),
                    dto.getStudentId(),
                    dto.getStudentName(),
                    dto.getCourseId(),
                    dto.getCourseName(),
                    dto.getEnrolledDate()
            );

            tmList.add(enrolmentTm);
        }

        tblEnrolment.setItems(tmList);
        tblEnrolment.getSelectionModel().getSelectedItem();
    }

    private void setCellValueFactory() {
        colEnrolmentId.setCellValueFactory(new PropertyValueFactory<>("enrolmentId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colEnrolledDate.setCellValueFactory(new PropertyValueFactory<>("enrolledDate"));
    }


    // ---------------------------------------- ON ACTION ----------------------------------------
    @FXML
    void cmbCourseIdOnAction(ActionEvent event) throws Exception {
        String courseId = cmbCourseId.getSelectionModel().getSelectedItem().toString();

        try {
            String courseName = courseBO.searchByCourseId(courseId).getCourseName();
            if (courseName != null) {
                txtCourseName.setText(courseName);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbStudentIdOnAction(ActionEvent event) throws Exception {
        String studentId = cmbStudentId.getSelectionModel().getSelectedItem().toString();

        try {
            String studentName = studentBO.searchByStudentId(studentId).getName();
            if (studentName != null) {
                txtStudentName.setText(studentName);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    // ---------------------------------------- ON KEY RELEASED ----------------------------------------
    @FXML
    void txtEnrolledDateOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtEnrolmentIdOnKeyReleased(KeyEvent event) {

    }


    // ---------------------------------------- VALIDATION ----------------------------------------
    public String isValid() {
        return null;
    }

}