package lv.digitalbear.sitedemo.config;

import lv.digitalbear.sitedemo.model.Roles;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/").permitAll();
//                    auth.requestMatchers(HttpMethod.GET, "/api//**").hasAnyRole(Roles.ADMIN.name(), Roles.USER.name());
//                    auth.requestMatchers(HttpMethod.POST, "/api/**").hasRole(Roles.ADMIN.name());
//                    auth.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole(Roles.ADMIN.name());
//                    //auth.anyRequest();
//                });
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passEncoder().encode("admin"))
                        //.roles(Roles.ADMIN.name())
                        .build(),
                User.builder()
                        .username("user")
                        .password(passEncoder().encode("user"))
                        //.roles(Roles.USER.name())
                        .build()
        );
    }
}
