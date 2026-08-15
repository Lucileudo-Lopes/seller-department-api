package com.lucieudo.seller_department_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucieudo.seller_department_api.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
