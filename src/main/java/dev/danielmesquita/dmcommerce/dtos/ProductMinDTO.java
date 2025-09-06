package dev.danielmesquita.dmcommerce.dtos;

import dev.danielmesquita.dmcommerce.models.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.Objects;

public class ProductMinDTO {
  private Long id;

  @Size(min = 3, max = 80, message = "Name must be 10-80 characters")
  @NotBlank(message = "Name is required")
  private String name;

  @Positive(message = "Price must be higher than 0")
  private Double price;

  private String imgUrl;

  public ProductMinDTO() {}

  public ProductMinDTO(Long id, String name, String description, Double price, String imgUrl) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.imgUrl = imgUrl;
  }

  public ProductMinDTO(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.price = product.getPrice();
    this.imgUrl = product.getImgUrl();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Double getPrice() {
    return price;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ProductMinDTO that = (ProductMinDTO) o;
    return Double.compare(that.price, price) == 0 && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, price);
  }
}
