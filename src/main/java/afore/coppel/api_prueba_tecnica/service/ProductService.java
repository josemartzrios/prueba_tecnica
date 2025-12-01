package afore.coppel.api_prueba_tecnica.service;

import afore.coppel.api_prueba_tecnica.model.Product;
import afore.coppel.api_prueba_tecnica.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpMethod;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Recupera y devuelve la lista completa de productos.
     * @return Una lista de objetos Product.
     */
    public List<Product> getAllProducts() {
        // Usamos el metodo findAll() heredado de JpaRepository
        return productRepository.findAll();
    }

    /**
     * Guarda un nuevo producto, validando que el SKU no exista.
     */
    public Product createProduct(Product product) {
        // Validación del SKU único
        if (productRepository.existsBySku(product.getSku())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El SKU '" + product.getSku() + "' ya existe."
            );
        }

        // El metodo save() de JpaRepository hace la inserción.
        return productRepository.save(product);
    }

    /**
     * Obtiene un producto buscando por su SKU.
     * @param sku El SKU del producto a buscar.
     * @return El objeto Product encontrado.
     * Si el producto no existe, lo cual debe ser mapeado a 404.
     */
    public Product getProductBySku(String sku) {
        return productRepository.findBySku(sku)
                .orElseThrow(() -> new NoSuchElementException("Producto con SKU " + sku + " no encontrado."));
    }
}