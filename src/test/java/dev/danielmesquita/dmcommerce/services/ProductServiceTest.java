package dev.danielmesquita.dmcommerce.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import dev.danielmesquita.dmcommerce.TestMocks;
import dev.danielmesquita.dmcommerce.dtos.ProductDTO;
import dev.danielmesquita.dmcommerce.models.Product;
import dev.danielmesquita.dmcommerce.repositories.ProductRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @InjectMocks private ProductService productService;

  @Mock private ProductRepository productRepository;

  private final Product pcMock = TestMocks.pcMock;

  private final Product smartphoneMock = TestMocks.smartphoneMock;

  private final ProductDTO pcMockDTO = new ProductDTO(pcMock);

  private final ProductDTO smartphoneDTO = new ProductDTO(smartphoneMock);

  @Test
  void findAllSuccessful() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<Product> page = new PageImpl<>(List.of(pcMock, smartphoneMock));
    when(productRepository.findAll(pageable)).thenReturn(page);

    Page<ProductDTO> expected = new PageImpl<>(List.of(pcMockDTO, smartphoneDTO));

    Page<ProductDTO> result = productService.findAll(pageable);

    assertNotNull(result);
    assertIterableEquals(expected.getContent(), result.getContent());
  }
}
