package dev.danielmesquita.dmcommerce.controllers;

import dev.danielmesquita.dmcommerce.dtos.ProductDTO;
import dev.danielmesquita.dmcommerce.services.ProductService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired private ProductService service;

  @GetMapping
  private ResponseEntity<Page<ProductDTO>> getAllProducts(
      @RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) {
    Page<ProductDTO> pageProductDTO = service.findAll(name, pageable);
    return ResponseEntity.ok(pageProductDTO);
  }

  @GetMapping("/{id}")
  private ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
    ProductDTO productDTO = service.findById(id);
    return ResponseEntity.ok(productDTO);
  }

  @PostMapping
  private ResponseEntity<ProductDTO> insertProduct(@Valid @RequestBody ProductDTO productDTO) {
    productDTO = service.insertProduct(productDTO);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(productDTO.getId())
            .toUri();
    return ResponseEntity.created(uri).body(productDTO);
  }

  @PutMapping("/{id}")
  private ResponseEntity<ProductDTO> update(
      @PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
    productDTO = service.updateProduct(id, productDTO);
    return ResponseEntity.ok(productDTO);
  }

  @DeleteMapping("/{id}")
  private ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
