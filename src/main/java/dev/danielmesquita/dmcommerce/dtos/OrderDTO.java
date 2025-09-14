package dev.danielmesquita.dmcommerce.dtos;

import dev.danielmesquita.dmcommerce.enums.OrderStatus;
import dev.danielmesquita.dmcommerce.models.Order;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
  private Long id;
  private Instant moment;
  private OrderStatus orderStatus;
  private UserMinDTO client;
  private PaymentDTO payment;
  private List<OrderItemDTO> items = new ArrayList<>();

  public OrderDTO(Order entity) {
    id = entity.getId();
    moment = entity.getMoment();
    orderStatus = entity.getStatus();
    client = new UserMinDTO(entity.getClient());
    payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
    entity.getItems().forEach(item -> this.items.add(new OrderItemDTO(item)));
  }

  public OrderDTO(
      Long id, Instant moment, OrderStatus orderStatus, UserMinDTO client, PaymentDTO payment) {
    this.id = id;
    this.moment = moment;
    this.orderStatus = orderStatus;
    this.client = client;
    this.payment = payment;
  }

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
