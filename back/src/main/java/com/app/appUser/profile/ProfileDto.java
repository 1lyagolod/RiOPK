package com.app.appUser.profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ProfileDto(
        Long id,

        @Size(min = 1, max = 255, message = "fio is required length 1-255")
        @NotEmpty(message = "fio is required")
        String fio,
        @Size(min = 1, max = 255, message = "fio is required length 1-255")
        @NotEmpty(message = "fio is required")
        String post,
        @Size(min = 1, max = 255, message = "email is required length 1-255")
        @NotEmpty(message = "email is required")
        @Email(message = "email is incorrect")
        String email,
        @Size(min = 1, max = 255, message = "tel is required length 1-255")
        @NotEmpty(message = "tel is required")
        String tel,
        @Size(min = 1, max = 255, message = "org is required length 1-255")
        @NotEmpty(message = "org is required")
        String org
) {
}
