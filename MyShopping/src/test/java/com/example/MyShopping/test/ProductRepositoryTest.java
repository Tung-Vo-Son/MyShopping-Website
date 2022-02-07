package com.example.MyShopping.test;

import com.example.MyShopping.entity.Product;
import com.example.MyShopping.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;


    @Test
    public void testCreateProduct(){
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(23);
        product.setQuantity(10);
        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testGetListProduct(){
        Iterable<Product> listProduct = productRepository.findAll();
        listProduct.forEach(p -> System.out.println(p));
    }

    @Test
    public void testGetProductById(){
        Integer productId = 1;
        Optional<Product> product = productRepository.findById(productId);
        System.out.println(product);
        assertThat(product).isNotNull();
    }
}
