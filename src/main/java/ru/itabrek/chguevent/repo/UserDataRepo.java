package ru.itabrek.chguevent.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.itabrek.chguevent.entity.User;
import ru.itabrek.chguevent.entity.UserData;

import java.util.Optional;

public interface UserDataRepo extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUser_Id(Long id);
    boolean existsByUser(User user);
    boolean existsByUser_Id(Long id);
    Optional<UserData> findByUser(User user);
}
