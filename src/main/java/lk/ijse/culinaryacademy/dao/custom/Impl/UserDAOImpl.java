package lk.ijse.culinaryacademy.dao.custom.Impl;

import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.dao.custom.UserDAO;
import lk.ijse.culinaryacademy.entity.User;
import org.hibernate.HibernateException;
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
            throw new IllegalArgumentException("New email and confirmation email do not match.");
        }

        if (newEmail == null || newEmail.isEmpty()) {
            throw new IllegalArgumentException("New email cannot be empty.");
        }

        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                String hql = "UPDATE User u SET u.email = :newEmail WHERE u.email = :currentEmail";
                Query query = session.createQuery(hql);
                query.setParameter("newEmail", newEmail);
                query.setParameter("currentEmail", currentEmail);

                int result = query.executeUpdate();

                if (result > 0) {
                    transaction.commit();
                    return true;
                } else {
                    transaction.rollback();
                    throw new IllegalArgumentException("Current email does not exist.");
                }
            } catch (HibernateException e) {
                e.printStackTrace();
                transaction.rollback();
                throw new Exception("Error updating email", e);
            }
        }
    }

    @Override
    public boolean changePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception {
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("New password and confirmation password do not match.");
        }

        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("New password cannot be empty.");
        }

        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                String hql = "UPDATE User u SET u.password = :newPassword WHERE u.password = :currentPassword";
                Query query = session.createQuery(hql);
                query.setParameter("newPassword", newPassword);
                query.setParameter("currentPassword", currentPassword);

                int result = query.executeUpdate();

                if (result > 0) {
                    transaction.commit();
                    return true;
                } else {
                    transaction.rollback();
                    throw new IllegalArgumentException("Current password does not exist.");
                }
            } catch (HibernateException e) {
                e.printStackTrace();
                transaction.rollback();
                throw new Exception("Error updating password", e);
            }
        }
    }

    @Override
    public boolean checkLogin(String username, String password) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while checking login credentials", e);
        }
    }

    @Override
    public boolean checkRegister(String username, String name, String email, String password, String confirmPassword, String role) throws Exception {
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
                user.setRole(role);

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
    public String getUsrName(String username) throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT u.name FROM User u WHERE u.username = :username";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("username", username);
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
