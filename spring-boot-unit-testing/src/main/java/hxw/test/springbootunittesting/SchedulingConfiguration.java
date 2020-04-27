package hxw.test.springbootunittesting;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(
    name = "hxw.test.springbootunittesting.scheduling.enabled",
    havingValue = "true",
    matchIfMissing = true
)
public class SchedulingConfiguration {
}
