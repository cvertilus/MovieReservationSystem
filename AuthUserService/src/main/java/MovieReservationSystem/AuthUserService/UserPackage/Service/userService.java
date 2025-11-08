package MovieReservationSystem.AuthUserService.UserPackage.Service;

import MovieReservationSystem.AuthUserService.UserPackage.Model.CreateUserRequest;
import MovieReservationSystem.AuthUserService.UserPackage.Model.LoginRequest;
import MovieReservationSystem.AuthUserService.UserPackage.Model.LoginResponse;
import MovieReservationSystem.AuthUserService.UserPackage.Model.UserDto;
import MovieReservationSystem.AuthUserService.UserPackage.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class userService {
    @Autowired
    private UserRepository userRepository;


    public LoginResponse loginUser(LoginRequest loginRequest) {
        return  userRepository.LoginUser(loginRequest);
    }

    public String logoutUser(String userId) {
        return userRepository.LogoutUser(userId);
    }

    public UserDto CreateUser(CreateUserRequest createUserRequest) {
        return userRepository.CreateUser(createUserRequest);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserDto getUserById(String userId) {
        return userRepository.getUserById(userId);
    }
}
