package hxw.test.springbootunittesting.service;

import hxw.test.springbootunittesting.domain.User;

public interface UserService {

    Long registerUser(User user, boolean sendWelcomeMail);
}
