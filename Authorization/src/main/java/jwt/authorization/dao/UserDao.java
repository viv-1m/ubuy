package jwt.authorization.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jwt.authorization.entity.User;

// CONTAINS AUTHENTICATION CREDENTIALS

public interface UserDao extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
}