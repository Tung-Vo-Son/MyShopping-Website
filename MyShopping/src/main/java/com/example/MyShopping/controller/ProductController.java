package com.example.MyShopping.controller;

import com.example.MyShopping.entity.Product;
import com.example.MyShopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public String getListAll(Model model){
        List<Product> listProducts = productService.getListAll();
        model.addAttribute("listProducts", listProducts);
        return "product/products";
    }

    @GetMapping("/products/add")
    public String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/products_add";
    }

    @PostMapping("/products/save")
    public String saveProduct(Product product){
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Integer id){
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/products/view/{id}")
    public String viewProduct(@PathVariable(name = "id") Integer id, Model model){
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()) {
            model.addAttribute("product", product);
            return "product/product_view";
        }
        else return null;
    }
}
