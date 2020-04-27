package hxw.test.springbootunittesting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import hxw.test.springbootunittesting.domain.User;
import hxw.test.springbootunittesting.persistence.entity.UserEntity;
import hxw.test.springbootunittesting.persistence.repository.UserRepository;
import hxw.test.springbootunittesting.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class MockBeanTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void testRegister() {
        // given
        User user = new User("huangxuewei", "huangxueweihxw@gmail.com");

        boolean sendWelcomeMail = true;

        given(userRepository.save(any(UserEntity.class))).willReturn(userEntity(1L));

        //when
        Long userId = userService.registerUser(user, sendWelcomeMail);

        //then
        assertThat(userId).isEqualTo(1L);
    }

    private UserEntity userEntity(Long id) {
        return new UserEntity(id, "huangxuaewei", "huangxueweihxw@gmail.com", LocalDateTime.now());
    }
}
