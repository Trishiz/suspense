package zw.co.nbs.suspense.schedule.impl;

import org.springframework.scheduling.annotation.Scheduled;

public interface EmailStatementSubscriptionScheduleIImpl {
    @Scheduled(cron = "${scheduled-tasks.service.statement-subscriptions.schedule}")
    void atSchedule();

    void submitSchedule() throws InterruptedException;
}
