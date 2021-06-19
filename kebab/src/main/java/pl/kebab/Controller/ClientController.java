package pl.kebab.Controller;

import pl.kebab.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kebab.Repository.ClientRepository;

import javax.validation.Valid;

@Controller
public class ClientController {
    private ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Client client) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        clientRepository.save(client);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("client", client);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            client.setId(id);
            return "update-user";
        }
        clientRepository.save(client);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        clientRepository.delete(client);
        return "redirect:/index";
    }
}


