package MovieReservationSystem.AuthUserService.UserPackage.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
}
