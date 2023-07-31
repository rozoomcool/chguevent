package ru.itabrek.chguevent.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.itabrek.chguevent.entity.User;
import ru.itabrek.chguevent.entity.UserData;

import java.util.Optional;

public interface UserDataRepo extends JpaRepository<UserData, Long> {
    @Transactional
    @Modifying
    @Query("update UserData u set u.userAvatar = ?1 where u.user = ?2")
    int updateUserAvatarByUser(String userAvatar, User user);
    Optional<UserData> findByUser_Id(Long id);
    boolean existsByUser(User user);
    boolean existsByUser_Id(Long id);
    Optional<UserData> findByUser(User user);
}
