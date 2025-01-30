package dev.danielmesquita.dmcommerce.dtos;

import dev.danielmesquita.dmcommerce.models.Product;

public class ProductDTO {
  private Long id;
  private String name;
  private String description;
  private Double price;
  private String imgUrl;

  public ProductDTO() {}

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
