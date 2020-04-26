package hxw.test.springbootunittesting.web.controllers;

import hxw.test.springbootunittesting.domain.User;
import hxw.test.springbootunittesting.service.UserService;
import hxw.test.springbootunittesting.web.vo.UserResource;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterRestController {

    private final UserService userService;

    @PostMapping("/forums/{forumId}/register")
    UserResource register(
        @PathVariable("forumId") Long forumId,
        @Valid @RequestBody UserResource userResource,
        @RequestParam("sendWelcomeMail") boolean sendWelcomeMail) {

        User user = new User(
            userResource.getName(),
            userResource.getEmail());
        Long userId = userService.registerUser(user, sendWelcomeMail);

        return new UserResource(
            user.getName(),
            user.getEmail());
    }
}