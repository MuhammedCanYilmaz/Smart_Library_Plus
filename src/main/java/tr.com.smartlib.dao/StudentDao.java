package tr.com.smartlib.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tr.com.smartlib.entity.Student;
import tr.com.smartlib.util.HibernateUtil;
import java.util.List;

public class StudentDao {

    public void save(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(student);
            tx.commit();
        }
    }

    public List<Student> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }

    public Student getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        }
    }

    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.remove(student);
                tx.commit();
                System.out.println("✅ Öğrenci ve ilişkili kayıtları silindi: " + student.getName());
            } else {
                System.out.println("❌ Hata: Bu ID ile kayıtlı öğrenci yok.");
            }
        }
    }
}