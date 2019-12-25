package bankApp.service;

import bankApp.entity.Account;
import bankApp.entity.Client;

import java.util.List;

public interface AccountService {

    List<Account> getByClientId(int clientId);

    Account getOne(int id);

    void saveOrUpdate(Account t);

    void delete(int id);
}
