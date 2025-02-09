package com.app.policy;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PolicyDto(
        Long id,

        @Size(min = 1, max = 255, message = "name is required length 1-255")
        @NotEmpty(message = "name is required")
        String name,
        String date,

        int view,

        @Min(value = 0, message = "ctr is required min 0")
        @Max(value = 100, message = "ctr is required max 100")
        float ctr,
        @Min(value = 0, message = "roi is required min 0")
        @Max(value = 100, message = "roi is required max 100")
        float roi,

        @Size(min = 1, max = 5000, message = "description is required length 1-5000")
        @NotEmpty(message = "description is required")
        String description,

        String file,

        String status,
        String statusName,

        Long orderingId
) {
}
