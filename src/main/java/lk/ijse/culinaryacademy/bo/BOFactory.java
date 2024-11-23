package lk.ijse.culinaryacademy.bo;

import lk.ijse.culinaryacademy.bo.custom.impl.CourseBOImpl;
import lk.ijse.culinaryacademy.bo.custom.impl.CredentialBOImpl;
import lk.ijse.culinaryacademy.bo.custom.impl.PaymentBOImpl;
import lk.ijse.culinaryacademy.bo.custom.impl.StudentBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private  BOFactory() {
    }

    public static BOFactory getBOFactory() { return (boFactory == null) ? (boFactory = new BOFactory()) : (boFactory); }

    public enum BOTypes{
        COURSE, CREDENTIAL, PAYMENT, STUDENT
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case COURSE:
                return new CourseBOImpl();

            case CREDENTIAL:
                return new CredentialBOImpl();

            case PAYMENT:
                return new PaymentBOImpl();

            case STUDENT:
                return new StudentBOImpl();

            default:
                return null;
        }
    }
}
