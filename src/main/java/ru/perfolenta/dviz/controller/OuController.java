package ru.perfolenta.dviz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.perfolenta.dviz.dto.OuDto;
import ru.perfolenta.dviz.dto.RelationNodeDto;
import ru.perfolenta.dviz.dto.SimpleMessageDto;
import ru.perfolenta.dviz.service.DataService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @GetMapping("/relationGraphModel")
    public List<RelationNodeDto> relationGraphModel(Model model) {

        return dataService.relationGraphModel();
    }

    @RequestMapping(value = "/doFullTextSearch", method = RequestMethod.POST)
    public String fullTextSearch (@RequestBody SimpleMessageDto dto) throws IOException {

        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("static/json/fullTextAnsExample.json");
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            int byteOne;

            while ((byteOne = is.read()) != -1) {
                baos.write(byteOne);
            }

            return new String(baos.toByteArray());
        }
    }

}
