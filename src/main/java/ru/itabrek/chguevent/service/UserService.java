package ru.itabrek.chguevent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itabrek.chguevent.entity.User;
import ru.itabrek.chguevent.exception.UserAlreadyExistException;
import ru.itabrek.chguevent.exception.UserNotFoundException;
import ru.itabrek.chguevent.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public User findById(long id) throws UserNotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));
    }

    public User findByNickname(String nickname) throws UserNotFoundException {
        return userRepo.findByNickname(nickname).orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));
    }

    public boolean existsByNickname(String nickname){
        return userRepo.existsByNickname(nickname);
    }

    public boolean existsByEmail(String email){
        return userRepo.existsByEmail(email);
    }

    public void changeNickname(Long id, String nickname) throws UserAlreadyExistException {
        if(existsByNickname(nickname)){
            throw new UserAlreadyExistException("USER WITH THIS NICKNAME ALREADY EXIST");
        }

        userRepo.updateNicknameById(nickname, id);
    }

    public void changeEmail(Long id, String email) throws UserAlreadyExistException {
        if(existsByEmail(email)){
            throw new UserAlreadyExistException("USER WITH THIS EMAIL ALREADY EXIST");
        }

        userRepo.updateEmailById(email, id);
    }

    public void changePassword(Long id, String password) {
        userRepo.updatePasswordById(passwordEncoder.encode(password), id);
    }
}
