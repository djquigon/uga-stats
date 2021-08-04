package com.techzilla.ugastats.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;

/**
 * Represents a team object in the uga-stats databases
 */
@Entity
@Table(name = "team")
public class Team {

    @Id
    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "head_coach_coach_id",nullable = false)
    private int coachId;

    
    /** 
     * @return int
     */
    public int getYear() {
        return this.year;
    }

    
    /** 
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    
    /** 
     * @return int
     */
    public int getCoachId() {
        return this.coachId;
    }

    
    /** 
     * @param coachId
     */
    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }
}