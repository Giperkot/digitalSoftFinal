package ru.perfolenta.dviz.service;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perfolenta.dviz.dto.OuDto;

import java.util.ArrayList;
import java.util.List;
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

}
