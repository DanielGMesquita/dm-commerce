package dev.danielmesquita.dmcommerce.services;

import dev.danielmesquita.dmcommerce.dtos.ProductDTO;
import dev.danielmesquita.dmcommerce.models.Product;
import dev.danielmesquita.dmcommerce.repositories.ProductRepository;
import dev.danielmesquita.dmcommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
  @Autowired private ProductRepository repository;

  @Transactional(readOnly = true)
  public Page<ProductDTO> findAll(Pageable pageable) {
    Page<Product> products = repository.findAll(pageable);
    return products.map(ProductDTO::new);
  }

  public ProductDTO findById(Long id) {
    Product productEntity =
        repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
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
    Product productEntity = repository.getReferenceById(id);
    dtoToEntity(productDTO, productEntity);

    productEntity = repository.save(productEntity);

    return new ProductDTO(productEntity);
  }

  private void dtoToEntity(ProductDTO productDTO, Product productEntity) {
    productEntity.setDescription(productDTO.getDescription());
    productEntity.setName(productDTO.getName());
    productEntity.setPrice(productDTO.getPrice());
    productEntity.setImgUrl(productDTO.getImgUrl());
  }

  @Transactional
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
