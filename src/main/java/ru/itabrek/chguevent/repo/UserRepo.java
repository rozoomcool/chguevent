package ru.itabrek.chguevent.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itabrek.chguevent.entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1 where u.id = ?2")
    int updatePasswordById(String password, Long id);
    @Transactional
    @Modifying
    @Query("update User u set u.email = ?1 where u.id = ?2")
    int updateEmailById(String email, Long id);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    @Transactional
    @Modifying
    @Query("update User u set u.nickname = ?1 where u.id = ?2")
    int updateNicknameById(String nickname, Long id);
    Optional<User> findByNickname(String nickname);
}
