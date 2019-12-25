package bankApp.controller;

import bankApp.entity.Account;
import bankApp.entity.Client;
import bankApp.service.AccountService;
import bankApp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountsController {

       private final AccountService accountService;
       private final ClientService clientService;

       private Client client;

       @Autowired
       public AccountsController(AccountService accountService, ClientService clientService) {
              this.accountService = accountService;
              this.clientService = clientService;
       }

       @RequestMapping("/listAccounts")
       public String listAccounts (
//               @RequestParam("clientId") int id,
//                                   @RequestParam("clientName") String name,
               @ModelAttribute("client") Client client2,
                                   Model model) {
              System.out.println("client2: " + client2);
              client = client2;
//              model.addAttribute("clientId", id);
//              model.addAttribute("clientName", name);
//              model.addAttribute("accounts", bankServiceAccount.getByClient(id));
              return "listAccounts";
       }

       @RequestMapping("/showUpdateAccountForm")
       public String showUpdateAccountForm(@RequestParam("clientId") int id,
                                           @RequestParam("clientName") String name,
                                           Model model) {
              model.addAttribute("clientId", id);
              model.addAttribute("clientName", name);
              model.addAttribute("account", new Account());
              return "updateAccountForm";
       }

       @RequestMapping("/saveAccount")
       public String saveAccount(@RequestParam("clientId") int id,
                                 @RequestParam("clientName") String name,
                                 @ModelAttribute("account") Account account) {
              account.setClient(clientService.getOne(id));
              accountService.saveOrUpdate(account);
              return "redirect:/listAccounts?clientId=" + id + "&clientName=" + name;
       }

       @RequestMapping("/updateAccount")
       public String updateAccount(@RequestParam("accountId") int accountId,
                                   @RequestParam("clientId") int clientId,
                                   @RequestParam("clientName") String name,
                                   Model model) {
              model.addAttribute("clientId", clientId);
              model.addAttribute("clientName", name);
              model.addAttribute("account", accountService.getOne(accountId));
              return "updateAccountForm";
       }
}
