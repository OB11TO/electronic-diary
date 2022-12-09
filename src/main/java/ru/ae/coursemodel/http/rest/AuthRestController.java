package ru.ae.coursemodel.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ae.coursemodel.dto.RefreshJwtToken;
import ru.ae.coursemodel.dto.auth.LoginRequest;
import ru.ae.coursemodel.dto.auth.LoginResponse;
import ru.ae.coursemodel.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/token")
    public ResponseEntity<LoginResponse> getNewAccessToken(@RequestBody RefreshJwtToken refreshJwtToken) {
        return ResponseEntity.ok(authService.getAccessToken(refreshJwtToken.getRefreshToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> getNewRefreshToken(@RequestBody RefreshJwtToken refreshJwtToken) {
        return ResponseEntity.ok(authService.refresh(refreshJwtToken.getRefreshToken()));
    }
}
