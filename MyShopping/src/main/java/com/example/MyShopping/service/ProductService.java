package com.example.MyShopping.service;

import com.example.MyShopping.entity.Product;
import com.example.MyShopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getListAll(){
        return (List<Product>)productRepository.findAll();
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public void delete(Integer id){
        Optional<Product> deleteProduct  = productRepository.findById(id);
        if(deleteProduct.isPresent()) {
            productRepository.deleteById(id);
        }
    }
}
