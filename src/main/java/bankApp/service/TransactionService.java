package bankApp.service;

import bankApp.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAll();

    Transaction getOne(int id);

    void saveOrUpdate(Transaction t);

    void delete(int id);
}
