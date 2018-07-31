package com.bookstore.repository;

import java.util.Optional;

//import java.util.Optional;
//import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import com.bookstore.entity.Role;
import com.bookstore.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	Optional<User> findByRole(String role);
}
