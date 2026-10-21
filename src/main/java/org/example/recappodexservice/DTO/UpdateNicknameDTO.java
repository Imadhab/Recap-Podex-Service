package org.example.recappodexservice.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateNicknameDTO(

        @NotBlank(message = "Nickname is required")
        @Size(min =2, message = "Nickname must have at least 2 characters")
        String nickname
) {
}
