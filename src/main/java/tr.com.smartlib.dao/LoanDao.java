package tr.com.smartlib.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tr.com.smartlib.entity.Loan;
import tr.com.smartlib.util.HibernateUtil;
import java.util.List;

public class LoanDao {

    public void save(Loan loan) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(loan);
            tx.commit();
        }
    }

    public void update(Loan loan) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(loan);
            tx.commit();
        }
    }

    public List<Loan> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Loan", Loan.class).list();
        }
    }

    public Loan getActiveLoanByBook(Long bookId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Loan l where l.book.id = :bId and l.returnDate is null", Loan.class)
                    .setParameter("bId", bookId)
                    .uniqueResult();
        }
    }

    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Loan loan = session.get(Loan.class, id);
            if (loan != null) {
                session.remove(loan);
                tx.commit();
            }
        }
    }
}