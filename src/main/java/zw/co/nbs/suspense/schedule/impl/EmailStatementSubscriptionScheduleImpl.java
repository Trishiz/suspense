package zw.co.nbs.suspense.schedule.impl;


import ch.qos.logback.classic.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;


import zw.co.nbs.suspense.fbeq.api.StatementGenerator;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class EmailStatementSubscriptionScheduleImpl implements EmailStatementSubscriptionScheduleIImpl {

    @Value("${spring.servlet.multipart.location}")
    private String uploadLocation;

    @Value("${scheduled-tasks.service.statement-subscriptions.cc}")
    private String ccTo;

    @Value("${statement-subscriptions.email}")
    private String statementEmail;

    @Value("${bfeq.statement.date-format}")
    private String statementDateFormat;

    private final AtomicBoolean busy = new AtomicBoolean(false);
    private final Environment environment;
    private final StatementGenerator statementGenerator;

//
    /
    public EmailStatementSubscriptionScheduleImpl(final ApplicationContext context) {
        this.environment = context.getBean(Environment.class);
        this.statementGenerator = context.getBean(StatementGenerator.class);
    }

    @Override
    @Scheduled(cron = "${scheduled-tasks.service.statement-subscriptions.schedule}")
    public void atSchedule() {
        boolean enabled = false;
        try {
            enabled = Boolean.parseBoolean(environment.getProperty("scheduled-tasks.service.statement-subscriptions.enabled"));
        } catch (Exception ignored) {
        }
        try {
            if (enabled) {
                submitSchedule();
            }
        } catch (InterruptedException ex) {
            Logger log = null;
            log.error("Error in starting the statement sending job.......{}", ex.toString());
        }
    }

    @Override
    public void submitSchedule() throws InterruptedException {
        if (!busy.compareAndSet(false, true)) {
            log.debug("Another statement service still executing.......");
            return;
        }
    }