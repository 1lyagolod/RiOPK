package com.app.appUser.profile.converter;

import com.app.appUser.profile.Profile;
import com.app.appUser.profile.ProfileDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileToProfileDtoConverter implements Converter<Profile, ProfileDto> {
    @Override
    public ProfileDto convert(Profile source) {
        return new ProfileDto(
                source.getId(),

                source.getFio(),
                source.getPost(),
                source.getEmail(),
                source.getTel(),
                source.getOrg()
        );
    }
}
