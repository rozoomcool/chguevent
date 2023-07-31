package ru.itabrek.chguevent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itabrek.chguevent.entity.User;
import ru.itabrek.chguevent.entity.UserData;
import ru.itabrek.chguevent.exception.UserNotFoundException;
import ru.itabrek.chguevent.repo.UserDataRepo;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserDataService {

    private final UserDataRepo userDataRepo;
    private final UserAvatarService userAvatarService;
    private final UserService userService;

    public void create(User user, UserData dataRequest){
        if(!userDataRepo.existsByUser(user)) {
            UserData userData = new UserData();
            userData.setUser(user);

            userData.builder()
                    .firstname(dataRequest.getFirstname())
                    .lastname(dataRequest.getLastname())
                    .age(dataRequest.getAge())
                    .phone(dataRequest.getPhone())
                    .build();

            userDataRepo.save(userData);
        }
    }

    public UserData findByUser(Long userId) throws UserNotFoundException {
        return userDataRepo.findByUser_Id(userId).orElseThrow(() -> new UserNotFoundException("USER DATA NOT FOUNDED"));
    }

    public void update(Long userId, UserData userData) throws UserNotFoundException {
        UserData dataToUpdate = userDataRepo.findByUser_Id(userId).orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));

        dataToUpdate.setFirstname(userData.getFirstname());
        dataToUpdate.setLastname(userData.getLastname());
        dataToUpdate.setPhone(userData.getPhone());
        dataToUpdate.setAge(userData.getAge());

        userDataRepo.save(dataToUpdate);
    }

    public void uploadUserAvatar(Long userId, byte[] userAvatar) throws UserNotFoundException, IOException {
        UserData userData = findByUser(userId);

        if(userData.getUserAvatar() != null){
            userAvatarService.deleteAvatar(userData.getUserAvatar());
        }

        String fileName = userAvatarService.saveAvatar(userAvatar);
        userDataRepo.updateUserAvatarByUser(fileName, userService.findById(userId));
    }

}
