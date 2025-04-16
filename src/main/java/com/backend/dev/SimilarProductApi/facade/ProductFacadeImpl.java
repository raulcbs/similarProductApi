package com.backend.dev.SimilarProductApi.facade;

import com.backend.dev.SimilarProductApi.dto.ProductDetailDTO;
import com.backend.dev.SimilarProductApi.exception.ProductNotFoundException;
import com.backend.dev.SimilarProductApi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {
    private final ProductService productService;

    @Override
    public Mono<List<ProductDetailDTO>> getSimilarProducts(String productId) {
        return productService.getSimilarProductIds(productId)
                .flatMapMany(Flux::fromIterable)
                .flatMap(productService::getProductDetail, 4)
                .collectList()
                .flatMap(products -> {
                    if (products.isEmpty()) {
                        return Mono.error(new ProductNotFoundException("No similar products found"));
                    }
                    return Mono.just(products);
                })
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ProductNotFoundException("Product Not found"));
                    }
                    return Mono.error(e);
                });
    }
}
