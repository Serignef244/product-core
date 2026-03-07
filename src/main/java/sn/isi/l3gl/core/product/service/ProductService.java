package sn.isi.l3gl.core.product.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.l3gl.core.product.entity.Product;
import sn.isi.l3gl.core.product.repository.ProductRepository;

@Service
public class ProductService {

    private static final int LOW_STOCK_THRESHOLD = 5;

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product updateQuantity(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
        product.setQuantity(quantity);
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public long countLowStockProducts() {
        return productRepository.countByQuantityLessThanEqual(LOW_STOCK_THRESHOLD);
    }
}
