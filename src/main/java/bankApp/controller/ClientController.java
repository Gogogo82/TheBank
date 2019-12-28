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
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping("/listClients")
    public String listClients(Model model) {
        model.addAttribute("clientsList", clientService.getAll());
        return "listClients";
    }

    @RequestMapping("/addOrUpdateClient")
    public String addOrUpdateClient(@RequestParam("clientId") int clientId, Model model) {

        Client client;

        if(clientId == -1)
            client = new Client();
        else
            client = clientService.findById(clientId);

        model.addAttribute("client", client);
        return "updateClientForm";
    }

    @RequestMapping("/saveClient")
    public String saveClient(@ModelAttribute("client") Client client){
        clientService.save(client);
        return "redirect:/client/listClients";
    }

    @RequestMapping("/deleteClient")
    public String deleteClient(@RequestParam("clientId") int id) {
        clientService.delete(id);
        return "redirect:/client/listClients";
    }
}
