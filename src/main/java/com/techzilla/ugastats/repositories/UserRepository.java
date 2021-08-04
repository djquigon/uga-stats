package com.techzilla.ugastats.repositories;

import com.techzilla.ugastats.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Represents a user repository from the uga stats database
 */
public interface UserRepository extends JpaRepository<User, Long>{

    /**
     * Finds a user by his email in the database
     * @param email
     * @return user witht he associated email
     */
    @Query("Select u from User u where u.email = ?1")
    User findByEmail(String email);

    //remember list
}