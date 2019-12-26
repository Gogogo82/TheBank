package bankApp.service;

import bankApp.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findByAccount(int accountId);

    Transaction findById(int id);

    void save(Transaction t);

    void delete(int id);


}
