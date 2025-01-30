package dev.danielmesquita.dmcommerce.services;

import dev.danielmesquita.dmcommerce.dtos.ProductDTO;
import dev.danielmesquita.dmcommerce.models.Product;
import dev.danielmesquita.dmcommerce.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
  @Autowired private ProductRepository repository;

  @Transactional(readOnly = true)
  public List<ProductDTO> findAll() {
    List<Product> products = repository.findAll();
    return products.stream().map(ProductDTO::new).toList();
  }

  public ProductDTO findById(Long id) {
    Product productEntity = repository.findById(id).get();
    return new ProductDTO(productEntity);
  }
}
