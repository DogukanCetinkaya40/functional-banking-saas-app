package controller;

import entity.User;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User kullaniciOlustur(@RequestBody User user) {

        return userService.kullaniciOlustur(user);

    }

    @GetMapping
    public List<User> tumKullanicilar() {

        return userService.tumKullanicilar();

    }

    @GetMapping("/{id}")
    public User kullaniciBul(@PathVariable UUID id) {

        return userService.kullaniciBul(id);

    }
}
