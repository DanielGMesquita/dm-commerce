package dev.danielmesquita.dmcommerce.controllers;

import dev.danielmesquita.dmcommerce.dtos.ProductDTO;
import dev.danielmesquita.dmcommerce.dtos.ProductMinDTO;
import dev.danielmesquita.dmcommerce.services.ProductService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired private ProductService service;

  @GetMapping
  public ResponseEntity<Page<ProductMinDTO>> getAllProducts(
      @RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) {
    Page<ProductMinDTO> productMinDTOPage = service.findAll(name, pageable);
    return ResponseEntity.ok(productMinDTOPage);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
    ProductDTO productDTO = service.findById(id);
    return ResponseEntity.ok(productDTO);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  public ResponseEntity<ProductDTO> insertProduct(@Valid @RequestBody ProductDTO productDTO) {
    productDTO = service.insertProduct(productDTO);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(productDTO.getId())
            .toUri();
    return ResponseEntity.created(uri).body(productDTO);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> update(
      @PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
    productDTO = service.updateProduct(id, productDTO);
    return ResponseEntity.ok(productDTO);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
