package dev.danielmesquita.dmcommerce.controllers;

import dev.danielmesquita.dmcommerce.dtos.ProductDTO;
import dev.danielmesquita.dmcommerce.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired private ProductService service;

  @GetMapping
  private Page<ProductDTO> getAllProducts(Pageable pageable) {
    return service.findAll(pageable);
  }

  @GetMapping("/{id}")
  private ProductDTO findById(@PathVariable Long id) {
    return service.findById(id);
  }
}
