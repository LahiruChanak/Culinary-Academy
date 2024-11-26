package lk.ijse.culinaryacademy.dao.custom.Impl;

import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.dao.custom.UserDAO;
import lk.ijse.culinaryacademy.entity.Student;
import lk.ijse.culinaryacademy.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User user) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User user) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(searchById(id));
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changeEmail(String currentEmail, String newEmail, String confirmEmail) throws Exception {
        return false;
    }

    @Override
    public boolean changePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception {
        return false;
    }

    @Override
    public User searchById(String userId) throws Exception {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            System.out.println(session.get(Student.class, userId));
            return session.get(User.class, userId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAll() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            users = (ArrayList<User>) session.createQuery("FROM User").list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<String> getIds() throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT u.userId FROM User u ORDER BY u.userId";
            Query<String> query = session.createQuery(hql, String.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String currentId() throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT u.userId FROM User u ORDER BY u.userId DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
