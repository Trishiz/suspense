package zw.co.nbs.suspense.fbeq.impl;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zw.co.nbs.suspense.fbeq.api.StatementGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
        for (SuspenseAccount suspenseAccount : suspenseAccount()) {
            final StringBuilder append = csvContent.append(suspenseAccount.getDate()).append(",").append(suspenseAccount.getReferenceId()).append(",").
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

   public List<Suspense>getSuspenseAccount() {
       String querry=
        List<Suspense> suspenseAccount=new ArrayList<>();
       try {
           (Connection connection = Fbeq.getConnection());
           PreparedStatement preparedStatement= connection.prepared Statement
                   ResultSet rs = preparedStatement executeQuerry()){

               while (rs.next()) {
                   String date = rs.getString("DATE");
                   String ref_id = rs.getString("REFERENCE_ID");
                   String date = rs.getString("PRINCIPAL");
                   String date = rs.getString("FEES");
                   String date = rs.getString("ROLLING_BALANCE");
               }
           }

       }
   }

    @Override
    public boolean generateExcelStatement(String fileName, String accountName) {
        return true;
    }

}