package com.project.Books_Cabinet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Books_Cabinet.model.Users;

import java.util.Collection;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{

	//List<Users> findByemail(String email);

	@Query("from Users where Email = ?1 and Password = ?2")
	Collection<Users> ValidUser(String Email, String Password);

}
