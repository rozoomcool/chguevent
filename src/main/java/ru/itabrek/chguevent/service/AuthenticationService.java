package ru.itabrek.chguevent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itabrek.chguevent.entity.Role;
import ru.itabrek.chguevent.entity.User;
import ru.itabrek.chguevent.entity.UserData;
import ru.itabrek.chguevent.exception.UserAlreadyExistException;
import ru.itabrek.chguevent.exception.UserNotFoundException;
import ru.itabrek.chguevent.model.AuthenticationRequest;
import ru.itabrek.chguevent.model.AuthenticationResponse;
import ru.itabrek.chguevent.model.RegisterRequest;
import ru.itabrek.chguevent.repo.UserDataRepo;
import ru.itabrek.chguevent.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserDataService userDataService;


    public AuthenticationResponse register(RegisterRequest request) throws UserAlreadyExistException {
        User user = User.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isEnabled(true)
                .isLocked(false)
                .role(Role.GUEST)
                .build();

        try{
            userRepo.save(user);
        } catch (Exception e){
            throw new UserAlreadyExistException("USER ALREADY EXIST");
        }

        String jwtToken = jwtService.generateToken(user);

        userDataService.create(user, new UserData());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(
            AuthenticationRequest request
    ) throws UserNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getNickname(),
                        request.getPassword()
                )
        );

        User user = userRepo.findByNickname(request.getNickname())
                .orElseThrow(() -> new UserNotFoundException("INCORRECT LOGIN OR PASSWORD"));

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
