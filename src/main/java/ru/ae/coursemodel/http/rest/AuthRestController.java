package ru.ae.coursemodel.http.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ae.coursemodel.config.jwt.BlacklistTokenService;
import ru.ae.coursemodel.dto.RefreshJwtToken;
import ru.ae.coursemodel.dto.auth.LoginRequest;
import ru.ae.coursemodel.dto.auth.LoginResponse;
import ru.ae.coursemodel.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth", description = "аутентификация")
public class AuthRestController {

    private final AuthService authService;
    private final BlacklistTokenService blacklistTokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/token")
    public ResponseEntity<LoginResponse> getNewAccessToken(@RequestBody RefreshJwtToken refreshJwtToken) {
        return ResponseEntity.ok(authService.getAccessToken(refreshJwtToken.getRefreshToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String header) {
        blacklistTokenService.addBlackList(header);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> getNewRefreshToken(@RequestBody RefreshJwtToken refreshJwtToken) {
        return ResponseEntity.ok(authService.refresh(refreshJwtToken.getRefreshToken()));
    }
}
