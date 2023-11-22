package zw.co.nbs.suspense.schedule.api;
public interface EmailStatementSubscriptionSchedule {

    void atSchedule();

    void submitSchedule() throws InterruptedException;
}
