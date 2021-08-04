package com.techzilla.ugastats.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * Represents a head_coach from the uga-stats database
 */
@Entity
@Table(name = "head_coach")
public class HeadCoach {

    @Id
    @Column(name = "coach_id", nullable = false)
    private int id;

    @Column(name = "first_name",nullable = false, length = 45)
    private String firstName;
    
    @Column(name = "last_name",nullable = false, length = 45)
    private String lastName;  

    
    /** 
     * @return int
     */
    public int getId() {
        return this.id;
    }

    
    /** 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * @return String
     */
    public String getFirstName() {
        return this.firstName;
    }

    
    /** 
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    /** 
     * @return String
     */
    public String getLastName() {
        return this.lastName;
    }

    
    /** 
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    /** 
     * @return String
     */
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }
}