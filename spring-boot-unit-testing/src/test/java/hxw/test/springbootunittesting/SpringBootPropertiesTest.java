package hxw.test.springbootunittesting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.flyway.enabled=false",
    "spring.liquibase.enabled=false",
    "foo=bar"
})
class SpringBootPropertiesTest {

    @Value("${foo}")
    String foo;

    @Test
    void test() {
        assertThat(foo).isEqualTo("bar");
    }
}