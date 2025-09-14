package dev.danielmesquita.dmcommerce.services;

import dev.danielmesquita.dmcommerce.dtos.OrderDTO;
import dev.danielmesquita.dmcommerce.models.Order;
import dev.danielmesquita.dmcommerce.repositories.OrderRepository;
import dev.danielmesquita.dmcommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

  @Autowired
  private OrderRepository repository;

  @Transactional(readOnly = true)
  public OrderDTO findById(Long id) {
    Order orderEntity =
            repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    return new OrderDTO(orderEntity);
  }
}
