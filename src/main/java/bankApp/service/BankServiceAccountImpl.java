package bankApp.service;

import bankApp.DAO.AccountDaoImpl;
import bankApp.DAO.BankDao;
import bankApp.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class BankServiceAccountImpl implements BankService<Account> {

    @Autowired
    private AccountDaoImpl bankDAO;

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

    @Transactional
    public List<Account> getByClient(int id) {
        getAll().forEach(System.out::println);
        return getAll();
    }
}
