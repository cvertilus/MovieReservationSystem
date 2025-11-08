package MovieReservationSystem.AuthUserService.UserPackage.Model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class CreateUserRequest {

    @NotNull(message = "firstName must not be null")
    @NotBlank(message = "firstName must not be blank")
    private String firstName;

    @NotNull(message = "lastName must not be null")
    @NotBlank(message = "lastName must not be blank")
    private String lastName;

    @NotNull(message = "password must not be null")
    @NotBlank(message = "password must not be blank")
    private String password;

    @NotNull(message = "email must not be null")
    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be a valid email address")
    private String email;

}
