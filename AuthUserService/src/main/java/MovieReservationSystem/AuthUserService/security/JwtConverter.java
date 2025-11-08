package MovieReservationSystem.AuthUserService.security;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class JwtConverter implements Converter <Jwt, Collection<GrantedAuthority>>{
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Set<String> roles = new HashSet<>();

        // Roles del realm
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.get("roles") != null) {
            roles.addAll((Collection<String>) realmAccess.get("roles"));
        }

        // Roles de cada cliente (resource_access)
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null) {
            for (Object value : resourceAccess.values()) {
                Map<String, Object> client = (Map<String, Object>) value;
                if (client.get("roles") != null) {
                    roles.addAll((Collection<String>) client.get("roles"));
                }
            }
        }

        // Convertir a GrantedAuthority con prefijo ROLE_
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toSet());
    }
}
