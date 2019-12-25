package bankApp.dao;

import bankApp.entity.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public TransactionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Transaction> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Transaction> query = session.createQuery("FROM Transaction", Transaction.class);
        return null;
    }

    @Override
    public Transaction findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Transaction.class, id);
    }

    @Override
    public void save(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(transaction);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction =session.get(Transaction.class, id);
        session.delete(transaction);
    }
}
