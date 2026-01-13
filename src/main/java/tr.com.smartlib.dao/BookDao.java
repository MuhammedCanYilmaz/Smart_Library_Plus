package tr.com.smartlib.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tr.com.smartlib.entity.Book;
import tr.com.smartlib.util.HibernateUtil;
import java.util.List;

public class BookDao {

    public void save(Book book) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(book);
            tx.commit();
        }
    }

    public void update(Book book) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(book);
            tx.commit();
        }
    }

    public List<Book> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Book", Book.class).list();
        }
    }

    public Book getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Book.class, id);
        }
    }

    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Book book = session.get(Book.class, id);
            if (book != null) {
                session.remove(book);
                tx.commit();
                System.out.println("✅ Kitap veritabanından silindi: " + book.getTitle());
            } else {
                System.out.println("❌ Hata: Bu ID ile kayıtlı kitap yok.");
            }
        }
    }
}