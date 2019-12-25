package bankApp.controller;

import bankApp.entity.Client;
import bankApp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/client")
public class ClientsController {

    private final ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping("/listClients")
    public String listClients(Model model) {
        model.addAttribute("clientsList", clientService.getAll());
        return "listClients";
    }

    @RequestMapping("/addNewClient")
    public String showUpdateForm(Model model) {
        model.addAttribute("client", new Client());
        return "updateClientForm";
    }

    @RequestMapping("/updateClient")
    public String updateClient(@RequestParam("clientId") int id, Model model) {
        Client clientFromDB = clientService.getOne(id);
        model.addAttribute("client", clientFromDB);
        return "updateClientForm";
    }

    @RequestMapping("/saveClient")
    public String saveClient(@ModelAttribute("client") Client client){
        clientService.saveOrUpdate(client);
        return "redirect:/client/listClients";
    }

    @RequestMapping("/deleteClient")
    public String deleteClient(@RequestParam("clientId") int id) {
        clientService.delete(id);
        return "redirect:/client/listClients";
    }

    @RequestMapping("redirectToAccountController")
    public String listAccounts (@RequestParam("clientId") int id, Model model) {
        model.addAttribute("client", clientService.getOne(id));
        return "listAccounts";
    }
}
