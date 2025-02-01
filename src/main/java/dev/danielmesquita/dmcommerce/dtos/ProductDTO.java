package dev.danielmesquita.dmcommerce.dtos;

import dev.danielmesquita.dmcommerce.models.Product;

public class ProductDTO {
  private final Long id;
  private final String name;
  private final String description;
  private final Double price;
  private final String imgUrl;

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
