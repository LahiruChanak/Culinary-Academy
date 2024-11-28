package lk.ijse.culinaryacademy.dao.custom.Impl;


import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.dao.custom.PaymentDAO;
import lk.ijse.culinaryacademy.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean add(Payment payment) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(payment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Payment payment) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(payment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String paymentId) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(searchById(paymentId));
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Payment searchById(String paymentId) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            return session.get(Payment.class, paymentId); // Returns null if not found
        } catch (Exception e) {
            throw new Exception("Error fetching Payment with ID: " + paymentId, e);
        }
    }


    @Override
    public String currentId() throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT p.paymentId FROM Payment p ORDER BY p.paymentId DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Payment> getAll() throws Exception {
        ArrayList<Payment> payments = new ArrayList<>();
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            payments = (ArrayList<Payment>) session.createQuery("FROM Payment p ORDER BY p.paymentId").list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public List<String> getIds() throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT p.paymentId FROM Payment p ORDER BY p.paymentId DESC";
            Query<String> query = session.createQuery(hql, String.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public double getTotalPayments() throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT SUM(p.fee) FROM Payment p";
            Query<Double> query = session.createQuery(hql, Double.class);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
