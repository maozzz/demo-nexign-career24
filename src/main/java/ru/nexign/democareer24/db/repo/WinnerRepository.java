package ru.nexign.democareer24.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nexign.democareer24.db.entity.Winner;

/**
 * @author : maoz (mao_z@mail.ru)
 **/
public interface WinnerRepository extends JpaRepository<Winner, Long> {
}
