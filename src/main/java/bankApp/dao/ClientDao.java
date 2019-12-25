package bankApp.dao;

import bankApp.entity.Client;

import java.util.List;

public interface ClientDao {

    List<Client> findAll();

    Client findById(int id);

    void save(Client entity);

    void delete(int id);
}
