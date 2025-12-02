package afore.coppel.api_prueba_tecnica.service;

import afore.coppel.api_prueba_tecnica.api.dto.UserResponse;
import afore.coppel.api_prueba_tecnica.model.Product;
import afore.coppel.api_prueba_tecnica.model.User;
import afore.coppel.api_prueba_tecnica.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Obtiene la lista de todos los usuarios y los mapea a UserResponse.
     */
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        // Mapea la lista de entidades User a la lista de DTOs UserResponse
        return users.stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un usuario por su ID y lo mapea a UserResponse.
     */
    public UserResponse getUserById(Long id) {

        // 1. Buscar el usuario por ID.
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // 2. Mapear la entidad al DTO.
        return mapToUserResponse(user);
    }

    /**
     * Metodo auxiliar para mapear de Entidad User a DTO UserResponse.
     */
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

    /**
     * Lógica para eliminar un usuario por su ID.
     */
    @Transactional
    public void deleteUser(Long id) {

        // 1. Verificar si el usuario existe antes de intentar eliminarlo.
        if (!userRepository.existsById(id)) {
            // Si no se encuentra, lanzar una excepción (para mapear a 404 Not Found)
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }

        // 2. Eliminar el usuario.
        userRepository.deleteById(id);

        // Aquí iría el log de auditoría: "Usuario con ID [id] eliminado por el administrador."
    }

}