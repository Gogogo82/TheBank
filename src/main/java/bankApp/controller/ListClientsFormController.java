package bankApp.controller;

import bankApp.entity.Client;
import bankApp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListClientsFormController {

//       @Autowired
//    BankService<Account> bankServiceAccount;

    @Autowired
    BankService<Client> bankService;

//    @Autowired
//    BankService<Transaction> bankServiceTransaction;

    @RequestMapping("/listClients")
    public String listClients(Model model) {
        model.addAttribute("clientsList", bankService.getAll());
        return "listClients";
    }

//    @RequestMapping("/listAccounts")
//    public String showAccounts(){
//        return "listAccounts";
//    }
//
//    @RequestMapping("/updateClient")
//    public String updateClient() {
//        return "/updateForm";
//    }
//
//    @RequestMapping("/deleteClient")
//    public String deleteClient() {
//        return "listAccounts";
//    }
}
