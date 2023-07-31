package ru.itabrek.chguevent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itabrek.chguevent.entity.User;
import ru.itabrek.chguevent.exception.UserAlreadyExistException;
import ru.itabrek.chguevent.exception.UserNotFoundException;
import ru.itabrek.chguevent.model.UserModel;
import ru.itabrek.chguevent.service.UserDataService;
import ru.itabrek.chguevent.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDataService userDataService;

    @GetMapping()
    public ResponseEntity getUser(@RequestAttribute User user){
        try{
            return ResponseEntity.ok(userService.findById(user.getId()));
        } catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(new UserModel(userService.findById(id)));
        } catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/update/nickname")
    public ResponseEntity changeNickname(@RequestAttribute User user, @RequestParam(name = "nickname") String nickname){
        try{
            userService.changeNickname(user.getId(), nickname);
            return ResponseEntity.ok().build();
        }catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/email")
    public ResponseEntity changeEmail(@RequestAttribute User user, @RequestParam(name = "email") String email){
        try{
            userService.changeEmail(user.getId(), email);
            return ResponseEntity.ok().build();
        }catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/password")
    public ResponseEntity changePassword(@RequestAttribute User user, @RequestParam(name = "password") String password){
        try {
            userService.changePassword(user.getId(), password);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
