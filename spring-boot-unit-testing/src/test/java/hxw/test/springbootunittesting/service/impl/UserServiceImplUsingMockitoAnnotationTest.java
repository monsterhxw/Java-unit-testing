package hxw.test.springbootunittesting.service.impl;

import static hxw.test.springbootunittesting.utils.UserAssert.assertThat;

import hxw.test.springbootunittesting.entity.UserEntity;
import hxw.test.springbootunittesting.repository.UserRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplUsingMockitoAnnotationTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    /**
     * using mockito @Mock annotation
     */
    @Test
    void savedUserHasRegistrationDateWithAnnotation() {
        UserEntity user = new UserEntity("huangxuewei", "huangxueweihxw@gmail.com");
        user.setRegistrationDate(LocalDateTime.now());
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class)))
            .then((Answer<UserEntity>) invocation -> user);
        UserEntity savedUser = userService.registerUser(user);

        assertThat(savedUser).hasRegistrationDate();
    }
}