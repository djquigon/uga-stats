package com.techzilla.ugastats.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "player_id", nullable = false, unique = true, length = 45)
    private String id;

    @Column(name = "first_name",nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name",nullable = false, length = 45)
    private String lastName;

    @Column(name = "unit",nullable = false)
    private String unit;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
