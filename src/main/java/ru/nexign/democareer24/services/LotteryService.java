package ru.nexign.democareer24.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.nexign.democareer24.db.entity.Lottery;
import ru.nexign.democareer24.db.entity.User;
import ru.nexign.democareer24.db.entity.Winner;
import ru.nexign.democareer24.db.repo.LotteryRepository;
import ru.nexign.democareer24.db.repo.WinnerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author : maoz (mao_z@mail.ru)
 **/
@Service @Slf4j @Transactional
public class LotteryService {
    private Long currentLotteryId;
    public static final String ERROR_MESSAGE = "Users count must be over 2";
    @Autowired LotteryRepository lotteryRepository;
    @Autowired WinnerRepository winnerRepository;
    @Autowired UserService userService;
    @Autowired Environment env;
    private RestTemplate rt = new RestTemplate();
    private Random rand = new Random();

    @EventListener(ContextRefreshedEvent.class)
    public long current() {
        if (currentLotteryId == null) { // Если нет текущего id смотрим в БД
            Lottery current = lotteryRepository.current();
            if (current != null) { // Если есть - подгружаем
                currentLotteryId = current.getId();
            } else { // Если в БД нет - создаем новую лотерею
                currentLotteryId = lotteryRepository.save(new Lottery()).getId();
            }
        }
        return currentLotteryId;
    }

    public synchronized User start() {
        Lottery current = lotteryRepository.findById(current()).get();
        List<User> users = current.getUsers();
        if (users.size() < 2) {
            throw new RuntimeException(ERROR_MESSAGE);
        }
        User winner = users.get(rand.nextInt(users.size()));

        Winner w = new Winner(winner, new Lottery(currentLotteryId));
        winnerRepository.save(w);

        current.close();
        currentLotteryId = null;
        lotteryRepository.save(current);

        return userService.win(winner, randPrize());
    }

    private int randPrize() {
        if (Arrays.stream(env.getActiveProfiles()).anyMatch("remote"::equals)) {
            log.info("Используем рандом с сайта");
            String in = rt.getForObject(
                    "https://www.random.org/integers/?num=1&min=1&max=998&col=1&base=10&format=plain&rnd=new",
                    String.class);
            return Integer.valueOf(in.replace("\n", ""));
        } else {
            log.info("Используем обычный рандом");
            return rand.nextInt(1000);
        }
    }
}
