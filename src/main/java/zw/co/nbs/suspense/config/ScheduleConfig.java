package zw.co.nbs.suspense.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import zw.co.nbs.suspense.schedule.api.EmailStatementSubscriptionSchedule;
import zw.co.nbs.suspense.schedule.impl.EmailStatementSubscriptionScheduleImpl;

@Configuration
@EnableScheduling
public class ScheduleConfig {
    @Bean
    public EmailStatementSubscriptionSchedule emailStatementSubscriptionSchedule(final ApplicationContext context) {
        return new EmailStatementSubscriptionScheduleImpl(context);
    }

}