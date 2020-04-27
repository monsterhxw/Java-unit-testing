package hxw.test.springbootunittesting;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.flyway.enabled=false",
    "spring.liquibase.enabled=false",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class SpringBootUnitTestingApplicationTests {

    @Test
    void contextLoads() {
    }
}
