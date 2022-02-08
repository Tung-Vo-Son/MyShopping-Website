package com.example.MyShopping.test;

import com.example.MyShopping.entity.Role;
import com.example.MyShopping.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreateRole(){
        Role roleAdmin = new Role("Admin", "Manage anything");      // id = 1
        Role roleSales = new Role("Sales", "add product,sale product,...");       // id = 2
        Role roleCustomer = new Role("Customer", "buy product, review,...");     // id = 3
        Role savedRole = roleRepository.save(roleCustomer);
        assertThat(savedRole.getId()).isGreaterThan(0);
    }

}
