package dev.danielmesquita.dmcommerce.services;

import dev.danielmesquita.dmcommerce.dtos.CategoryDTO;
import dev.danielmesquita.dmcommerce.models.Category;
import dev.danielmesquita.dmcommerce.repositories.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
  @Autowired private CategoryRepository repository;

  @Transactional(readOnly = true)
  public List<CategoryDTO> findAll() {
    List<Category> categories = repository.findAll();
    return categories.stream().map(CategoryDTO::new).toList();
  }
}
