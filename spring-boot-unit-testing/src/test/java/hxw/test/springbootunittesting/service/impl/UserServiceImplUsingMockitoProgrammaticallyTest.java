package hxw.test.springbootunittesting.service.impl;

import static hxw.test.springbootunittesting.utils.UserAssert.assertThat;

import hxw.test.springbootunittesting.entity.UserEntity;
import hxw.test.springbootunittesting.repository.UserRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplUsingMockitoProgrammaticallyTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private UserServiceImpl userService;

    @BeforeEach
    void initCase() {
        userService = new UserServiceImpl(userRepository);
    }

    /**
     * using mockito programmatically
     */
    @Test
    void savedUserHasRegistrationDateWithProgrammatically() {
        UserEntity user = new UserEntity("huangxuewei", "huangxueweihxw@gmail.com");
        user.setRegistrationDate(LocalDateTime.now());
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class)))
            .then((Answer<UserEntity>) invocation -> user);
        UserEntity savedUser = userService.registerUser(user);

        assertThat(savedUser).hasRegistrationDate();
    }
}