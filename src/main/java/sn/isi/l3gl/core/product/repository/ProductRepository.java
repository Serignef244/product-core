package sn.isi.l3gl.core.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.l3gl.core.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
