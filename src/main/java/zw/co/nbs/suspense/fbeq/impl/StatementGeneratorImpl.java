package zw.co.nbs.suspense.fbeq.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import zw.co.nbs.suspense.fbeq.api.StatementGenerator;

import javax.transaction.Transaction;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class StatementGeneratorImpl implements StatementGenerator {
    public StatementGeneratorImpl(ApplicationContext context) {

    }

    public String generateTransaction(List<Transaction> transactions) throws IOException {
        String fileName = "statement.csv";

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            CSVWriter writer = new CSVWriter(fileWriter);

            String[] header = {"Date", "ReferenceId", "Principal", "Fees", "rollingBalance"};
            writer.writeNext(header);

            for (Transaction transaction : transactions) {
                String[] transactionRow = {transaction.getDate(), transaction.getDRefereneId(), transaction.getAmount()};
                writer.writeNext(transactionRow);
            }
        }
    }