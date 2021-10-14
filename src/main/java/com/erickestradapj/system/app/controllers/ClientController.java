package com.erickestradapj.system.app.controllers;

import com.erickestradapj.system.app.models.entity.Client;
import com.erickestradapj.system.app.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("title", "Customer List");
        model.addAttribute("clients", clientService.findAll());

        return "list";
    }

    @RequestMapping(value = "/form")
    public String create(Map<String, Object> model) {
        Client client = new Client();
        model.put("title", "Client Form");
        model.put("client", client);

        return "form";
    }

    @RequestMapping(value = "/form/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Client client = null;

        if (id > 0) {
            client = clientService.findById(id);
        } else {
            return "redirect:/list";
        }

        model.addAttribute("title", "Edit Client");
        model.addAttribute("client", client);
        return "form";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (id > 0) {
            clientService.delete(id);
        }

        return "redirect:/list";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String save(@Valid Client client, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Client Form");
            return "form";
        }

        clientService.save(client);
        status.setComplete();
        return "redirect:/list";
    }
}
