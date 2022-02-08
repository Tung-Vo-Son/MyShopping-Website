package com.example.MyShopping.test;

import com.example.MyShopping.entity.Role;
import com.example.MyShopping.entity.User;
import com.example.MyShopping.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    public String encodePassword(String rawPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(rawPassword);
        return encodePassword;
    }

    @Test
    public void testCreateUser(){
        Role roleAdmin = testEntityManager.find(Role.class, 1);
        String password = "11111111";
        User userAdmin = new User("Tung", "tungson@gmail.com", encodePassword(password));
        userAdmin.addRole(roleAdmin);
        User savedUser = userRepository.save(userAdmin);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUser(){
        List<User> listUsers = (List<User>) userRepository.findAll();
        listUsers.forEach(u -> System.out.println(u.toString()));
    }

    @Test
    public void testFindById(){
        Optional<User> userFind = userRepository.findById(1);
        System.out.println(userFind);
        assertThat(userFind).isNotNull();
    }

    @Test
    public void testDeleteUser(){
        Integer deleteID = 2;
        userRepository.deleteById(deleteID);
    }

    @Test
    public void testFindByEmail(){
        String email = "harrypotter@gmail.com";
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        assertThat(user).isNotNull();
    }
}
