package bankApp.service;

import bankApp.DAO.BankDao;
import bankApp.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Service
public class BankServiceTransactionImpl implements BankService<Transaction> {

    @Autowired
    private BankDao<Transaction> bankDAO;

    @Override
    public List<Transaction> getAll() {
        return bankDAO.getAll();
    }

    @Override
    @Transactional
    public Transaction getOne(int id) {
        return bankDAO.getOne(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Transaction transaction) {
        bankDAO.saveOrUpdate(transaction);
    }

    @Override
    @Transactional
    public void delete(int id) {
        bankDAO.delete(id);
    }
}
