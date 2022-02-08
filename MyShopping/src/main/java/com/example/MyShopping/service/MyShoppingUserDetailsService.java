package com.example.MyShopping.service;

import com.example.MyShopping.entity.User;
import com.example.MyShopping.repository.UserRepository;
import com.example.MyShopping.security.MyShoppingUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyShoppingUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user != null)
        {
            System.out.println(user);
            return new MyShoppingUserDetails(user);
        }
        else throw new UsernameNotFoundException("Could not find user");
    }
}
