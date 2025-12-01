package afore.coppel.api_prueba_tecnica.controller;

import afore.coppel.api_prueba_tecnica.api.dto.AuthResponse;
import afore.coppel.api_prueba_tecnica.api.dto.UserLogin;
import afore.coppel.api_prueba_tecnica.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserLogin userLogin) {
        try {
            AuthResponse response = authService.login(userLogin);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
