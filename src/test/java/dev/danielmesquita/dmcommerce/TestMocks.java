package dev.danielmesquita.dmcommerce;

import dev.danielmesquita.dmcommerce.models.Category;
import dev.danielmesquita.dmcommerce.models.Product;
import java.util.HashSet;
import java.util.Set;

public class TestMocks {

  public static final Set<Category> setCategoriesMock =
      new HashSet<>(Set.of(new Category(1L, "Computadores"), new Category(2L, "Tecnologia")));

  public static final Product pcMock =
      new Product(123L, "PC", "Novo PC", 200.00, "www.image.com", setCategoriesMock);

  public static final Product smartphoneMock =
      new Product(123L, "Iphone", "Iphone 15", 200.00, "www.image.com", setCategoriesMock);
}
