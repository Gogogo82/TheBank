package bankApp.service;

import bankApp.dao.ClientDao;
import bankApp.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    @Transactional
    public List<Client> getAll() {
        return clientDao.findAll();
    }

    @Override
    @Transactional
    public Client findById(int id) {
        return clientDao.findById(id);
    }

    @Override
    @Transactional
    public void save(Client client) {
        clientDao.save(client);
    }

    @Override
    @Transactional
    public void delete(int id) {
        clientDao.delete(id);
    }
}
