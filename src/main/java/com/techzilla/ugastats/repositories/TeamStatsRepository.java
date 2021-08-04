package com.techzilla.ugastats.repositories;

import com.techzilla.ugastats.entities.TeamStats;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Represents a team_stats repository from the uga-stats database
 */
public interface TeamStatsRepository extends JpaRepository<TeamStats, Integer>{
    //inherited methods
}