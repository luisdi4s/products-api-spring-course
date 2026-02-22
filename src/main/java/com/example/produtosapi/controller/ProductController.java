package com.example.produtosapi.controller;

import com.example.produtosapi.model.Product;
import com.example.produtosapi.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("product")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {

        System.out.println("Received product :" + product);
        var id = UUID.randomUUID().toString();
        product.setId(id);
        productRepository.save(product);
        return product;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id,
                                 @RequestBody Product product)
    {
        product.setId(id);
        productRepository.save(product);
        return product;
    }

    @GetMapping
    public List<Product> searchProductByName(@RequestParam("name") String name) {
        return productRepository.findByName(name);
    }

}
