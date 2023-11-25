package zw.co.nbs.suspense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class StatementGenerator {
	private static final String JDBC_URL = "jdbc:mysql://192.168.1.68:3306/betdb4?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Gracedube80";

	@SpringBootApplication
	public static class SuspenseApplication {

		public static void main(String[] args) {
			SpringApplication.run(SuspenseApplication.class, args);
		}
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				generateStatement();
			}
		};
	}
	private static void generateStatement() {
		String sqlQuery = "SELECT SAPF.SAPOD AS date," +
				"           CAST(SAPF.SAPBR AS VARCHAR(500) CCSID 37) AS Reference_Id," +
				"           CAST(SAPF.SACCY AS VARCHAR(500) CCSID 37) AS Currency," +
				"           SAPF.SAAMA / 100 AS Principal," +
				"           SAPF.SAAMA * 0.01 /100 AS Fees" +
				"    FROM KFILNBS.SAPF\n" +
				"    WHERE SAPF.SAAB || SAPF.SAAN || SAPF.SAAS = '3202811346840'";
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

		String fileName = "statement" + dateFormat.format(new Date()) + ".csv";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(sqlQuery);
			 FileWriter writer = new FileWriter(fileName)) {
			writer.append("Date,Reference_Id,Principal,Fees,Rolling_balance\n");

			while (resultSet.next()) {
				String referenceId = resultSet.getString("reference_id");
				double principal = resultSet.getDouble("principal");
				double fees = resultSet.getDouble("fees");
				String rollingBalance = resultSet.getString("rolling_balance");
				Date date = resultSet.getDate("date");

				SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy");
				String formattedDate = dateFormatter.format(date);

				writer.append(referenceId).append(",").append(String.valueOf(principal)).append(",")
						.append((char) fees).append(",").append(rollingBalance).append(",").append(formattedDate).append("\n");
			}

			System.out.println("Statement generated successfully.");

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	private static long calculateDelay() {
		long currentTime = System.currentTimeMillis();
		int desiredHour = 8;
		int desiredMinute = 0;
		int desiredSecond = 0;

		Date desiredExecutionTime = new Date();
		desiredExecutionTime.setHours(desiredHour);
		desiredExecutionTime.setMinutes(desiredMinute);
		desiredExecutionTime.setSeconds(desiredSecond);

		if (currentTime > desiredExecutionTime.getTime()) {
			desiredExecutionTime.setDate(desiredExecutionTime.getDate() + 1);
		}
		return desiredExecutionTime.getTime() - currentTime;
	}
}