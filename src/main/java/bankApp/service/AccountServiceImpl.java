package bankApp.service;

import bankApp.dao.AccountDao;
import bankApp.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    @Transactional
    public List<Account> getByClientId(int clientId) {
//        getAll().forEach(System.out::println);
        return accountDao.findByClientId(clientId);
    }

    @Override
    @Transactional
    public Account getOne(int id) {
        return accountDao.findById(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Account account) {
        accountDao.save(account);
    }

    @Override
    @Transactional
    public void delete(int id) {
        accountDao.delete(id);
    }


}
