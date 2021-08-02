package kwy.taxi.allocation.controller;

import kwy.taxi.allocation.model.User;
import kwy.taxi.allocation.security.MyToken;
import kwy.taxi.allocation.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public User signUp(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/sign-in")
    public MyToken signIn(@RequestBody User user){
        return userService.validateUser(user);
    }
}
