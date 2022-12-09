package ru.ae.coursemodel.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ae.coursemodel.config.jwt.JwtProvider;
import ru.ae.coursemodel.dto.auth.LoginRequest;
import ru.ae.coursemodel.dto.auth.LoginResponse;
import ru.ae.coursemodel.entity.User;
import ru.ae.coursemodel.exeption.AuthException;
import ru.ae.coursemodel.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(@NonNull LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.getLogin())
                .orElseThrow(() -> new AuthException("User not found!!!"));
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getUsername(), refreshToken);
            return new LoginResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Incorrect password!!!");
        }
    }

    public LoginResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            var claims = jwtProvider.getRefreshClaims(refreshToken);
            var login = claims.getSubject();
            var saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByUsername(login)
                        .orElseThrow(() -> new AuthException("User not found"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new LoginResponse(accessToken, null);
            }
        }
        return new LoginResponse(null, null);
    }

    public LoginResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            var claims = jwtProvider.getRefreshClaims(refreshToken);
            var login = claims.getSubject();
            var saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByUsername(login)
                        .orElseThrow(() -> new AuthException("User not found"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getUsername(), newRefreshToken);
                return new LoginResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid JWT token");
    }

    public org.springframework.security.core.userdetails.User getAuthInfo() {
        return (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication();
    }
}
