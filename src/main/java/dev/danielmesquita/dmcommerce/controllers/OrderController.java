package dev.danielmesquita.dmcommerce.controllers;

import dev.danielmesquita.dmcommerce.dtos.OrderDTO;
import dev.danielmesquita.dmcommerce.services.OrderService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/orders")
public class OrderController {

  @Autowired private OrderService service;

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
  @GetMapping(value = "/{id}")
  public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
    OrderDTO orderDTO = service.findById(id);
    return ResponseEntity.ok(orderDTO);
  }

  @PreAuthorize("hasRole('ROLE_CLIENT')")
  @PostMapping
  public ResponseEntity<OrderDTO> insertOrder(@Valid @RequestBody OrderDTO orderDTO) {
    orderDTO = service.insert(orderDTO);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(orderDTO.getId())
            .toUri();
    return ResponseEntity.created(uri).body(orderDTO);
  }
}
