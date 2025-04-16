package com.backend.dev.SimilarProductApi.facade;

import com.backend.dev.SimilarProductApi.dto.ProductDetailDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductFacade {

    Mono<List<ProductDetailDTO>> getSimilarProducts(String productId);

}
