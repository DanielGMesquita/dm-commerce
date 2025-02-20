package dev.danielmesquita.dmcommerce.repositories;

import dev.danielmesquita.dmcommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
