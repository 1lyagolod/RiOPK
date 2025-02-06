package com.app.appUser.profile;

import com.app.appUser.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;
    private final UserService userService;

    public Profile update(Profile update){
        Profile old = userService.getCurrentUser().getProfile();
        old.update(update);
        return repository.save(old);
    }

}
