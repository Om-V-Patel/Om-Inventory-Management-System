package com.om.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.om.app.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//	@Query("SELECT u FROM User u WHERE u.email = ?1")
public User findByEmail(String email);

	
	
}
