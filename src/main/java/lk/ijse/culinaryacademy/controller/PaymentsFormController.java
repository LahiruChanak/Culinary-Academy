package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PaymentsFormController {

    @FXML
    private JFXComboBox<?> cmbCourseId;

    @FXML
    private JFXComboBox<?> cmbStatus;

    @FXML
    private JFXComboBox<?> cmbStudentId;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private JFXTextField txtFee;

    @FXML
    private JFXTextField txtPaymentDate;

    @FXML
    private JFXTextField txtPaymentId;

    @FXML
    void initialize() {

    }

    // ------------------------------------ CRUD OPERATIONS ------------------------------------
    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    // ------------------------------------ OTHER OPERATIONS ------------------------------------


    // ------------------------------------ ON ACTION ------------------------------------
    @FXML
    void txtPaymentIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbStudentIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCourseIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbStatusOnAction(ActionEvent event) {

    }

    @FXML
    void txtFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtPaymentDateOnAction(ActionEvent event) {

    }

}
