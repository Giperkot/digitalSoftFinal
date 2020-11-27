package ru.perfolenta.dviz.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Getter
@Setter
public class Ou {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String mnemonic;
    private String imgUrl;

    private Ou() {
        // Empty constructor required as of Neo4j API 2.0.5
    };
}
