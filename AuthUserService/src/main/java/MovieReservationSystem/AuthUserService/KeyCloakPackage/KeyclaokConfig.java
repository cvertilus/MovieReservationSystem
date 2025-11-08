package MovieReservationSystem.AuthUserService.KeyCloakPackage;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "keycloak")
public class KeyclaokConfig {
    @Value("${app.keycloak.serverurl}")
    private String serverUrl;
    @Value("${app.keycloak.realm}")
    private String realm;
    @Value("${app.keycloak.clientid}")
    private String clientId;
    @Value("${app.keycloak.secret}")
    private String clientSecret;

    @Bean
    public Keycloak getInstance() {

        return  KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();

    }
}
