package zw.co.nbs.suspense.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import zw.co.nbs.suspense.fbeq.config.FbeqConfig;

@SpringBootApplication
@Import(FbeqConfig.class)
public class SuspenseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuspenseApplication.class, args);
    }



}
