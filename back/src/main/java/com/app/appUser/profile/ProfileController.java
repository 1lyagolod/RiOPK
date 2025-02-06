package com.app.appUser.profile;

import com.app.appUser.profile.converter.ProfileDtoToProfileConverter;
import com.app.appUser.profile.converter.ProfileToProfileDtoConverter;
import com.app.system.Result;
import com.app.system.StatusCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.app.util.Global.CLIENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/profiles")
@Secured({CLIENT})
public class ProfileController {

    private final ProfileService service;
    private final ProfileToProfileDtoConverter toDtoConverter;
    private final ProfileDtoToProfileConverter toConverter;

    @PutMapping
    public Result update(@Valid @RequestBody ProfileDto updateDto) {
        Profile update = toConverter.convert(updateDto);
        Profile updated = service.update(update);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update",
                toDtoConverter.convert(updated)
        );
    }

}
