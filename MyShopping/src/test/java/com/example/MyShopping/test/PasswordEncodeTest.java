package com.example.MyShopping.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordEncodeTest {
    @Test
    public void testEncodePassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "hondaphuthuy";
        String encodePassword = passwordEncoder.encode(rawPassword);
        System.out.println(encodePassword);
        boolean matchPassword = passwordEncoder.matches(rawPassword, encodePassword);
        assertThat(matchPassword).isTrue();
    }

}
