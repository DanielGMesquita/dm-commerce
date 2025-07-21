package dev.danielmesquita.dmcommerce.repositories;

import dev.danielmesquita.dmcommerce.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  @Query("SELECT DISTINCT c FROM Category c JOIN FETCH c.products")
  Page<Category> searchByName(String name, Pageable pageable);
}
