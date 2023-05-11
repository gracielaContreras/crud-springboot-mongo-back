package com.tutorial.crudspringbootmongoback.crud.controller;

import com.tutorial.crudspringbootmongoback.crud.dto.ProductDTO;
import com.tutorial.crudspringbootmongoback.crud.entities.Product;
import com.tutorial.crudspringbootmongoback.crud.service.ProductService;
import com.tutorial.crudspringbootmongoback.global.dto.MessageDto;
import com.tutorial.crudspringbootmongoback.global.exceptions.AttibuteException;
import com.tutorial.crudspringbootmongoback.global.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping()
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable int id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.getOne(id));
    }
    @PostMapping
    public ResponseEntity<MessageDto> save(@Valid @RequestBody ProductDTO productDTO) throws AttibuteException {
        Product product = productService.save(productDTO);
        String message = new StringBuilder().append("product ")
                                            .append(product.getName())
                                            .append(" have been saved").toString();
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> update(
                                        @PathVariable int id,
                                        @Valid @RequestBody ProductDTO productDTO)
            throws ResourceNotFoundException, AttibuteException {
        Product product = productService.update(id, productDTO);
        String message = new StringBuilder().append("product ")
                                            .append(product.getName())
                                            .append(" have been updated").toString();
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable int id){
        Product product = productService.delete(id);
        String message = new StringBuilder().append("product ")
                .append(product.getName())
                .append(" have been deleted").toString();
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
