package dev.danielmesquita.dmcommerce.services;

import dev.danielmesquita.dmcommerce.dtos.ProductDTO;
import dev.danielmesquita.dmcommerce.models.Product;
import dev.danielmesquita.dmcommerce.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired private ProductRepository repository;

  public List<ProductDTO> findAll() {
    List<ProductDTO> output = new ArrayList<>();
    List<Product> products = repository.findAll();
    for (Product product : products) {
      output.add(new ProductDTO(product));
    }
    return output;
  }

  public ProductDTO findById(Long id) {
    return repository
        .findById(id)
        .map(ProductDTO::new)
        .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
  }
}
