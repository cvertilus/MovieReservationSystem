package MovieReservationSystem.AuthUserService.UserPackage.Model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LoginResponse {
    private Object token;
    private String message;

}
