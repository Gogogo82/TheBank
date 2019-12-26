package bankApp.dao;

import bankApp.entity.Transaction;

import java.util.List;

public interface TransactionDao {

    List<Transaction> findByAccount(int accountId);

    Transaction findById(int id);

    void save(Transaction entity);

    void delete(int id);
}
