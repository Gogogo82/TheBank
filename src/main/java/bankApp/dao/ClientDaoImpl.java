package bankApp.dao;

import bankApp.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Client> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Client> query = session.createQuery("FROM Client", Client.class);
        return query.getResultList();
    }

    @Override
    public Client findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);
    }

    @Override
    public void save(Client client) {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(client);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Client client = session.get(Client.class, id);
        session.delete(client);
    }
}
