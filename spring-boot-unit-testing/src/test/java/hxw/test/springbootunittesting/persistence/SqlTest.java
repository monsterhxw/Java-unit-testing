package hxw.test.springbootunittesting.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import hxw.test.springbootunittesting.persistence.entity.UserEntity;
import hxw.test.springbootunittesting.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.liquibase.enabled=false",
    "spring.flyway.enabled=false"
})
class SqlTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql("createUser.sql")
    void whenInitializedByDbUnit_thenFindsByName() {
        UserEntity user = userRepository.findByName("huangxuewei");

        assertThat(user).isNotNull();
    }
}
