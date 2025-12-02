package afore.coppel.api_prueba_tecnica.service;

import afore.coppel.api_prueba_tecnica.api.dto.ProductAuditLog;
// Importa tu repositorio de logs real aquí (ProductAuditLogRepository)
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditService {

    // private final ProductAuditLogRepository productAuditLogRepository; // Descomentar en la implementación real

    /**
     * Obtiene y devuelve todos los registros de auditoría de productos.
     */
    public List<ProductAuditLog> getAllProductLogs() {

        // Retornamos datos simulados usando la estructura de tu DTO:
        return List.of(
                ProductAuditLog.builder()
                        .logId(101L)
                        .product("SMART-WATCH-A9")
                        .user("admin@empresa.com")
                        .action("UPDATE")
                        .changesJson("{\"oldPrice\": 79.99, \"newPrice\": 85.00}")
                        .changedAt(LocalDateTime.now().minusHours(1))
                        .build(),
                ProductAuditLog.builder()
                        .logId(102L)
                        .product("TABLET-XYZ")
                        .user("admin@empresa.com")
                        .action("CREATE")
                        .changesJson("{\"name\": \"Tablet XYZ\", \"price\": 499.00}")
                        .changedAt(LocalDateTime.now().minusMinutes(30))
                        .build()
        );
    }
}