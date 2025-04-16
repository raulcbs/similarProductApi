package com.backend.dev.SimilarProductApi.controller;

import com.backend.dev.SimilarProductApi.dto.ErrorDTO;
import com.backend.dev.SimilarProductApi.dto.ProductDetailDTO;
import com.backend.dev.SimilarProductApi.exception.ProductNotFoundException;
import com.backend.dev.SimilarProductApi.facade.ProductFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SimilarProductController {
    private final ProductFacade productFacade;

    @Operation(summary = "Similar products", operationId = "get-product-similar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDetailDTO.class))
                    )),
            @ApiResponse(responseCode = "404", description = "Product Not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    ))
    })
    @GetMapping("/product/{productId}/similar")
    public Mono<ResponseEntity<List<ProductDetailDTO>>> getSimilarProducts(@PathVariable String productId) {
        return productFacade.getSimilarProducts(productId)
                .map(ResponseEntity::ok)
                .onErrorResume(ProductNotFoundException.class,
                        e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }
}