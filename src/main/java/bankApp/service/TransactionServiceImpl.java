package bankApp.service;

import bankApp.dao.AccountDao;
import bankApp.dao.TransactionDao;
import bankApp.entity.Account;
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

        // change accounts balance
        Account accountFrom = accountDao.findById(transaction.getAccountFromId());
        accountFrom.setAmount(accountFrom.getAmount() - transaction.getAmount());

        Account accountTo = accountDao.findById(transaction.getAccountToId());
        accountTo.setAmount(accountTo.getAmount() + transaction.getAmount());

        // set Account objects to Transaction, according to account ID from <form>
        transaction.setAccountFrom(accountFrom);
        transaction.setAccountTo(accountTo);

        transactionDAO.save(transaction);
    }

    @Override
    @Transactional
    public void delete(int id) {
        transactionDAO.delete(id);
    }
}
