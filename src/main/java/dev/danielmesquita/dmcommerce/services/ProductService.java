package dev.danielmesquita.dmcommerce.services;

import dev.danielmesquita.dmcommerce.dtos.CategoryDTO;
import dev.danielmesquita.dmcommerce.dtos.ProductDTO;
import dev.danielmesquita.dmcommerce.models.Category;
import dev.danielmesquita.dmcommerce.models.Product;
import dev.danielmesquita.dmcommerce.repositories.CategoryRepository;
import dev.danielmesquita.dmcommerce.repositories.ProductRepository;
import dev.danielmesquita.dmcommerce.services.exceptions.DatabaseException;
import dev.danielmesquita.dmcommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
  @Autowired private ProductRepository repository;
  @Autowired private CategoryRepository categoryRepository;
  private static final String PRODUCT_NOT_FOUND = "Product not found";

  @Transactional(readOnly = true)
  public Page<ProductDTO> findAll(Pageable pageable) {
    Page<Product> products = repository.findAll(pageable);
    return products.map(ProductDTO::new);
  }

  public ProductDTO findById(Long id) {
    Product productEntity =
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND));
    return new ProductDTO(productEntity);
  }

  @Transactional
  public ProductDTO insertProduct(ProductDTO productDTO) {
    Product productEntity = new Product();
    dtoToEntity(productDTO, productEntity);

    productEntity = repository.save(productEntity);

    return new ProductDTO(productEntity);
  }

  @Transactional
  public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
    try {
      Product productEntity = repository.getReferenceById(id);
      dtoToEntity(productDTO, productEntity);

      productEntity = repository.save(productEntity);

      return new ProductDTO(productEntity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
    }
  }

  private void dtoToEntity(ProductDTO productDTO, Product productEntity) {
    productEntity.setDescription(productDTO.getDescription());
    productEntity.setName(productDTO.getName());
    productEntity.setPrice(productDTO.getPrice());
    productEntity.setImgUrl(productDTO.getImgUrl());
    for (CategoryDTO categoryDTO : productDTO.getCategories()) {
      Category entity = categoryRepository.getReferenceById(categoryDTO.getId());
      productEntity.getCategories().add(entity);
    }
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
    }
    try {
      repository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Database integrity fail");
    }
  }
}
