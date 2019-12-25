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

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/account")
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

//        System.out.println("@RequestMapping(\"/listAccounts\"");
        Client client = clientService.getOne(id);
//        System.out.println(client);
        List<Account> accountList = accountService.getByClientId(id);
//        accountList.forEach(System.out::println);
        model.addAttribute("accounts", accountList);
        model.addAttribute("client", client);
        return "listAccounts";
    }

    @RequestMapping("/addNewAccount")
    public String showUpdateAccountForm(@RequestParam("clientId") int id,
                                        @RequestParam("clientName") String name,
                                        Model model) {
//        System.out.println();
//        System.out.println("/addNewAccount");
//        System.out.println();
        model.addAttribute("clientId", id);
        model.addAttribute("clientName", name);
        model.addAttribute("account", new Account());
        return "updateAccountForm";
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

    @RequestMapping("/saveAccount")
    public String saveAccount(@RequestParam("clientId") int clientId,
                              @ModelAttribute("account") Account account) {
//              account.setClient(clientService.getOne(clientId));
        account.setClient(clientService.getOne(clientId));
        accountService.saveOrUpdate(account);
        return "redirect:/account/listAccounts?clientId=" + clientId;
    }

    @RequestMapping("/deleteAccount")
    public String deleteAccount(@RequestParam("accountId") int accountId,
                                @RequestParam("clientId") int clientId) {

        // breaking bi-directional relationships
//        List<Account> accountList = clientService.getOne(clientId).getAccounts();
//        Iterator<Account> iterator = accountList.iterator();
//
//        while (iterator.hasNext()) {
//            if(iterator.next().getId() == accountId) {
//                iterator.remove();
//                accountList.forEach(System.out::println);
//                break;
//            }
//        }

        accountService.delete(accountId);

        return "redirect:/account/listAccounts?clientId=" + clientId;
    }
}
