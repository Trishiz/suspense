package zw.co.nbs.suspense;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TransactionDataFetcher {

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                fetchAndSendData();
            }
        };
        long delay = calculateDelay();
        long interval = 24 * 60 * 60 * 1000;
        timer.schedule(task, delay, interval);
    }
    private static void fetchAndSendData() {
        System.out.println("Fetching and sending data for everyday transactions: " + new Date());
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