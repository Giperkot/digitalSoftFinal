package ru.perfolenta.dviz.controller;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.perfolenta.dviz.dto.OuDto;
import ru.perfolenta.dviz.service.DataService;

import java.util.List;

@Controller
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";

    private DataService dataService;

    @Autowired
    public GreetingController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {

        List<OuDto> ouList = dataService.getOus();
        model.addAttribute("modelRecordList", ouList);

        return "/index";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }
}