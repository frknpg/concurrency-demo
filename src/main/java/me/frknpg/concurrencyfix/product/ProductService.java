package me.frknpg.concurrencyfix.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void save(CreateProductRequest createProductRequest) {
        Product product = productRepository.findByUuid(createProductRequest.getUuid())
                .orElseGet(() -> Product.builder()
                        .uuid(createProductRequest.getUuid())
                        .amount(createProductRequest.getAmount())
                        .build());
        productRepository.save(product);
    }
}
