package bankApp.service;

import bankApp.DAO.BankDao;
import bankApp.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankServiceClientImpl implements BankService<Client> {

    @Autowired
    private BankDao<Client> bankDao;

    @Override
    @Transactional
    public List<Client> getAll() {
        return bankDao.getAll();
    }

    @Override
    @Transactional
    public Client getOne(int id) {
        return bankDao.getOne(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Client client) {
        bankDao.saveOrUpdate(client);
    }

    @Override
    @Transactional
    public void delete(int id) {
        bankDao.delete(id);
    }
}
