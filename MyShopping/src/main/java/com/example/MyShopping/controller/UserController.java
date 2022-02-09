package com.example.MyShopping.controller;

import com.example.MyShopping.entity.Role;
import com.example.MyShopping.entity.User;
import com.example.MyShopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    EntityManager entityManager;

    @GetMapping("/users")
    public String listAll(Model model){
        List<User> listUser = userService.listAll();
        model.addAttribute("listUser", listUser);
        return "user/users";            // html file path direction.
    }

    @GetMapping("/users/add")
    public String newUser(Model model){
        User user = new User();
        List<Role> listRoles = userService.getListRole();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "user/user_add";
    }

    @PostMapping("/users/save")
    public String saveUser(User user){
        System.out.println(user);
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Integer id){
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping("/signup")
    public String signupCustomer(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user/signup";
    }

    @PostMapping("/customers/signup")
    public String saveSignUp(User customer){
        System.out.println(customer);
        userService.addRoleCustomer(customer);
        userService.save(customer);
        return "redirect:/login";
    }
}
