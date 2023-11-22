package zw.co.nbs.suspense.fbeq.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zw.co.nbs.suspense.fbeq.api.FbeqDBConnection;
import zw.co.nbs.suspense.fbeq.api.StatementGenerator;
import zw.co.nbs.suspense.fbeq.impl.FbeqDBConnectionImpl;
import zw.co.nbs.suspense.fbeq.impl.StatementGeneratorImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FbeqConfig {

    @Bean
    public FbeqDBConnection fbeqDBConnection() {
        return new FbeqDBConnectionImpl();
    }

    @Bean
    public StatementGenerator statementGenerator(final ApplicationContext context) {
        return new StatementGeneratorImpl(context);
    }

}
