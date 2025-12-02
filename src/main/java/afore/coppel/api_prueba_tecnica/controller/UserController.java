package afore.coppel.api_prueba_tecnica.controller;

import afore.coppel.api_prueba_tecnica.api.dto.UserResponse;
import afore.coppel.api_prueba_tecnica.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * GET /users
     * Lista todos los usuarios. Requiere el rol ADMIN.
     */
    @GetMapping
    // ¡La parte crucial! Restringe el acceso solo a usuarios con rol ADMIN.
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> listUsers() {

        List<UserResponse> users = userService.getAllUsers();

        // Retorna la lista de usuarios con el estado 200 OK
        return ResponseEntity.ok(users);
    }

    /**
     * GET /users/{id}
     * Obtiene un usuario por su ID. Requiere el rol ADMIN.
     */
    @GetMapping("/{id}")
    // Restricción de acceso: solo para usuarios con rol 'ADMIN'.
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

        UserResponse user = userService.getUserById(id);

        // Retorna el detalle del usuario con el estado 200 OK
        return ResponseEntity.ok(user);
    }

    /**
     * DELETE /users/{id}
     * Elimina un usuario por su ID. Requiere el rol ADMIN.
     */
    @DeleteMapping("/{id}")
    // Restricción de acceso: solo para usuarios con rol 'ADMIN'.
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        // Retorna el estado 204 No Content, indicando éxito sin contenido de respuesta.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}