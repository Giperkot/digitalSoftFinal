package ru.perfolenta.dviz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perfolenta.dviz.dto.OuDto;
import ru.perfolenta.dviz.service.DataService;

import java.util.List;

@RestController
public class OuController {

    private DataService dataService;

    @Autowired
    public OuController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping(path = "/ou", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OuDto> getOus() {

        return dataService.getOus();
    }
}
