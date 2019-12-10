package bankApp.DAO;

import bankApp.entity.Account;
import bankApp.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

//@Repository
public class AccountDaoImpl implements BankDao<Account> {

//    @Autowired
    SessionFactory hsqlSessionFactory;

    @Override
    public List<Account> getAll() {
        Session session = hsqlSessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("FROM account", Account.class);
        return query.getResultList();
    }

    public Account getOne(int id) {
        Session session = hsqlSessionFactory.getCurrentSession();
        return session.get(Account.class, id);
    }

    @Override
    public void saveOrUpdate(Account entity) {
        Session session = hsqlSessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public void delete(int id) {
        Session session = hsqlSessionFactory.getCurrentSession();
        Account account = session.get(Account.class, id);
        session.delete(account);
    }

}
