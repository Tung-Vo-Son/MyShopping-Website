package com.example.MyShopping.test;

import com.example.MyShopping.entity.ShoppingCart;
import com.example.MyShopping.entity.Product;
import com.example.MyShopping.entity.User;
import com.example.MyShopping.repository.ShoppingCartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ShoppingCartRepositoryTest {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testSaveItem(){
        Integer customerID = 1;
        Integer productID = 4;

        User customer = testEntityManager.find(User.class, customerID);
        Product product = testEntityManager.find(Product.class, productID);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(customer);
        shoppingCart.setProduct(product);
        shoppingCart.setQuantity(1);
        ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCart);
        assertThat(savedShoppingCart.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindByUserAndProduct(){
        Integer productID = 4;
        Integer customerID = 1;
        User customer = testEntityManager.find(User.class, customerID);
        Product product = testEntityManager.find(Product.class, productID);
        ShoppingCart item = shoppingCartRepository.findByUserAndProduct(customer, product);
        assertThat(item).isNotNull();
        System.out.println(item);
    }
}
