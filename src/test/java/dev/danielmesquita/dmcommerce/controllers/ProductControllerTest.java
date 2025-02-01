package dev.danielmesquita.dmcommerce.controllers;

import dev.danielmesquita.dmcommerce.TestMocks;
import dev.danielmesquita.dmcommerce.models.Product;
import dev.danielmesquita.dmcommerce.services.ProductService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductControllerTest {
  @Mock private ProductService productService;

  @InjectMocks private ProductController productController;

  private final Product productMock = TestMocks.productMock;
}
