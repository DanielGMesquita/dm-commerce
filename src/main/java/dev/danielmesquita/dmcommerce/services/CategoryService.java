package dev.danielmesquita.dmcommerce.services;

import dev.danielmesquita.dmcommerce.dtos.CategoryDTO;
import dev.danielmesquita.dmcommerce.dtos.ProductMinDTO;
import dev.danielmesquita.dmcommerce.models.Category;
import dev.danielmesquita.dmcommerce.models.Product;
import dev.danielmesquita.dmcommerce.repositories.CategoryRepository;
import dev.danielmesquita.dmcommerce.services.exceptions.DatabaseException;
import dev.danielmesquita.dmcommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
  @Autowired private CategoryRepository repository;

  @Autowired private ProductService productService;

  private static final String CATEGORY_NOT_FOUND = "Category not found";

  @Transactional(readOnly = true)
  public Page<CategoryDTO> findAllPageable(Pageable pageable) {
    Page<Category> products = repository.findAll(pageable);
    return products.map(CategoryDTO::new);
  }

  @Transactional(readOnly = true)
  public List<CategoryDTO> findAll() {
    List<Category> categories = repository.findAll();
    return categories.stream().map(CategoryDTO::new).toList();
  }

  public CategoryDTO findById(Long id) {
    Category category =
        repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND));
    return new CategoryDTO(category);
  }

  @Transactional
  public CategoryDTO insertProduct(CategoryDTO categoryDTO) {
    Category categoryEntity = new Category();
    dtoToEntity(categoryDTO, categoryEntity);

    categoryEntity = repository.save(categoryEntity);

    return new CategoryDTO(categoryEntity);
  }

  @Transactional
  public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
    try {
      Category categoryEntity = repository.getReferenceById(id);
      dtoToEntity(categoryDTO, categoryEntity);

      categoryEntity = repository.save(categoryEntity);

      return new CategoryDTO(categoryEntity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(CATEGORY_NOT_FOUND);
    }
  }

  @Transactional(readOnly = true)
  public List<ProductMinDTO> findProductsByCategoryId(Long id) {
    Optional<Category> result = repository.findById(id);
    Set<Product> list =
        result.orElseThrow(() -> new ResourceNotFoundException(CATEGORY_NOT_FOUND)).getProducts();
    return list.stream().map(ProductMinDTO::new).toList();
  }

  private void dtoToEntity(CategoryDTO categoryDTO, Category categoryEntity) {
    categoryEntity.setName(categoryDTO.getName());
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException(CATEGORY_NOT_FOUND);
    }
    try {
      repository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Database integrity fail");
    }
  }
}
