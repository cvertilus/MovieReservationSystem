package MovieReservationSystem.AuthUserService.KeyCloakPackage;

import MovieReservationSystem.AuthUserService.UserPackage.Model.CreateUserRequest;
import MovieReservationSystem.AuthUserService.UserPackage.Model.LoginRequest;
import MovieReservationSystem.AuthUserService.UserPackage.Model.LoginResponse;
import MovieReservationSystem.AuthUserService.UserPackage.Model.UserDto;
import MovieReservationSystem.AuthUserService.UserPackage.Repository.UserRepository;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class KeycloakRepository implements UserRepository {
    @Autowired
    private Keycloak keycloak;

    @Autowired
    private KeycloakFeign keycloakFeign;

    @Value("${app.keycloak.realm}")
    private String realm;
    @Value("${app.keycloak.clientid}")
    private String clientId;
    @Value("${app.keycloak.secret}")
    private String clientSecret;


    @Override
    public LoginResponse LoginUser(LoginRequest loginRequest) {
        // Implement Keycloak authentication logic here

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("grant_type", "password");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("username", loginRequest.getUsername());
        formData.add("password", loginRequest.getPassword());

        // Call Keycloak to get the token
        try {
            Map<String, Object> response = keycloakFeign.getToken(formData);
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setToken(response.get("access_token"));
                loginResponse.setMessage("Authentication successful");
                return loginResponse;


        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String LogoutUser(String idUser) {
        // Implement Keycloak logout logic here
        try {
            keycloak.realm(realm).users().get(idUser).logout();
            return "User logged out successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to logout user: " + e.getMessage());
        }
    }

    @Override
    public UserDto CreateUser(CreateUserRequest createUserRequest) {
        // Implement Keycloak user creation logic here
        CredentialRepresentation credentialRepresentation = newCredential(createUserRequest.getPassword());
        UserRepresentation userRepresentation = newUserReprsentation(createUserRequest,credentialRepresentation);

        try {
            Response response = keycloak.realm(realm).users().create(userRepresentation);
            String location = response.getLocation().toString();
            String userId = location.substring(location.lastIndexOf("/") + 1);
            userRepresentation.setId(userId);
            asignarRoleToUser(userId);
            return convertToUserDto(userRepresentation);
        }catch (Exception e){
            throw new RuntimeException("Failed to create user: " + e.getMessage());
        }
    }

    private CredentialRepresentation newCredential (String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);
        return credential;
    }

    private UserRepresentation newUserReprsentation (CreateUserRequest createUserRequest, CredentialRepresentation credentialRepresentation)
    {
        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setUsername(createUserRequest.getEmail());
        userRepresentation.setFirstName(createUserRequest.getFirstName());
        userRepresentation.setLastName(createUserRequest.getLastName());
        userRepresentation.setEmail(createUserRequest.getEmail());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
        userRepresentation.setEnabled(true);

        return  userRepresentation;

    }

    private void  asignarRoleToUser(String userId){
        RoleRepresentation roleRepresentation = keycloak.realm(realm).roles().get("USER").toRepresentation();
        keycloak.realm(realm).users().get(userId).roles().realmLevel().add(Collections.singletonList(roleRepresentation));
    }

    private UserDto convertToUserDto(UserRepresentation userRepresentation){
        UserDto userDto =  UserDto.builder()
                .email(userRepresentation.getEmail())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .userId(userRepresentation.getId())
                .build();
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserRepresentation> users = keycloak.realm(realm).users().list();
        List<UserDto> userDtos = users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto getUserById(String userId) {
        UserRepresentation userRepresentation = keycloak.realm(realm).users().get(userId).toRepresentation();
        return convertToUserDto(userRepresentation);
    }


}
