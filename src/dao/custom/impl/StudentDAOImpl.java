package dao.custom.impl;

import dao.custom.StudentDAO;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfigration;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student entity) throws Exception {
        Session session = FactoryConfigration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session = FactoryConfigration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfigration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student students = session.get(Student.class, s);

        session.delete(students);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Student> findAll() throws Exception {
        Session session = FactoryConfigration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();



        Query query = session.createQuery("from Student");
        List<Student> list = query.list();

        transaction.commit();

        session.close();
        return list;

    }
}
