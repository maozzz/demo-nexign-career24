package ru.nexign.democareer24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nexign.democareer24.db.entity.User;
import ru.nexign.democareer24.services.LotteryService;
import ru.nexign.democareer24.services.UserService;

import java.util.List;

/**
 * @author : maoz (mao_z@mail.ru)
 **/
@RestController
@RequestMapping("/lottery")
public class MyController {
    @Autowired UserService userService;
    @Autowired LotteryService lotteryService;

    @GetMapping("/participant")
    public List<User> participants() {
        List<User> participants = userService.participants();
        return participants;
    }

    @PostMapping("/participant")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/start")
    public User start() {
        return lotteryService.start();
    }

    @GetMapping("/winners")
    public List<User> winners() {
        return userService.winners();
    }
}
