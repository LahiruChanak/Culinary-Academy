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
        if (!newEmail.equals(confirmEmail)) {
            return false;
        }
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE User u SET u.email = :newEmail WHERE u.email = :currentEmail";
            Query query = session.createQuery(hql);
            query.setParameter("newEmail", newEmail);
            query.setParameter("currentEmail", currentEmail);
            int result = query.executeUpdate();
            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception {
        if (!newPassword.equals(confirmPassword)) {
            return false;
        }
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE User u SET u.password = :newPassword WHERE u.password = :currentPassword";
            Query query = session.createQuery(hql);
            query.setParameter("newPassword", newPassword);
            query.setParameter("currentPassword", currentPassword);
            int result = query.executeUpdate();
            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkLogin(String email, String password) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkRegister(String username, String name, String email, String password, String confirmPassword) throws Exception {
        // Check if the password mismatches
        if (!password.equals(confirmPassword)) {
            return false;
        }

        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                User user = new User();
                user.setUsername(username);
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);

                session.save(user);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw new Exception("Error occurred while registering the user: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public String getUserName(String email) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT u.name FROM User u WHERE u.email = :email";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User searchByName(String name) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT u FROM User u WHERE u.name = :name";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
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
            String hql = "SELECT u.username FROM User u ORDER BY u.username";
            Query<String> query = session.createQuery(hql, String.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User searchById(String id) throws Exception {
        return null;
    }

    @Override
    public String currentId() throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT u.username FROM User u ORDER BY u.username DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
