package zw.co.nbs.suspense.fbeq.impl;

import antlr.ASTNULLType;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zw.co.nbs.suspense.fbeq.api.StatementGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class StatementGeneratorImpl implements StatementGenerator {
    public StatementGeneratorImpl(ApplicationContext context) {
    }

    public <SuspenseAccount> void generateStatement() {

        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Date");
        csvContent.append("ReferenceId");
        csvContent.append("Principal");
        csvContent.append("Fees");
        csvContent.append("RollingBalance");
        for (SuspenseAccount suspenseAccount : suspenseAccounts()) {
            csvContent.append(suspenseAccount.getDate()).append(",").append(suspenseAccount.getReferenceId()).append(",").
                    append(suspenseAccount.getPrincipal()).append(",").append(suspenseAccount.getFees).append(",").append(suspenseAccount.getRollingBalance());

        }

        String fileName = "SuspenseAccountStatement.csv";
        Path path = Paths.get(fileName);
        try {
            Files.write(path, csvContent.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean generateExcelStatement(String fileName, String accountName) {
        return true;
    }

}