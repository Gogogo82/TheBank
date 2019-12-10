package bankApp.controller;

import bankApp.entity.Client;
import bankApp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/showUpdateForm")
    public String showUpdateForm(Model model) {
        model.addAttribute("client", new Client());
        return "updateClientForm";
    }

    @RequestMapping("/saveClient")
    public String saveClient(@ModelAttribute("client") Client client){
        bankService.saveOrUpdate(client);
        return "redirect:/listClients";
    }

    @RequestMapping("/updateClient")
    public String updateClient(@RequestParam("clientId") int id, Model model) {
        Client clientFromDB = bankService.getOne(id);
        System.out.println("id: " + id);
        System.out.println(clientFromDB);
        model.addAttribute("client", clientFromDB);
        return "updateClientForm";
    }

    @RequestMapping("/deleteClient")
    public String deleteClient(@RequestParam("clientId") int id) {
        bankService.delete(id);
        return "redirect:/listClients";
    }
}
