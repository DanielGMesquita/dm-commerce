package dev.danielmesquita.dmcommerce.services;

import dev.danielmesquita.dmcommerce.dtos.ProductDTO;
import dev.danielmesquita.dmcommerce.models.Product;
import dev.danielmesquita.dmcommerce.repositories.ProductRepository;
import java.util.List;
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
    Product productEntity = repository.findById(id).get();
    return new ProductDTO(productEntity);
  }
}
