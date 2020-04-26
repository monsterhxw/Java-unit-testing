package hxw.test.springbootunittesting.service.impl;

import hxw.test.springbootunittesting.domain.SaveUserPort;
import hxw.test.springbootunittesting.domain.SendMailPort;
import hxw.test.springbootunittesting.domain.User;
import hxw.test.springbootunittesting.service.UserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SaveUserPort saveUserPort;

    private final SendMailPort sendMailPort;

    @Override
    public Long registerUser(User user, boolean sendWelcomeMail) {
        user.setRegistrationDate(LocalDateTime.now());

        if (sendWelcomeMail) {
            sendMailPort.sendMail("Welcome!", "Thanks for registering.");
        }

        return saveUserPort.saveUser(user);
    }
}
