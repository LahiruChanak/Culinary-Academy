package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.PaymentDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {
    boolean addPayment(PaymentDTO paymentDTO);

    List<PaymentDTO> getAllPayments() throws Exception;

    String currentPaymentId() throws Exception;
}
