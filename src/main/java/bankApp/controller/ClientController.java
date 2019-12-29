package bankApp.controller;

import bankApp.entity.Client;
import bankApp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // pre-process form data
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        // trim whitespaces for any input string, and if no chars left, replace with null
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
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
    public String saveClient(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult){

        System.out.println("bindingResult: " + bindingResult);
        if (bindingResult.hasErrors())
            return "updateClientForm";
        else {
            clientService.save(client);
            return "redirect:/client/listClients";
        }
    }

    @RequestMapping("/deleteClient")
    public String deleteClient(@RequestParam("clientId") int id) {
        clientService.delete(id);
        return "redirect:/client/listClients";
    }
}
