package com.backend.dev.SimilarProductApi.service;

import com.backend.dev.SimilarProductApi.dto.ProductDetailDTO;
import com.backend.dev.SimilarProductApi.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final WebClient webClient;



    public Mono<List<String>> getSimilarProductIds(String productId) {
        return webClient.get()
                .uri("/product/{productId}/similarids", productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ProductNotFoundException("Product Not found"));
                    }
                    return Mono.error(new RuntimeException("Client error: " + response.statusCode()));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RuntimeException("Server error: " + response.statusCode())))
                .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                .timeout(Duration.ofSeconds(60));
    }

    public Mono<ProductDetailDTO> getProductDetail(String productId) {
        return webClient.get()
                .uri("/product/{productId}", productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ProductNotFoundException("Product with ID " + productId + " not found"));
                    }
                    return Mono.error(new RuntimeException("Client error: " + response.statusCode()));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RuntimeException("Server error: " + response.statusCode())))
                .bodyToMono(ProductDetailDTO.class)
                .timeout(Duration.ofSeconds(60));
    }
}