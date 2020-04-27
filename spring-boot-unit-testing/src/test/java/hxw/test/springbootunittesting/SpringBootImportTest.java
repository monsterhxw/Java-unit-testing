package hxw.test.springbootunittesting;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import other.namespace.Foo;

@SpringBootTest
@ActiveProfiles("test")
@Import(other.namespace.Foo.class)
class SpringBootImportTest {

    @Autowired
    private Foo foo;

    @Test
    void test() {
        assertThat(foo).isNotNull();
    }
}
