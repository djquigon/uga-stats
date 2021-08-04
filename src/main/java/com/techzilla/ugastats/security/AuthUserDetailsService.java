package com.techzilla.ugastats.security;

import com.techzilla.ugastats.entities.User;
import com.techzilla.ugastats.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Represents a UserDetailsService object for the uga stats webapp
 */
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;
    
    
    /** 
     * Loads a users credentials based on a given email
     * @param email
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User was not found");
        }
        return new AuthUserDetails(user);
    }
    
}
