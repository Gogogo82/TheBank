package bankApp.controller;

import bankApp.entity.Account;
import bankApp.entity.Client;
import bankApp.service.AccountService;
import bankApp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/account")
@SessionAttributes("currentClient")
public class AccountController {

    private final AccountService accountService;
    private final ClientService clientService;

    @Autowired
    public AccountController(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }


    // pre-process form data
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        // trim whitespaces for any input string, and if no chars left, replace with null
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/listAccounts")
    public String listAccounts(@RequestParam("clientId") int id, Model model) {

        Client client = clientService.findById(id);
        model.addAttribute("currentClient", client);
        List<Account> accountList = accountService.findByClientId(id);
        model.addAttribute("accounts", accountList);
        model.addAttribute("client", client);
        return "listAccounts";
    }

    @RequestMapping("/addOrUpdateAccount")
    public String addOrUpdateAccount(@RequestParam("accountId") int accountId, Model model) {

        Client client = (Client) model.getAttribute("currentClient");
        model.addAttribute("client", client);
        Account account;

        if (accountId == -1)
            account = new Account();
        else
            account = accountService.findById(accountId);

        model.addAttribute("account", account);

        return "updateAccountForm";
    }

    @RequestMapping("/saveAccount")
    public String saveAccount(@Valid @ModelAttribute("account") Account account,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors())
            return "updateAccountForm";
        else {
            Client client = (Client) model.getAttribute("currentClient");
            account.setClient(client);
            accountService.save(account);
            return "redirect:/account/listAccounts?clientId=" + client.getId();
        }
    }

    @RequestMapping("/deleteAccount")
    public String deleteAccount(@RequestParam("accountId") int accountId, Model model) {

        accountService.delete(accountId);
        Client client = (Client) model.getAttribute("currentClient");

        return "redirect:/account/listAccounts?clientId=" + client.getId();
    }
}
