package ru.perfolenta.dviz.service;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perfolenta.dviz.dto.OuDto;

import java.util.List;

@Service
public class DataService {

    private final Driver driver;

    @Autowired
    public DataService(Driver driver) {
        this.driver = driver;
    }

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
