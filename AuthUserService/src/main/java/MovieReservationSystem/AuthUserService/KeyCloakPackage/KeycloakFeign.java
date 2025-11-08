package MovieReservationSystem.AuthUserService.KeyCloakPackage;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.Map;

@FeignClient(name = "keycloak-service", url = "http://localhost:8080/realms/MovieReservationSystem/protocol/openid-connect/token")
public interface KeycloakFeign {

    @RequestMapping(method = RequestMethod.POST)
    Map<String ,Object> getToken (@RequestBody MultiValueMap<String, String> formData);
}
