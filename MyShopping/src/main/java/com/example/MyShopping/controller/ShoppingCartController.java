package com.example.MyShopping.controller;

import com.example.MyShopping.entity.Product;
import com.example.MyShopping.entity.ShoppingCart;
import com.example.MyShopping.entity.User;
import com.example.MyShopping.service.ShoppingCartService;
import com.example.MyShopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    UserService userService;

    @GetMapping("/cart/{productID}")
    public String addProductToCart(@PathVariable(name = "productID") Integer productID){
        shoppingCartService.addItem(1, productID, getCurrentUser());
        return "redirect:/products";
    }

    private User getCurrentUser(){
        String currentUserEmail = (SecurityContextHolder.getContext().getAuthentication().getName());
        User currentUser = userService.getUserByEmail(currentUserEmail);
        return currentUser;
    }

    @GetMapping("/cart")
    public String viewCart(Model model){
        List<ShoppingCart> listItemCart = shoppingCartService.getListCartItem(getCurrentUser());
        model.addAttribute("listItemCart", listItemCart);
        return "cart/cart";
    }

    @PostMapping("/cart/payment")
    public String paymentCart(){
        shoppingCartService.pay(getCurrentUser());
        return "redirect:/products";
    }
}
