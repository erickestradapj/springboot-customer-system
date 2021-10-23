package com.erickestradapj.system.app.controllers;

import com.erickestradapj.system.app.models.entity.Client;
import com.erickestradapj.system.app.services.IClientService;
import com.erickestradapj.system.app.util.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Controller
@SessionAttributes("client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @RequestMapping(value = "/list")
    public String list(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 4);

        Page<Client> clients = clientService.findAll(pageRequest);

        PageRender<Client> pageRender = new PageRender<>("/list", clients);

        model.addAttribute("title", "Customer List");
        model.addAttribute("clients", clients);
        model.addAttribute("page", pageRender);

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
    public String edit(@PathVariable Long id, Model model, RedirectAttributes flash) {

        Client client = null;

        if (id > 0) {
            client = clientService.findById(id);
            if (client == null) {
                flash.addFlashAttribute("error", "Customer ID doesn't exist in the DB");
                return "redirect:/list";
            }
        } else {
            flash.addFlashAttribute("error", "Customer ID cannot be zero!");
            return "redirect:/list";
        }

        model.addAttribute("title", "Edit Client");
        model.addAttribute("client", client);
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String save(@Valid Client client, BindingResult result, Model model, @RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Client Form");
            return "form";
        }

        if (!photo.isEmpty()) {
            Path pathResources = Paths.get("src//main//resources//static/uploads");
            String rootPath = pathResources.toFile().getAbsolutePath();
            try {
                byte[] bytes = photo.getBytes();
                Path pathComplete = Paths.get(rootPath + "//" + photo.getOriginalFilename());
                Files.write(pathComplete, bytes);
                flash.addFlashAttribute("info", "Uploaded successfully: " + photo.getOriginalFilename());

                client.setPhoto(photo.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String msgFlash = (client.getId() != null) ? "Customer edited successfully" : "Customer created successfully";

        clientService.save(client);
        status.setComplete();
        flash.addFlashAttribute("success", msgFlash);
        return "redirect:/list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            clientService.delete(id);
            flash.addFlashAttribute("success", "Customer deleted successfully");
        }

        return "redirect:/list";
    }
}
