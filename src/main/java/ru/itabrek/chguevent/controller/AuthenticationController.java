package ru.itabrek.chguevent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itabrek.chguevent.exception.UserAlreadyExistException;
import ru.itabrek.chguevent.exception.UserNotFoundException;
import ru.itabrek.chguevent.model.AuthenticationRequest;
import ru.itabrek.chguevent.model.AuthenticationResponse;
import ru.itabrek.chguevent.model.RegisterRequest;
import ru.itabrek.chguevent.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody RegisterRequest request
    ) throws UserAlreadyExistException {
        try{
            return ResponseEntity.ok(service.register(request));
        } catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("REGISTRATION IS FAILED");
        }

    }

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            return ResponseEntity.ok(service.login(request));
        } catch(UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
//"AUTHENTICATION IS FAILED"