package ru.itabrek.chguevent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itabrek.chguevent.entity.User;
import ru.itabrek.chguevent.entity.UserData;
import ru.itabrek.chguevent.exception.UserNotFoundException;
import ru.itabrek.chguevent.service.UserDataService;

@RestController
@RequestMapping("/api/v1/user_data")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataService userDataService;
    @GetMapping
    public ResponseEntity getUserData(@RequestAttribute User user){
        try{
            return ResponseEntity.ok(userDataService.findByUser(user.getId()));
        } catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateUserData(@RequestAttribute User user, @RequestBody UserData userData){
        try{
            userDataService.update(user.getId(), userData);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}