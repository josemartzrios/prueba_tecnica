package afore.coppel.api_prueba_tecnica.service;

import afore.coppel.api_prueba_tecnica.api.dto.AuthResponse;
import afore.coppel.api_prueba_tecnica.api.dto.UserLogin;
import afore.coppel.api_prueba_tecnica.model.User;
import afore.coppel.api_prueba_tecnica.repository.UserRepository;
import afore.coppel.api_prueba_tecnica.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(UserLogin userLogin) {
        try {
            // Autenticar
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLogin.getEmail(),
                            userLogin.getPassword()
                    )
            );

            // Buscar usuario
            User user = userRepository.findByEmail(userLogin.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Generar token
            String token = jwtUtil.generateToken(user.getEmail(), String.valueOf(user.getRole()));

            return new AuthResponse(token, user.getEmail(), user.getRole().name());

        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}