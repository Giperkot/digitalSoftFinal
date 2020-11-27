package ru.perfolenta.dviz.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Getter
@Setter
@NoArgsConstructor
public class Attribute {
    @Id
    @GeneratedValue
    private Long id;

    private String mnemonic;
    private String name;
    private String description;
}
