package dev.danielmesquita.dmcommerce.dtos;

import dev.danielmesquita.dmcommerce.models.Product;
import jakarta.validation.constraints.*;

public class ProductDTO {

  private Long id;

  @Size(min = 3, max = 80, message = "Name must be 10-80 characters")
  @NotBlank(message = "Name is required")
  private String name;

  @Size(min = 10, message = "Description must have at least 10 characters")
  @NotBlank(message = "Description is required")
  private String description;

  @Positive(message = "Price must be higher than 0")
  private Double price;

  private String imgUrl;

  public ProductDTO() {}

  public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.imgUrl = imgUrl;
  }

  public ProductDTO(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.description = product.getDescription();
    this.price = product.getPrice();
    this.imgUrl = product.getImgUrl();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Double getPrice() {
    return price;
  }

  public String getImgUrl() {
    return imgUrl;
  }
}
