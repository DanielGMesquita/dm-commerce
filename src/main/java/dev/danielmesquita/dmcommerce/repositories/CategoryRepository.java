package dev.danielmesquita.dmcommerce.repositories;

import dev.danielmesquita.dmcommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
