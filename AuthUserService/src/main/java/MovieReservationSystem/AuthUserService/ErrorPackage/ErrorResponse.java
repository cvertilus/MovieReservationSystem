package MovieReservationSystem.AuthUserService.ErrorPackage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;

}
