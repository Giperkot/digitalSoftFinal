package ru.perfolenta.dviz.service;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Query;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perfolenta.dviz.dto.*;
import ru.perfolenta.dviz.dto.showcase.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DataService {

    private static final Pattern UPPER_PATTERN = Pattern.compile("([ЁА-Я])");

    private final Driver driver;

    @Autowired
    public DataService(Driver driver) {
        this.driver = driver;
    }

    public DataShowcaseDto getDataShowcase () {

        DataShowcaseDto result = new DataShowcaseDto();

        try (Session session = driver.session()) {

            result.getAttributes().addAll(session.run("match (n:Attributes) return n;")
                    .list(r -> {
                        Node m = r.get("n").asNode();

                        return  new AttributeDto(m.get("access_level").asString(),
                                m.get("name").asString(),
                                m.get("comment").asString(),
                                m.get("id").asString(),
                                m.get("type").asString(),
                                m.get("hashtag").asString());
                    }));

            result.getOrgs().addAll(session.run("match (n:Orgs) return n;")
                    .list(r -> {
                        Node m = r.get("n").asNode();

                        return  new OrgDto(m.get("name").asString(),
                                m.get("comment").asString(),
                                m.get("id").asString(),
                                m.get("hashtag").asString());
                    }));

            result.getDocuments().addAll(session.run("match (n:Documents) return n;")
                    .list(r -> {
                        Node m = r.get("n").asNode();

                        return new DocumentDto(m.get("access_level").asString(),
                                m.get("name").asString(),
                                m.get("comment").asString(),
                                m.get("id").asString(),
                                m.get("hashtag").asString());
                    }));


            result.getGovDepartment().addAll(session.run("match (n:GovDepartment) return n;")
                    .list(r -> {
                        Node m = r.get("n").asNode();

                        return new GovDepertmentDto(m.get("dwhuri").asString(),
                                m.get("name").asString(),
                                m.get("comment").asString(),
                                m.get("id").asString(),
                                m.get("uri").asString(),
                                m.get("hashtag").asString());
                    }));


            result.getInfSystem().addAll(session.run("match (n:InfSystem) return n;")
                    .list(r -> {
                        Node m = r.get("n").asNode();

                        return new InfSystemDto(m.get("access_level").asString(),
                                m.get("name").asString(),
                                m.get("comment").asString(),
                                m.get("id").asString(),
                                m.get("hashtag").asString());
                    }));

            result.getPersons().addAll(session.run("match (n:Persons) return n;")
                    .list(r -> {
                        Node m = r.get("n").asNode();

                        return new PersonDto(m.get("name").asString(),
                                m.get("comment").asString(),
                                m.get("id").asString(),
                                m.get("hashtag").asString());
                    }));

        }

        return result;
    }

    public static String handleCamelCase (String source) {
        List<String> upperCharList = new ArrayList<>();

        Matcher matcher = UPPER_PATTERN.matcher(source);

        while (matcher.find()) {
            upperCharList.add(matcher.group(1));
        }

        if (!upperCharList.isEmpty()) {

            String[] stringArr = UPPER_PATTERN.split(source, 0);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < stringArr.length; i++) {

                stringBuilder.append(stringArr[i]);

                if (i == stringArr.length - 1) {
                    continue;
                }

                if (i == 0) {
                    stringBuilder.append(upperCharList.get(i));
                    continue;
                }

                char upperChar = upperCharList.get(i).charAt(0);

                if (upperChar == 'Ё') {
                    upperChar = 'ё';
                } else {
                    upperChar = (char)(upperChar + 32);
                }


                stringBuilder.append(" ").append(upperChar);
            }

            return stringBuilder.toString();
        }

        return source;
    }

    public List<OuDto> getOus() {

        try (Session session = driver.session()) {
            return session.run("MATCH (n) RETURN n limit 25")
                    .list(r -> {
                        Node m = r.get("n").asNode();
                        String label = m.get("label").asString();

                        String clearLabel = handleCamelCase(label);

                        return  new OuDto(m.get("name").asString(), clearLabel, m.get("uri").asString());
                    });
        }
    }

    public List<RelationNodeDto> relationGraphModel() {
        try (Session session = driver.session()) {
            return session.run("match (n)-[r*1..3]->(m:GovDepartment {id: \"PFR\"}) return n,m")
                    .list(r -> {
                        Node m = r.get("n").asNode();

                        return  new RelationNodeDto(m.get("dwhuri").asString(),
                                m.get("name").asString(),
                                m.get("comment").asString(),
                                m.get("id").asString(),
                                m.get("uri").asString(),
                                m.get("hashtag").asString());
                    });
        }
    }


    public List<SearchResultDto> fullTextSearch (SimpleMessageDto dto) {

        String message = dto.getMessage();

        String searchMessage = message.replaceAll(" ", "*");

        String query = "CALL db.index.fulltext.queryNodes(\"AttrIndex\", \"name:*${query}*\") YIELD node RETURN node\n" +
                "union all\n" +
                "CALL db.index.fulltext.queryNodes(\"ISIndex\", \"name:*${query}*\") YIELD node RETURN node\n" +
                "union all\n" +
                "CALL db.index.fulltext.queryNodes(\"PersIndex\", \"name:*${query}*\") YIELD node RETURN node\n" +
                "union all\n" +
                "CALL db.index.fulltext.queryNodes(\"OrgIndex\", \"name:*${query}*\") YIELD node RETURN node\n" +
                "union all\n" +
                "CALL db.index.fulltext.queryNodes(\"Docs\", \"name:*${query}*\") YIELD node RETURN node\n" +
                "union all\n" +
                "CALL db.index.fulltext.queryNodes(\"GDIndex\", \"name:*${query}*\") YIELD node RETURN node;";


        String finalQuery = query.replaceAll("\\$\\{query\\}", searchMessage);

        try (Session session = driver.session()) {
            return session.run(finalQuery)
                    .list(r -> {
                        Node m = r.get("node").asNode();

                        return  new SearchResultDto(m.get("name").asString(),
                                m.get("comment").asString(),
                                m.get("id").asString(),
                                m.get("hashtag").asString());
                    });
        }

    }

    public String searchNode(String searchEntity){
        Session session = driver.session();
        Map<String,Object> params = new HashMap<>();
        params.put( "name", searchEntity);
        Query query = new Query("MATCH (n) WHERE n.label=$name RETURN n", params);
        Result r = session.run(query);
        return r.toString();
    }


}
