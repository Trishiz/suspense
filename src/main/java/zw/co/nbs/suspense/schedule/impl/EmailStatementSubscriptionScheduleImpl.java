package zw.co.nbs.suspense.schedule.impl;


import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import zw.co.nbs.suspense.fbeq.api.StatementGenerator;
import zw.co.nbs.suspense.schedule.api.EmailStatementSubscriptionSchedule;

import java.util.concurrent.atomic.AtomicBoolean;
import static sun.security.jgss.GSSToken.debug;
public class EmailStatementSubscriptionScheduleImpl implements EmailStatementSubscriptionSchedule {


    private final AtomicBoolean busy = new AtomicBoolean(false);
    private final StatementGenerator statementGenerator;

    public EmailStatementSubscriptionScheduleImpl(final ApplicationContext context) {
        this.statementGenerator = context.getBean(StatementGenerator.class);
    }

    @Override
    @Scheduled(cron = "0 8 * * *")
    public void atSchedule() {

        try {
            submitSchedule();
        } catch (InterruptedException ex) {
            Logger log = null;
            log.error("Error in starting the statement sending job.......{}", ex.toString());
        }
    }
    @Override
    public void submitSchedule() throws InterruptedException {
        if (!busy.compareAndSet(false, true)) {
            debug("Another statement service still executing.......");
        }
    }
}