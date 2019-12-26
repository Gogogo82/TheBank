package bankApp.service;

import bankApp.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    List<Account> findByClientId(int clientId);

    Account findById(int id);

    void save(Account t);

    void delete(int id);
}
