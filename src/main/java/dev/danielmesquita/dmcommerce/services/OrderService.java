package dev.danielmesquita.dmcommerce.services;

import dev.danielmesquita.dmcommerce.dtos.OrderDTO;
import dev.danielmesquita.dmcommerce.dtos.OrderItemDTO;
import dev.danielmesquita.dmcommerce.enums.OrderStatus;
import dev.danielmesquita.dmcommerce.models.Order;
import dev.danielmesquita.dmcommerce.models.OrderItem;
import dev.danielmesquita.dmcommerce.models.Product;
import dev.danielmesquita.dmcommerce.models.User;
import dev.danielmesquita.dmcommerce.repositories.OrderItemRepository;
import dev.danielmesquita.dmcommerce.repositories.OrderRepository;
import dev.danielmesquita.dmcommerce.repositories.ProductRepository;
import dev.danielmesquita.dmcommerce.services.exceptions.ForbiddenException;
import dev.danielmesquita.dmcommerce.services.exceptions.ResourceNotFoundException;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

  @Autowired private OrderRepository repository;
  @Autowired private ProductRepository productRepository;
  @Autowired private OrderItemRepository orderItemRepository;
  @Autowired private UserService userService;

  @Transactional(readOnly = true)
  public OrderDTO findById(Long id) {
    User authenticatedUser = userService.authenticated();
    Order orderEntity =
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    if (!authenticatedUser.hasRole("ROLE_ADMIN")
        && !orderEntity.getClient().getId().equals(authenticatedUser.getId())) {
      throw new ForbiddenException("Access denied");
    }
    return new OrderDTO(orderEntity);
  }

  @Transactional
  public OrderDTO insert(OrderDTO orderDTO) {
    Order order = new Order();

    order.setClient(userService.authenticated());
    order.setMoment(Instant.now());
    order.setStatus(OrderStatus.WAITING_PAYMENT);

    for (OrderItemDTO itemDTO : orderDTO.getItems()) {
      Product product = productRepository.getReferenceById(itemDTO.getProductId());
      OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
      order.getItems().add(item);
    }

    repository.save(order);
    orderItemRepository.saveAll(order.getItems());

    return new OrderDTO(order);
  }
}
