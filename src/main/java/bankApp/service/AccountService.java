package bankApp.service;

import bankApp.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account getOne(int id);

    void saveOrUpdate(Account t);

    void delete(int id);
}
