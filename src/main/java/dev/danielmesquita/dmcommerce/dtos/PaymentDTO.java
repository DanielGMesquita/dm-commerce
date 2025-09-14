package dev.danielmesquita.dmcommerce.dtos;

import dev.danielmesquita.dmcommerce.models.Payment;
import java.time.Instant;

public class PaymentDTO {
  private Long id;
  private Instant moment;

  public PaymentDTO(Payment entity) {
    id = entity.getId();
    moment = entity.getMoment();
  }

  public PaymentDTO(Long id, Instant moment) {
    this.id = id;
    this.moment = moment;
  }

  public Long getId() {
    return id;
  }

  public Instant getMoment() {
    return moment;
  }
}
