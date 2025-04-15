package com.backend.dev.SimilarProductApi.controller;

import com.backend.dev.SimilarProductApi.dto.ErrorDTO;
import com.backend.dev.SimilarProductApi.dto.ProductDetailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SimilarProductController {

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
    public ResponseEntity<?> getSimilarProducts(@PathVariable String productId) {
        if ("1".equals(productId)) {
            List<ProductDetailDTO> similarProducts = new ArrayList<>();
            similarProducts.add(new ProductDetailDTO()
                    .setId("2")
                    .setName("Similar Product 1")
                    .setPrice(99.99)
                    .setAvailability(true));
            similarProducts.add(new ProductDetailDTO()
                    .setId("3")
                    .setName("Similar Product 3")
                    .setPrice(149.99)
                    .setAvailability(true));
            similarProducts.add(new ProductDetailDTO()
                    .setId("4")
                    .setName("Similar Product 3")
                    .setPrice(79.99)
                    .setAvailability(false));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(similarProducts);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO().setMessage("Product Not found"));
    }
}