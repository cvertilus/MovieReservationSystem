package MovieReservationSystem.AuthUserService.UserPackage.Repository;

import MovieReservationSystem.AuthUserService.UserPackage.Model.CreateUserRequest;
import MovieReservationSystem.AuthUserService.UserPackage.Model.LoginRequest;
import MovieReservationSystem.AuthUserService.UserPackage.Model.LoginResponse;
import MovieReservationSystem.AuthUserService.UserPackage.Model.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    LoginResponse LoginUser(LoginRequest loginRequest);
    String LogoutUser(String idUser);
    UserDto CreateUser(CreateUserRequest createUserRequest);
    List<UserDto> getAllUsers();
    UserDto getUserById(String userId);
}
