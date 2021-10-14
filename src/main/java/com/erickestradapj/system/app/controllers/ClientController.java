package com.erickestradapj.system.app.controllers;

import com.erickestradapj.system.app.models.dao.IClientDao;
import com.erickestradapj.system.app.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class ClientController {

    @Autowired
    private IClientDao clientDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("title", "Customer List");
        model.addAttribute("clients", clientDao.findAll());

        return "list";
    }

    @RequestMapping(value = "/form")
    public String create(Map<String, Object> model) {
        Client client = new Client();
        model.put("title", "Client Form");
        model.put("client", client);

        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String save(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Client Form");
            return "form";
        }

        clientDao.save(client);
        return "redirect:/list";
    }
}
