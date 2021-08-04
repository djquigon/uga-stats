package com.techzilla.ugastats.repositories;

import com.techzilla.ugastats.entities.HeadCoach;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Represents a head_coach repository from the uga-stats database
 */
public interface HeadCoachRepository extends JpaRepository<HeadCoach, Integer>{
    @Query("Select h from HeadCoach h where h.id = ?1")
    HeadCoach findByCoachId(int id);
}