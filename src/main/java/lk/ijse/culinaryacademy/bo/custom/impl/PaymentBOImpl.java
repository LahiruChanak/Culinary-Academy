package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.PaymentBO;
import lk.ijse.culinaryacademy.dto.PaymentDTO;

import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    @Override
    public boolean addPayment(PaymentDTO paymentDTO) {
        return false;
    }

    @Override
    public List<PaymentDTO> getAllPayments() throws Exception {
        return List.of();
    }

    @Override
    public String currentPaymentId() throws Exception {
        return "";
    }
}
