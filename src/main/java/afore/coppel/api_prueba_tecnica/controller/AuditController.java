package afore.coppel.api_prueba_tecnica.controller;

import afore.coppel.api_prueba_tecnica.api.dto.ProductAuditLog;
import afore.coppel.api_prueba_tecnica.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    /**
     * GET /audit/product-logs
     * Lista logs de auditoría de productos. Requiere rol ADMIN.
     */
    @GetMapping("/product-logs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProductAuditLog>> getProductAuditLogs() {

        List<ProductAuditLog> logs = auditService.getAllProductLogs();

        return ResponseEntity.ok(logs);
    }
}