package bankApp.DAO;

import bankApp.entity.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

//@Repository
public class TransactionDaoImpl implements BankDao<Transaction> {

//    @Autowired
    private SessionFactory hsqlSessionFactory;

//    public List<Transaction> getByClientFrom(int id) {
//        Session session = hsqlSessionFactory.getCurrentSession();
//        Query<Transaction> query = session.createQuery(
//                "FROM transaction JOIN account ON transaction.accountFrom = account.id where account.ownerId = id",
//                Transaction.class);
//        return query.getResultList();
//    }
//
//    public List<Transaction> getByClientTo(int id) {
//        Session session = hsqlSessionFactory.getCurrentSession();
//        Query<Transaction> query = session.createQuery(
//                "FROM transaction JOIN account ON transaction.accountTo = account.id where account.ownerId = id",
//                Transaction.class);
//        return query.getResultList();
//    }


    @Override
    public List<Transaction> getAll() {
        Session session = hsqlSessionFactory.getCurrentSession();
        Query<Transaction> query = session.createQuery("FROM Transaction", Transaction.class);
        return null;
    }

    @Override
    public Transaction getOne(int id) {
        Session session = hsqlSessionFactory.getCurrentSession();
        return session.get(Transaction.class, id);
    }

    @Override
    public void saveOrUpdate(Transaction transaction) {
        Session session = hsqlSessionFactory.getCurrentSession();
        session.saveOrUpdate(transaction);
    }

    @Override
    public void delete(int id) {
        Session session = hsqlSessionFactory.getCurrentSession();
        Transaction transaction =session.get(Transaction.class, id);
        session.delete(transaction);
    }
}
