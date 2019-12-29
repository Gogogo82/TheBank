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

        Account accountFrom = accountDao.findById(transaction.getAccountFromId());
        Account accountTo = accountDao.findById(transaction.getAccountToId());

        if (accountFrom.getNumber().equals(accountTo.getNumber()))
            throw new RuntimeException("Credit and debit accounts are equal");

        // change accounts balance
        double newAmount = accountFrom.getAmount() - transaction.getAmount();

        if (newAmount < 0)
            throw new RuntimeException("Not enough money to withdraw");

        accountFrom.setAmount(newAmount);

        accountTo.setAmount(accountTo.getAmount() + transaction.getAmount());

        // set ValidateAccount objects to Transaction, according to account ID from <form>
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
