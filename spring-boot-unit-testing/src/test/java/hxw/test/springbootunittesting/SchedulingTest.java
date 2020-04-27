package hxw.test.springbootunittesting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "hxw.test.springbootunittesting.scheduling.enabled=false")
class SchedulingTest {

    @Autowired(required = false)
    private SchedulingConfiguration schedulingConfiguration;

    @Test
    void test() {
        assertThat(schedulingConfiguration).isNull();
    }
}
