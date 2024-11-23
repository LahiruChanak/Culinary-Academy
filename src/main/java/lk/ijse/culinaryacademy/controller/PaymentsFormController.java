package lk.ijse.culinaryacademy.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.ijse.culinaryacademy.bo.BOFactory;
import lk.ijse.culinaryacademy.bo.custom.PaymentBO;
import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.dto.PaymentDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PaymentsFormController {

    @FXML
    private JFXComboBox<String> cmbCourseId;

    @FXML
    private JFXComboBox<String> cmbStatus;

    @FXML
    private JFXComboBox<String> cmbStudentId;

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

    private List<PaymentDTO> paymentList = new ArrayList<>();

    // Objects
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PAYMENT);

    @FXML
    void initialize() {

    }

    // ------------------------------------ CRUD OPERATIONS ------------------------------------
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String paymentId = txtPaymentId.getText();
        String studentId = cmbStudentId.getValue();
        String courseId = cmbCourseId.getValue();
        String paymentDateText = txtPaymentDate.getText();
        String feeText = txtFee.getText();
        String status = cmbStatus.getValue();

        Date paymentDate;
        double fee;

        try {
            paymentDate = Date.valueOf(paymentDateText);
            fee = Double.parseDouble(feeText);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Input").show();
            return;
        }

        PaymentDTO paymentDTO = new PaymentDTO(paymentId, studentId, courseId, paymentDate, fee, status);

        try {
            boolean isAdded = paymentBO.addPayment(paymentDTO);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Added Successfully.").show();
                clearField();
                refreshTable();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
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
    private void clearField() {
        txtPaymentId.clear();
        cmbStudentId.getSelectionModel().clearSelection();
        cmbCourseId.getSelectionModel().clearSelection();
        txtPaymentDate.clear();
        txtFee.clear();
        cmbStatus.getSelectionModel().clearSelection();
    }

    private void refreshTable() {
        this.paymentList = getAllPayments();
        loadPaymentTable();
    }

    private void loadNextPaymentId() throws Exception {
        try {
            String currentId = paymentBO.currentPaymentId();
            String nextId = nextId(currentId);

            txtPaymentId.setText(nextId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
//        if (currentId != null) {
//            String[] split = currentId.split("P");
//            int id = Integer.parseInt(split[1]);
//            return "P" + String.format("%03d", ++id);
//        }
        return "P001";
    }

    private List<PaymentDTO> getAllPayments() {
        List<PaymentDTO> paymentList = null;
        try {
            paymentList = paymentBO.getAllPayments();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return paymentList;
    }

    private void loadPaymentTable() {
//        tblPayment.getItems().clear();
//        tblPayment.getItems().addAll(paymentList);
    }

    private void loadStudentIds() {
    }


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
