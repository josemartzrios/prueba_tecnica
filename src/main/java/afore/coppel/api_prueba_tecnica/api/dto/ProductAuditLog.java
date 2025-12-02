package afore.coppel.api_prueba_tecnica.api.dto;
import lombok.Data;
import java.time.LocalDateTime;
import lombok.Builder;


@Data
@Builder
public class ProductAuditLog {

    private Long logId;
    private String product;
    private String user;
    private String action; // Ej: "CREATE", "UPDATE", "DELETE"
    private String changesJson;
    private LocalDateTime changedAt;
}
