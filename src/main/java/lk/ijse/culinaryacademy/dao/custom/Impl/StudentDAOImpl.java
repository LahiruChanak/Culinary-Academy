package lk.ijse.culinaryacademy.dao.custom.Impl;


import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.dao.custom.StudentDAO;
import lk.ijse.culinaryacademy.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    Session session;

//    @Override
//    public void setSession(Session session) {
//        this.session = session;
//    }

    @Override
    public boolean add(Student student) throws Exception {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Student student) throws Exception {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String studentId) throws Exception {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(Student.class, studentId));
            transaction.commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String currentId() throws Exception {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "select s.studentId from Student s order by s.studentId desc";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Student> getAll() throws Exception {
        return List.of();
    }

    @Override
    public Student searchById(String studentId) throws Exception {
        return null;
    }

    @Override
    public List<String> getIds() throws Exception {
        return List.of();
    }
}
