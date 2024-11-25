package lk.ijse.culinaryacademy.dao.custom.Impl;


import lk.ijse.culinaryacademy.dao.custom.PaymentDAO;
import lk.ijse.culinaryacademy.entity.Payment;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean add(Payment payment) throws Exception {
        return false;
    }

    @Override
    public boolean update(Payment payment) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String paymentId) throws Exception {
        return false;
    }

    @Override
    public Payment searchById(String paymentId) throws Exception {
        return null;
    }

    @Override
    public String currentId() throws Exception {
        return "";
    }

    @Override
    public List<Payment> getAll() throws Exception {
        return List.of();
    }

    @Override
    public List<String> getIds() throws Exception {
        return List.of();
    }
}
