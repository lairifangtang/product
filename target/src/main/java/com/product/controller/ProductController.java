package com.product.controller;

import com.product.model.Products;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Base64;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
//import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productid}")
    public ResponseEntity<Products> getProductById(@PathVariable int productid) {
        Optional<Products> product = productService.getProductById(productid);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestParam("cover") MultipartFile cover,
                                                  @RequestParam("name") String name,
                                                  @RequestParam("price") double price,
                                                  @RequestParam(value = "priceSale", required = false) Double priceSale,
                                                  @RequestParam("colors") String colors,
                                                  @RequestParam("status") String status) {
        String base64Cover = null;
        try {
            base64Cover = Base64.getEncoder().encodeToString(cover.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        Products product = new Products();
        product.setCover(base64Cover);
        product.setName(name);
        product.setPrice(price);
        product.setPriceSale(priceSale);
        product.setColors(colors);
        product.setStatus(status);

        Products savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{productid}")
    public ResponseEntity<Products> updateProduct(@PathVariable int productid,
                                                  @RequestParam("cover") MultipartFile cover,
                                                  @RequestParam("name") String name,
                                                  @RequestParam("price") double price,
                                                  @RequestParam(value = "priceSale", required = false) Double priceSale,
                                                  @RequestParam("colors") String colors,
                                                  @RequestParam("status") String status) {
        Optional<Products> productOptional = productService.getProductById(productid);
        if (productOptional.isPresent()) {
            Products product = productOptional.get();
            if (cover != null && !cover.isEmpty()) {
                String base64Cover = null;
                try {
                    base64Cover = Base64.getEncoder().encodeToString(cover.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }

                product.setCover(base64Cover);
            }

            product.setName(name);
            product.setPrice(price);
            product.setPriceSale(priceSale);
            product.setColors(colors);
            product.setStatus(status);

            Products updatedProduct = productService.saveProduct(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{productid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productid) {
        Optional<Products> product = productService.getProductById(productid);
        if (product.isPresent()) {
            productService.deleteProduct(productid);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

