package com.techzilla.ugastats.repositories;

import com.techzilla.ugastats.entities.Player;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Represents a player repository from the uga-stats database
 */
public interface PlayerRepository extends JpaRepository<Player, String>{
    //inherited methods
}