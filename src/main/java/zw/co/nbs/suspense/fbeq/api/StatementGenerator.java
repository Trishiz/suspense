package zw.co.nbs.suspense.fbeq.api;

public interface StatementGenerator {

   boolean generateCSVStatement(String inputAccNumber, String startDate, String endDate, String fileName, String accountName);
}
