package hxw.test.springbootunittesting.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * As of Spring Boot 2.1, we no longer need to load the SpringExtension because it's included as a
 * meta annotation in the Spring Boot test annotations like @DataJpaTest, @WebMvcTest, and @SpringBootTest.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(userRepository).isNotNull();
    }
}