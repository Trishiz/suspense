package zw.co.nbs.suspense.schedule.impl;

import org.springframework.scheduling.annotation.Scheduled;

public interface EmailStatementSubscriptionScheduleIImpl {
    @Scheduled(cron = "0 0 0 * * ?")
    void atSchedule();

    void submitSchedule() throws InterruptedException;
}
