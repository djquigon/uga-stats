package com.techzilla.ugastats.repositories;

import java.util.List;

import com.techzilla.ugastats.entities.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Represents a team object repository from the uga-stats database
 */
public interface TeamRepository extends JpaRepository<Team, Integer>{
    @Query("Select year from Team u where u.coachId = ?1")
    List<Integer> findCoachYears(int coachId);
}