package bankApp.service;

import bankApp.DAO.BankDao;
import bankApp.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Service
public class BankServiceAccountImpl implements BankService<Account> {

    @Autowired
    private BankDao<Account> bankDAO;

    @Override
    public List<Account> getAll() {
        return bankDAO.getAll();
    }

    @Override
    @Transactional
    public Account getOne(int id) {
        return bankDAO.getOne(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Account account) {
        bankDAO.saveOrUpdate(account);
    }

    @Override
    @Transactional
    public void delete(int id) {
        bankDAO.delete(id);
    }
}
