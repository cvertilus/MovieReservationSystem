package MovieReservationSystem.apiGateWay.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

   @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
       http
               .csrf(ServerHttpSecurity.CsrfSpec::disable)
               .authorizeExchange(exchange -> exchange
                       .pathMatchers("/auth/**").permitAll()
                       .pathMatchers("/actuator/**").permitAll()
                       .pathMatchers("/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html","/webjars/**").permitAll()
                       .anyExchange()
                       .authenticated())
               .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
       return  http.build();
   }
}
