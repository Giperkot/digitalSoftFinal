package ru.perfolenta.dviz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.yandex.clickhouse.ClickHouseConnection;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.*;
import java.util.*;
import java.util.function.Supplier;

@Slf4j
@Component
public class ClickHouseService {
    @Autowired
    private Supplier<Connection> clickHouseConnectionFactory;


    public List<Map<String,String>> exeSql(String sql){
        log.info("cliockhouse sql：" + sql);
        Connection connection = clickHouseConnectionFactory.get();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            ResultSetMetaData rsmd = results.getMetaData();
            List<Map<String,String>> list = new ArrayList<>();
            while(results.next()){
                Map<String,String> row = new HashMap<>();
                for(int i = 1;i<=rsmd.getColumnCount();i++){
                    row.put(rsmd.getColumnName(i),results.getString(rsmd.getColumnName(i)));
                }
                list.add(row);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}