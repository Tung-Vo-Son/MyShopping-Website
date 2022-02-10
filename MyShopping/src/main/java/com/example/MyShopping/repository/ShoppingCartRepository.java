package com.example.MyShopping.repository;

import com.example.MyShopping.entity.Product;
import com.example.MyShopping.entity.ShoppingCart;
import com.example.MyShopping.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {
//    @Modifying
//    @Query("UPDATE Shopping_Cart c SET c.quantity = ?1 WHERE c.user.id = ?2 AND c.product.id = ?3")
//    public void updateQuantity(Integer quantity, Integer customerID, Integer productID);

    public ShoppingCart findByUserAndProduct(User customer, Product product);
    public List<ShoppingCart> findAllByUser(User user);
}
