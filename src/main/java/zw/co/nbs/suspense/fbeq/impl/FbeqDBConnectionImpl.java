package zw.co.nbs.suspense.fbeq.impl;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import zw.co.nbs.suspense.fbeq.api.FbeqDBConnection;

import java.sql.*;

@NoArgsConstructor
public class FbeqDBConnectionImpl implements FbeqDBConnection {

    @Value("${spring.secondary-datasource.driver-class-name}")
    private String jdbcClassName;

    @Value("${spring-datasource.url}")
    private String url;

    @Value("${spring-datasource.username}")
    private String username;

    @Value("${spring-datasource.password}")
    private String password;
    public static Connection conn;


    private Connection openConn() throws Exception {
        if (conn == null || conn.isClosed()) {
            return conn;
        }

        public ResultSet executeQuery (String sqlQuery)
        {
            ResultSet rs = null;

            return (Connection) rs;
        }
    }

    @Override
    public ResultSet executeQuery(String sqlString) {
        return null;
    }
}