package lk.ijse.culinaryacademy.dao.custom.Impl;

import lk.ijse.culinaryacademy.dao.custom.EnrolmentDAO;
import lk.ijse.culinaryacademy.entity.Enrolment;

import java.util.List;

public class EnrolmentDAOImpl implements EnrolmentDAO {
    @Override
    public boolean add(Enrolment enrolment) throws Exception {
        return false;
    }

    @Override
    public boolean update(Enrolment enrolment) throws Exception {
        return false;
    }

    @Override
    public String currentId() throws Exception {
        return "";
    }

    @Override
    public Enrolment searchById(String id) throws Exception {
        return null;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public List<Enrolment> getAll() throws Exception {
        return List.of();
    }

    @Override
    public List<String> getIds() throws Exception {
        return List.of();
    }

}
