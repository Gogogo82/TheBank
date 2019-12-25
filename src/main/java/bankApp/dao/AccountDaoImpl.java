package bankApp.dao;

import bankApp.entity.Account;
import bankApp.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public AccountDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Account> findByClientId(int clientId) {
        Session session = sessionFactory.getCurrentSession();
        Client client = session.get(Client.class, clientId);
        Query<Account> query = session.createQuery("FROM Account WHERE client = :clientParam", Account.class);
        query.setParameter("clientParam", client);
        return query.getResultList();
    }

    public Account findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Account.class, id);
    }

    @Override
    public void save(Account entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Account account = session.get(Account.class, id);
        Client client = account.getClient();
        client.getAccounts().remove(account);
        session.save(client);
    }
}
