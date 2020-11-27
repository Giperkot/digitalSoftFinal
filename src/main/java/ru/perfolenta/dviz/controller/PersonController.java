package ru.perfolenta.dviz.controller;

import org.neo4j.driver.AccessMode;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    private final Driver driver;

    public PersonController(Driver driver) {
        this.driver = driver;
    }

    @GetMapping(path = "/ou/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMovieTitles() {

        try (Session session = driver.session()) {
            return session.run("MATCH (m:Person) RETURN m ORDER BY m.name ASC")
                    .list(r -> r.get("m").asNode().get("snils").asString());
        }
    }
}
