package bankApp.controller;

import bankApp.entity.Account;
import bankApp.entity.Transaction;
import bankApp.service.AccountService;
import bankApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/transaction")
@SessionAttributes("currentAccount")
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @RequestMapping("listTransactions")
    public String listTransactions(@RequestParam("accountId") int accountId, Model model){

        Account account = accountService.findById(accountId);
        model.addAttribute("currentAccount", account);

        List<Transaction> transactionList = transactionService.findByAccount(account.getId());
        model.addAttribute("transactionList", transactionList);

        return "listTransactions";
    }

    @RequestMapping("addOrUpdateTransaction")
    public String addOrUpdateTransaction(@RequestParam("transactionId") int transactionId, Model model) {

        Transaction transaction;

        if(transactionId == -1) {
            transaction = new Transaction();
            transaction.setDate(LocalDate.now());
        }
        else transaction = transactionService.findById(transactionId);

        Account account = (Account)model.getAttribute("currentAccount");

        Map<Integer, String> accountsMap = new TreeMap<>();
        List<Account> accountsList = accountService.findAll();
        for (Account accountItem : accountsList) {
            accountsMap.put(accountItem.getId(), accountItem.getNumber());
        }

        model.addAttribute("transaction", transaction);
        model.addAttribute("currentAccount", account);
        model.addAttribute("accountsMap", accountsMap);

        return "updateTransactionForm";
    }

    @RequestMapping("saveTransaction")
    public String saveTransaction(@ModelAttribute("transaction") Transaction transaction, Model model) {

        transactionService.save(transaction);
        Account currentAccount = (Account)model.getAttribute("currentAccount");

        return "redirect:/transaction/listTransactions?accountId=" + currentAccount.getId();
    }

    @RequestMapping("deleteTransaction")
    public String deleteTransaction(@ModelAttribute("transactionId") int transactionId, Model model) {

        transactionService.delete(transactionId);
        Account currentAccount = (Account)model.getAttribute("currentAccount");

        return "redirect:/transaction/listTransactions?accountId=" + currentAccount.getId();
    }

}
