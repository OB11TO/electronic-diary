package ru.ae.coursemodel.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.ae.coursemodel.config.jwt.JwtFilter;

import static ru.ae.coursemodel.entity.Role.ADMIN;

@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(urlConfig -> urlConfig
                        .antMatchers("/auth/login", "/auth/token", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .antMatchers("/users/registration").hasAuthority(ADMIN.getAuthority())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login")
//                        .deleteCookies("JSESSIONID"))
//                .formLogin(login -> login
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/teachers"));

        return http.build();
    }
}
