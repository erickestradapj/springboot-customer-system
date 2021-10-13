package com.erickestradapj.system.app.controllers;

import com.erickestradapj.system.app.models.dao.IClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientController {

    @Autowired
    private IClientDao clientDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("title", "Customer list");
        model.addAttribute("clients", clientDao.findAll());

        return "list";
    }
}
