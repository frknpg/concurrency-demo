package me.frknpg.concurrencyfix.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void upsert(CreateProductRequest createProductRequest) {
        Product product = productRepository.findByUuid(createProductRequest.getUuid())
                .orElseGet(() -> Product.builder()
                        .uuid(createProductRequest.getUuid())
                        .amount(createProductRequest.getAmount())
                        .build());
        productRepository.save(product);
    }
}
