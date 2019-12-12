package bankApp.controller;

import bankApp.entity.Account;
import bankApp.entity.Client;
import bankApp.service.BankService;
import bankApp.service.BankServiceAccountImpl;
import bankApp.service.BankServiceClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountsController {

       @Autowired
       BankServiceAccountImpl bankServiceAccount;

//       @Autowired
//       BankServiceClientImpl bankServiceClient;

       @RequestMapping("/listAccounts")
       public String listAccounts (@RequestParam("clientId") int id,
                                   @RequestParam("clientName") String name,
                                   Model model) {
//              Client client = bankServiceClient.getOne(id);
              model.addAttribute("clientName", name);
              model.addAttribute("accounts", bankServiceAccount.getByClient(id));
              return "listAccounts";
       }

       @RequestMapping("/showUpdateAccountForm")
       public String showUpdateAccountForm(@RequestParam("clientName") String clientName, Model model) {
              Account account = new Account();
              account.setAmount(0d);
              model.addAttribute("account", account);
              return "updateAccountForm";
       }

       @RequestMapping("/saveAccount")
       public String saveAccount(@ModelAttribute("account") Account account) {
              bankServiceAccount.saveOrUpdate(account);
              return "redirect:/listAccounts";
       }

}
