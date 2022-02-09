package com.example.MyShopping.service;

import com.example.MyShopping.entity.Role;
import com.example.MyShopping.entity.User;
import com.example.MyShopping.repository.RoleRepository;
import com.example.MyShopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll(){
        return (List<User>) userRepository.findAll();
    }

    public List<Role> getListRole(){
        return (List<Role>) roleRepository.findAll();
    }

    public void save(User user){
        endcodePassword(user);
        userRepository.save(user);
    }

    public void delete(Integer id){
        Optional<User> deleteUser = userRepository.findById(id);
        if(deleteUser.isPresent()){
            userRepository.deleteById(id);
        }
    }

    public void addRoleCustomer(User user){
        Optional<Role> roleCustomer = roleRepository.findById(3);
        user.addRole(roleCustomer.get());
    }

    private void endcodePassword(User user){
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
    }
}
