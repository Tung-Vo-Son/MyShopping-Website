package com.example.MyShopping.repository;

import com.example.MyShopping.entity.Product;
import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
