package MovieReservationSystem.AuthUserService.UserPackage.Controller;


import MovieReservationSystem.AuthUserService.UserPackage.Model.CreateUserRequest;
import MovieReservationSystem.AuthUserService.UserPackage.Model.LoginRequest;
import MovieReservationSystem.AuthUserService.UserPackage.Model.LoginResponse;
import MovieReservationSystem.AuthUserService.UserPackage.Model.UserDto;
import MovieReservationSystem.AuthUserService.UserPackage.Service.userService;

import jakarta.validation.Valid;

import jakarta.ws.rs.NotFoundException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private userService userService;

    @PostMapping("/auth/login" )
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        // Implement login logic here
        LoginResponse loginResponse = userService.loginUser(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/user/logout")
    public ResponseEntity <String> logout(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        String response = userService.logoutUser(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        // Implement user creation logic here
        UserDto createdUser = userService.CreateUser(createUserRequest);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/user/allUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        UserDto user = userService.getUserById(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return ResponseEntity.ok(user);
    }


}
