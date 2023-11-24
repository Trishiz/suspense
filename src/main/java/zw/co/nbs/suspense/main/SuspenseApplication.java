package zw.co.nbs.suspense.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import zw.co.nbs.suspense.fbeq.config.FbeqConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@SpringBootApplication
@Import(FbeqConfig.class)
public class SuspenseApplication {
    private List<SuspenseAccount> suspenseAccount;
    private String filePath;
    private String fileName;

    public static void main(String[] args) {
        SpringApplication.run(SuspenseApplication.class, args);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/generate")
    public ResponseEntity<InputStreamResource> generateStatement() throws IOException {
        String sqlQuery = "";
        List<SuspenseAccount> suspenseAccount = jdbcTemplate.query(sqlQuery, new Object[]{},(SuspenseAccount.class));

        String fileName = "statement.csv";
        String filePath = "path/to/file/";


        Path path = null;
        path = generateCSVFile(suspenseAccount, filePath, fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(Files.newInputStream(path)));
    }

    private <SuspenseAccount> Path generateCSVFile(List<SuspenseAccount> suspenseAccount, String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        return null;
    }


}
