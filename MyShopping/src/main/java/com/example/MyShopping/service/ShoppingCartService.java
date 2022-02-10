package com.example.MyShopping.service;

import com.example.MyShopping.entity.Product;
import com.example.MyShopping.entity.ShoppingCart;
import com.example.MyShopping.entity.User;
import com.example.MyShopping.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private EntityManager entityManager;

    public void addItem(Integer quantity, Integer productID, User customer){
        Integer updateQuantity = quantity;
        Product product = entityManager.find(Product.class, productID);
        System.out.println(product);
        ShoppingCart item = shoppingCartRepository.findByUserAndProduct(customer, product);
        System.out.println(customer);
        if(item != null){
            updateQuantity = item.getQuantity() + quantity;
        }
        else{
            item = new ShoppingCart();
            item.setProduct(product);
            item.setUser(customer);
        }
        item.setQuantity(updateQuantity);
        System.out.println(item);
        shoppingCartRepository.save(item);
    }

    public List<ShoppingCart> getListCartItem(User customer){
       return (List<ShoppingCart>)shoppingCartRepository.findAllByUser(customer);
    }

    public void pay(User customer){
        List<ShoppingCart> items = getListCartItem(customer);
        for(ShoppingCart item : items){
            shoppingCartRepository.deleteById(item.getId());
        }
    }
}
