package bankApp.dao;

import bankApp.entity.Transaction;

import java.util.List;

public interface TransactionDao {

    List<Transaction> findAll();

    Transaction findById(int id);

    void save(Transaction entity);

    void delete(int id);
}
