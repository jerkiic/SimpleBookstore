package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
