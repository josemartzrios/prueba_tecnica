package afore.coppel.api_prueba_tecnica.service;

import afore.coppel.api_prueba_tecnica.model.Product;
import afore.coppel.api_prueba_tecnica.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}