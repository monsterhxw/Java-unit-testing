package hxw.test.springbootunittesting.service.impl;

import hxw.test.springbootunittesting.domain.SaveUserPort;
import hxw.test.springbootunittesting.domain.SendMailPort;
import hxw.test.springbootunittesting.domain.User;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplUsingMockitoProgrammaticallyTest {

    private SaveUserPort saveUserPort = Mockito.mock(SaveUserPort.class);

    private SendMailPort sendMailPort = Mockito.mock(SendMailPort.class);

    private UserServiceImpl userService;

    @BeforeEach
    void initCase() {
        userService = new UserServiceImpl(saveUserPort, sendMailPort);
    }

    /**
     * using mockito programmatically
     */
    @Test
    void savedUserHasRegistrationDateWithProgrammatically() {
        User user = new User("huangxuewei", "huangxueweihxw@gmail.com");
        user.setRegistrationDate(LocalDateTime.now());
        Mockito.when(saveUserPort.saveUser(Mockito.any(User.class))).then((Answer<Long>) a -> 42L);
        Long userId = userService.registerUser(user, false);

        Assertions.assertThat(userId).isNotNull();
    }
}