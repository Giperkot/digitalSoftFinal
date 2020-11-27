package ru.perfolenta.dviz.controller;


import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perfolenta.dviz.dto.OuDto;

import java.util.List;

@RestController
public class OuController {
    private final Driver driver;

    public OuController(Driver driver) {
        this.driver = driver;
    }

    @GetMapping(path = "/ou", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OuDto> getOus() {

        try (Session session = driver.session()) {
            return session.run("MATCH (n) RETURN n limit 25")
                    .list(r -> {
                        Node m = r.get("n").asNode();
                        return  new OuDto(m.get("name").asString(), m.get("label").asString(), m.get("uri").asString());
                    });
        }
    }
}
