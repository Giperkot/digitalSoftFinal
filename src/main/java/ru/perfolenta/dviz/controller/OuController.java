package ru.perfolenta.dviz.controller;


import ru.perfolenta.dviz.dto.OuDto;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return session.run("MATCH (m:Ou) RETURN m ORDER BY m.name ASC")
                    .list(r -> {
                        Node m = r.get("m").asNode();
                        return  new OuDto(m.get("name").asString(), m.get("mnemonic").asString(), m.get("description").asString(), m.get("imgUrl").asString());
                    });
        }
    }
}
