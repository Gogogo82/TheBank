package bankApp.dao;

import bankApp.entity.Account;

import java.util.List;

public interface AccountDao {

    List<Account> findAll();

    List<Account> findByClientId(int clientId);

    Account findById(int id);

    void save(Account entity);

    void delete(int id);
}
