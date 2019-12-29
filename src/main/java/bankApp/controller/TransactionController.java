package bankApp.controller;

import bankApp.entity.Account;
import bankApp.entity.Transaction;
import bankApp.service.AccountService;
import bankApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    // pre-process form data
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        // trim whitespaces for any input string, and if no chars left, replace with null
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("listTransactions")
    public String listTransactions(@RequestParam("accountId") int accountId, Model model) {

        Account account = accountService.findById(accountId);
        model.addAttribute("currentAccount", account);

        List<Transaction> transactionList = transactionService.findByAccount(account.getId());
        model.addAttribute("transactionList", transactionList);

        return "listTransactions";
    }

    // util: build accounts list for form:select
    private Map<Integer, String> getAccountsMap() {

        Map<Integer, String> accountsMap = new TreeMap<>();
        List<Account> accountsList = accountService.findAll();
        for (Account accountItem : accountsList) {
            accountsMap.put(accountItem.getId(), accountItem.getNumber());
        }
        return accountsMap;
    }

    @RequestMapping("addOrUpdateTransaction")
    public String addOrUpdateTransaction(@RequestParam("transactionId") int transactionId, Model model) {

        Transaction transaction;

        if (transactionId == -1) {
            transaction = new Transaction();
            transaction.setDate(LocalDate.now());
        } else {
            transaction = transactionService.findById(transactionId);
            transaction.setAccountFromId(transaction.getAccountFrom().getId());
            transaction.setAccountToId(transaction.getAccountTo().getId());
        }

        Account account = (Account) model.getAttribute("currentAccount");

        model.addAttribute("transaction", transaction);
        model.addAttribute("currentAccount", account);
        model.addAttribute("accountsMap", getAccountsMap());

        return "updateTransactionForm";
    }

    @RequestMapping("saveTransaction")
    public String saveTransaction(@Valid @ModelAttribute("transaction") Transaction transaction,
                                  BindingResult bindingResult,
                                  Model model) {

        String result = "updateTransactionForm";

        if (bindingResult.hasErrors()) {
            Account account = (Account) model.getAttribute("currentAccount");

            model.addAttribute("transaction", transaction);
            model.addAttribute("currentAccount", account);
            model.addAttribute("accountsMap", getAccountsMap());
            return result;
        } else {
            try {
                transactionService.save(transaction);
                Account currentAccount = (Account) model.getAttribute("currentAccount");
                result = "redirect:/transaction/listTransactions?accountId=" + currentAccount.getId();

            } catch (Exception e) {
                bindingResult.rejectValue("accountFrom", "error.transaction", e.getMessage());

                Account account = (Account) model.getAttribute("currentAccount");
                model.addAttribute("transaction", transaction);
                model.addAttribute("currentAccount", account);
                model.addAttribute("accountsMap", getAccountsMap());
            }
        }
        return result;
    }

    @RequestMapping("deleteTransaction")
    public String deleteTransaction(@ModelAttribute("transactionId") int transactionId, Model model) {

        transactionService.delete(transactionId);
        Account currentAccount = (Account) model.getAttribute("currentAccount");
        return "redirect:/transaction/listTransactions?accountId=" + currentAccount.getId();
    }

}
