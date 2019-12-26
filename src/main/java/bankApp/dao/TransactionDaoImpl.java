package bankApp.dao;

import bankApp.entity.Account;
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
    public List<Transaction> findByAccount(int accountId) {
        Session session = sessionFactory.getCurrentSession();
        Account account = session.get(Account.class, accountId);
//
//        Query<Transaction> query = session.createQuery(
//                "FROM Transaction WHERE accountFrom = :accountParam OR accountTo = :accountParam",
//                Transaction.class);
//
//        query.setParameter("accountParam", accountId);
        Query<Transaction> query = session.createQuery(
                "FROM Transaction WHERE accountFrom = :accountParam OR accountTo = :accountParam",
                Transaction.class);

        query.setParameter("accountParam", account);

        query.getResultList().forEach(System.out::println);
        return query.getResultList();
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
        Transaction transaction = session.get(Transaction.class, id);

        Account accountFrom = transaction.getAccountFrom();
        accountFrom.getTransactionsFrom().remove(transaction);

        Account accountTo = transaction.getAccountTo();
        accountTo.getTransactionsTo().remove(transaction);

        session.save(transaction);
    }
}
