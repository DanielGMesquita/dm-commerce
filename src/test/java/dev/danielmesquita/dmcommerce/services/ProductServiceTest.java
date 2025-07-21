package dev.danielmesquita.dmcommerce.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

//  @InjectMocks private ProductService productService;
//
//  @Mock private ProductRepository productRepository;
//
//  private final Product pcMock = TestMocks.pcMock;
//
//  private final Product smartphoneMock = TestMocks.smartphoneMock;
//
//  private final ProductDTO pcMockDTO = new ProductDTO(pcMock);
//
//  private final ProductDTO smartphoneDTO = new ProductDTO(smartphoneMock);
//
////  @Test
////  void findAllSuccessful() {
////    Pageable pageable = PageRequest.of(0, 10);
////    Page<Product> page = new PageImpl<>(List.of(pcMock, smartphoneMock));
////    when(productRepository.searchByName("PC", pageable)).thenReturn(page);
////
////    Page<ProductDTO> expected = new PageImpl<>(List.of(pcMockDTO, smartphoneDTO));
////
////    Page<ProductDTO> result = productService.findAll("PC", pageable);
////
////    assertNotNull(result);
////    assertIterableEquals(expected.getContent(), result.getContent());
////  }
}
