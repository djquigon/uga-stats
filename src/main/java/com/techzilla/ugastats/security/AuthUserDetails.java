package com.techzilla.ugastats.security;

import java.util.Collection;

import com.techzilla.ugastats.entities.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Represents a UserDetails object for a uga-stats app user
 */
public class AuthUserDetails implements UserDetails{
    
    private User user;
    
    public AuthUserDetails(User user) {
        this.user = user;
    }

    
    /** 
     * Retrieves Authorities for an authorized user
     * @return Collection<? extends GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    
    /** 
     * Retrieves an authorized users password
     * @return String
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    
    /** 
     * Retrieves an authorized users username
     * @return String
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    
    /** 
     * Checks if a users account is expired
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    
    /** 
     * Checks if a users account is locked
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    
    /** 
     * Checks if a users credentials are expired
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    
    /** 
     * Checks if a user is enabled
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    
    /** 
     * Returns the full name of an authorized user
     * @return String
     */
    public String getFullName(){
        return user.getFirstName() + " " + user.getLastName();
    }
}
