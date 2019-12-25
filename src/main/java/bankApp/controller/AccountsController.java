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
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/account")
@SessionAttributes("currentClient")
public class AccountsController {

    private final AccountService accountService;
    private final ClientService clientService;

    @Autowired
    public AccountsController(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }

    @RequestMapping("/listAccounts")
    public String listAccounts(@RequestParam("clientId") int id, Model model) {

        Client client = clientService.getOne(id);
        model.addAttribute("currentClient",  client);
        List<Account> accountList = accountService.getByClientId(id);
        model.addAttribute("accounts", accountList);
        model.addAttribute("client", client);
        return "listAccounts";
    }

    @RequestMapping("/addNewAccount")
    public String showUpdateAccountForm(Model model) {

        Client client = (Client)model.getAttribute("currentClient");
        model.addAttribute("client", client);
        model.addAttribute("account", new Account());
        return "updateAccountForm";
    }

    @RequestMapping("/updateAccount")
    public String updateAccount(@RequestParam("accountId") int accountId, Model model) {

        Client client = (Client)model.getAttribute("currentClient");
        model.addAttribute("client", client);
        model.addAttribute("account", accountService.getOne(accountId));
        return "updateAccountForm";
    }

    @RequestMapping("/saveAccount")
    public String saveAccount(@ModelAttribute("account") Account account, Model model) {

        Client client = (Client)model.getAttribute("currentClient");
        account.setClient(client);
        accountService.saveOrUpdate(account);
        return "redirect:/account/listAccounts?clientId=" + client.getId();
    }

    @RequestMapping("/deleteAccount")
    public String deleteAccount(@RequestParam("accountId") int accountId, Model model) {

        Client client = (Client)model.getAttribute("currentClient");
        accountService.delete(accountId);
        return "redirect:/account/listAccounts?clientId=" + client.getId();
    }
}
