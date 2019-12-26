package bankApp.service;

import bankApp.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    Client findById(int id);

    void save(Client t);

    void delete(int id);
}
