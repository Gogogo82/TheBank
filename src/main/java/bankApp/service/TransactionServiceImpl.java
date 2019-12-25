package bankApp.service;

import bankApp.dao.TransactionDao;
import bankApp.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDAO;

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Override
    @Transactional
    public List<Transaction> getAll() {
        return transactionDAO.findAll();
    }

    @Override
    @Transactional
    public Transaction getOne(int id) {
        return transactionDAO.findById(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Transaction transaction) {
        transactionDAO.save(transaction);
    }

    @Override
    @Transactional
    public void delete(int id) {
        transactionDAO.delete(id);
    }
}
