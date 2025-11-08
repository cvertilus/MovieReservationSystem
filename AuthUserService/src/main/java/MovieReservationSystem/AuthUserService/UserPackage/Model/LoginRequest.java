package MovieReservationSystem.AuthUserService.UserPackage.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Username must not be blank")
    @NotNull(message = "Username must not be null")
    @Email(message = "Username must be a valid email address")
    private String username;

    @NotBlank(message = "Password must not be blank")
    @NotNull(message = "Password must not be null")
    private String password;
}
