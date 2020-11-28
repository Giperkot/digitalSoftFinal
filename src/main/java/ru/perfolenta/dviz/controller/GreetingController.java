package ru.perfolenta.dviz.controller;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/data-model")
    public String dataModel(Model model) {
//        List<Record> lst = dataService.getDataModelFias();
//        model.addAttribute("FIAS", lst);
        return "/data-model";
    }

    @GetMapping("/search-clear")
    public String searchClear(Model model) {

        return "/search-clear";
    }

    /*@GetMapping("/relationGraphModel")
    public String relationGraphModel(Model model) {



        return "/relationGraphModel";
    }*/

    @GetMapping("/fullTextSearch")
    public String fullTextSearch(Model model) {
//        model.addAttribute("dataShowcase", dataService.getDataShowcase());
        return "/fullTextSearch";
    }

    @GetMapping("/dataShowcase")
    public String dataShowcase(Model model) {

        model.addAttribute("dataShowcase", dataService.getDataShowcase());

        return "/dataShowcase";
    }


    @PostMapping("/search-clear/search-entity")
    public String searchClear(@RequestParam String searchId) {
        return dataService.searchNode(searchId);
    }
}