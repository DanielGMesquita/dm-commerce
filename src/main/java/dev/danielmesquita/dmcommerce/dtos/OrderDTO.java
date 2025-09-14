package dev.danielmesquita.dmcommerce.dtos;

import dev.danielmesquita.dmcommerce.enums.OrderStatus;
import dev.danielmesquita.dmcommerce.models.Order;
import dev.danielmesquita.dmcommerce.models.OrderItem;
import jakarta.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
  private Long id;
  private Instant moment;
  private OrderStatus orderStatus;
  private UserMinDTO client;
  private PaymentDTO payment;

  @NotEmpty(message = "Order must have at least one item")
  private List<OrderItemDTO> items = new ArrayList<>();

  public OrderDTO(Order entity) {
    id = entity.getId();
    moment = entity.getMoment();
    orderStatus = entity.getStatus();
    client = new UserMinDTO(entity.getClient());
    payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
    for (OrderItem item : entity.getItems()) {
      items.add(new OrderItemDTO(item));
    }
  }

  public OrderDTO(
      Long id, Instant moment, OrderStatus orderStatus, UserMinDTO client, PaymentDTO payment) {
    this.id = id;
    this.moment = moment;
    this.orderStatus = orderStatus;
    this.client = client;
    this.payment = payment;
  }

  public OrderDTO() {}

  public List<OrderItemDTO> getItems() {
    return items;
  }

  public Long getId() {
    return id;
  }

  public Instant getMoment() {
    return moment;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public UserMinDTO getClient() {
    return client;
  }

  public PaymentDTO getPayment() {
    return payment;
  }

  public Double getTotal() {
    double sum = 0.0;
    for (OrderItemDTO item : items) {
      sum += item.getSubTotal();
    }
    return sum;
  }
}
