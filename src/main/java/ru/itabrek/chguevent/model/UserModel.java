package ru.itabrek.chguevent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itabrek.chguevent.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Long id;
    private String nickname;
    private String email;
    private String role;

    public UserModel(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.role = user.getRole().name();
    }
}
