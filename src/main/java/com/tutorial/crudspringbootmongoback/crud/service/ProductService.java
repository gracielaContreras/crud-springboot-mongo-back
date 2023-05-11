package com.tutorial.crudspringbootmongoback.crud.service;

import com.tutorial.crudspringbootmongoback.crud.dto.ProductDTO;
import com.tutorial.crudspringbootmongoback.crud.entities.Product;
import com.tutorial.crudspringbootmongoback.crud.repository.ProductRepository;
import com.tutorial.crudspringbootmongoback.global.exceptions.AttibuteException;
import com.tutorial.crudspringbootmongoback.global.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }
    public Product getOne(int id) throws ResourceNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                                        ("not found product"));
    }
    public Product save(ProductDTO productDTO) throws AttibuteException {
        if( productRepository.existsByName(productDTO.getName()))
            throw new AttibuteException("name already in use");
        int id = autoIncrement();
        Product product = new Product(id, productDTO.getName(), productDTO.getPrice());
        return productRepository.save(product);
    }
    public Product update(int id, ProductDTO productDTO) throws ResourceNotFoundException, AttibuteException {
        Product product = productRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("not found product"));
        if( productRepository.existsByName(productDTO.getName())
                && productRepository.findByName(productDTO.getName()).get().getId() != id)
            throw new AttibuteException("name already in use");

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return productRepository.save(product);
    }
    public Product delete (int id){
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
        return product;
    }
    private int autoIncrement(){
        List<Product> products = productRepository.findAll();
        return products.isEmpty() ? 1 :
                products.stream()
                            .max(Comparator.comparing(Product::getId))
                            .get().getId() + 1;
    }
}
