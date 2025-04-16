package com.backend.dev.SimilarProductApi.service;

import com.backend.dev.SimilarProductApi.dto.ProductDetailDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {

    Mono<List<String>> getSimilarProductIds(String productId);

    Mono<ProductDetailDTO> getProductDetail(String productId);

}