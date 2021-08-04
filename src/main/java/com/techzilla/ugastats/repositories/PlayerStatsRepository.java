package com.techzilla.ugastats.repositories;

import com.techzilla.ugastats.entities.PlayerStats;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Represents a player_stats object repository in the uga-stats database
 */
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, PlayerStatsId>{
    //inherited methods
}