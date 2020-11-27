package ru.perfolenta.dviz;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yandex.clickhouse.ClickHouseConnection;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties (prefix = "kas")
@Setter
public class YAMLConfig {
    private String name;
    private String environment;
    private boolean enabled;
    private List<String> servers = new ArrayList<>();

    private String address;
    private String username;
    private String password;
    private String db;
    private Integer socketTimeout;

//    @Bean
//    public Supplier<Connection> clickHouseConnectionFactory() {
//
//        Supplier<Connection> factory = () -> {
//            ClickHouseConnection conn = null;
//            ClickHouseProperties properties = new ClickHouseProperties();
//            properties.setUser(username);
//            properties.setPassword(password);
//            properties.setDatabase(db);
//            properties.setSocketTimeout(socketTimeout);
//            ClickHouseDataSource clickHouseDataSource = new ClickHouseDataSource(address, properties);
//            try {
//                conn = clickHouseDataSource.getConnection();
//                return conn;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return conn;
//        };
//        return factory;
//    }
}
