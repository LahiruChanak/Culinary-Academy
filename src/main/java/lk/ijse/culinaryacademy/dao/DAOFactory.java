package lk.ijse.culinaryacademy.dao;

import lk.ijse.culinaryacademy.dao.custom.Impl.CourseDAOImpl;
import lk.ijse.culinaryacademy.dao.custom.Impl.CredentialDAOImpl;
import lk.ijse.culinaryacademy.dao.custom.Impl.PaymentDAOImpl;
import lk.ijse.culinaryacademy.dao.custom.Impl.StudentDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        COURSE, CREDENTIAL, PAYMENT, STUDENT
    }

    //Object Creation Logic

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case COURSE:
                return new CourseDAOImpl();

            case CREDENTIAL:
                return new CredentialDAOImpl();

            case PAYMENT:
                return new PaymentDAOImpl();

            case STUDENT:
                return new StudentDAOImpl();

            default:
                return null;
        }
    }
}
