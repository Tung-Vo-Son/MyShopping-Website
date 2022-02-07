package com.example.MyShopping.repository;

import com.example.MyShopping.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
