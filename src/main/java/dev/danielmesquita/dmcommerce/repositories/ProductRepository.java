package dev.danielmesquita.dmcommerce.repositories;

import dev.danielmesquita.dmcommerce.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(
      value =
          "SELECT p FROM Product p JOIN FETCH p.categories WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))",
      countQuery =
          "SELECT COUNT(p) FROM Product p JOIN p.categories WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
  Page<Product> findByName(String name, Pageable pageable);
}
