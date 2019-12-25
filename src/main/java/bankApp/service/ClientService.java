package bankApp.service;

import bankApp.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    Client getOne(int id);

    void saveOrUpdate(Client t);

    void delete(int id);
}
