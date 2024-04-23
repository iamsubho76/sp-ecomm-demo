package com.sp.ecomm.controller;

import com.sp.ecomm.dto.ProductDTO;
import com.sp.ecomm.dto.Response;
import com.sp.ecomm.exception.B2CErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Product Api", description = "This api is responsible for product related operations")
public interface IProductController {

    @GetMapping
    @Operation(summary = "Get all products")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = B2CErrorDetails.class))}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = B2CErrorDetails.class))})
    })
    ResponseEntity<Response<List<ProductDTO>>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size);

    @GetMapping("/search")
    @Operation(summary = "search all products by their name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = B2CErrorDetails.class))}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = B2CErrorDetails.class))})
    })
    ResponseEntity<Response<List<ProductDTO>>> search(@RequestParam("name") String name, @RequestParam("value") String value);
}