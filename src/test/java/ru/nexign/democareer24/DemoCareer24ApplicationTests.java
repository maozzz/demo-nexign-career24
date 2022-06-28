package ru.nexign.democareer24;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.nexign.democareer24.db.entity.User;
import ru.nexign.democareer24.services.LotteryService;
import ru.nexign.democareer24.services.UserService;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(false)
class DemoCareer24ApplicationTests {
    @Autowired UserService userService;
    @Autowired LotteryService lotteryService;

    @Test @Order(0)
    void contextLoads() {
    }

    /**
     * Сначала пользователей нет - должна быть ошибка "меньше 2"
     */
    @Test @Order(1)
    void checkException() {
        assertThrows(RuntimeException.class, () -> lotteryService.start(), LotteryService.ERROR_MESSAGE);
    }

    /**
     * Проверяем
     */
    @Test @Order(2)
    void normalTest() {
        User user = createUser();
        assertTrue(userService.participants().size() > 0);
        assertThrows(RuntimeException.class, () -> lotteryService.start(), LotteryService.ERROR_MESSAGE);
        User user2 = createUser();
        List<User> users = userService.participants();
        assertTrue(users.size() > 0);

        User winner = lotteryService.start();
        assertNotNull(winner);
        assertTrue(
                users.stream()
                        .map(User::getId)
                        .anyMatch(id -> id.equals(winner.getId()))
        );
        assertTrue(winner.getPrize() > 0);

        List<User> newUsers = userService.participants();
        assertTrue(newUsers.size() == 0);
    }

    private User createUser() {
        User u = new User();
        u.setName("Вася");
        u.setAge(new Random().nextInt(15) + 18);
        u.setCity("Петровск");
        return userService.create(u);
    }
}
