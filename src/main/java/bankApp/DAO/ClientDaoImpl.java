package bankApp.DAO;

import bankApp.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDaoImpl implements BankDao<Client> {

    @Autowired
    private SessionFactory hsqlSessionFactory;

    @Override
    public List<Client> getAll() {
        Session session = hsqlSessionFactory.getCurrentSession();
        Query<Client> query = session.createQuery("FROM Client", Client.class);
        return query.getResultList();
    }

    @Override
    public Client getOne(int id) {
        Session session = hsqlSessionFactory.getCurrentSession();
        return session.get(Client.class, id);
    }

    @Override
    public void saveOrUpdate(Client client) {
            Session session = hsqlSessionFactory.getCurrentSession();
            session.saveOrUpdate(client);
    }

    @Override
    public void delete(int id) {
        Session session = hsqlSessionFactory.getCurrentSession();
        Client client = session.get(Client.class, id);
        session.delete(client);
    }
}
