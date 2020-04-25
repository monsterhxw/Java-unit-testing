package hxw.test.springbootunittesting.service.impl;

import hxw.test.springbootunittesting.entity.UserEntity;
import hxw.test.springbootunittesting.repository.UserRepository;
import hxw.test.springbootunittesting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserEntity registerUser(UserEntity user) {
        return userRepository.save(user);
    }
}
