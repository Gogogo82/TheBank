package bankApp.service;

import bankApp.dao.AccountDao;
import bankApp.dao.TransactionDao;
import bankApp.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDAO;
    private final AccountDao accountDao;

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionDAO, AccountDao accountDao) {
        this.transactionDAO = transactionDAO;
        this.accountDao = accountDao;
    }

    @Override
    @Transactional
    public List<Transaction> findByAccount(int accountId) {
        return transactionDAO.findByAccount(accountId);
    }

    @Override
    @Transactional
    public Transaction findById(int id) {
        return transactionDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Transaction transaction) {

        transaction.setAccountFrom(accountDao.findById(transaction.getAccountFromId()));
        transaction.setAccountTo(accountDao.findById(transaction.getAccountToId()));
        transactionDAO.save(transaction);
    }

    @Override
    @Transactional
    public void delete(int id) {
        transactionDAO.delete(id);
    }
}
