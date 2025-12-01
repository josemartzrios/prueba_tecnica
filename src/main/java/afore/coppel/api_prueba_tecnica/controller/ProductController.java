package afore.coppel.api_prueba_tecnica.controller;

import afore.coppel.api_prueba_tecnica.model.Product;
import afore.coppel.api_prueba_tecnica.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products") // Mapea la ruta base /products
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * GET /products
     * Lista todos los productos disponibles.
     * Corresponde a la respuesta '200' en tu definición Swagger.
     */
    @GetMapping // Mapea las solicitudes GET a /products
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            // Si la lista está vacía, se podría devolver 404 Not Found,
            // pero el estándar de APIs REST sugiere devolver 200 OK con lista vacía ([]).
            // Para el propósito del ejercicio, devolveremos 200 OK, cumpliendo con la definición
            // de que el endpoint está accesible.
            return ResponseEntity.ok(products);
        }

        // Devuelve 200 OK con el cuerpo de la lista de productos
        return ResponseEntity.ok(products);
    }
}