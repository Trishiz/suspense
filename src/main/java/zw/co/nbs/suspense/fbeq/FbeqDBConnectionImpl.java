package zw.co.nbs.suspense.fbeq;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.sql.ResultSet;

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

    @Override
    public ResultSet executeQuery(String sqlString) {
        return null;



    }
}


