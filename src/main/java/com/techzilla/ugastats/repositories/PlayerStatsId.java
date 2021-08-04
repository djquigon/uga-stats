package com.techzilla.ugastats.repositories;

import java.io.Serializable;

/**
 * Represents a player_stats object's id in the uga-stats database
 */
public class PlayerStatsId implements Serializable {
    private String playerId;
    private int teamYear;

    public PlayerStatsId(){}

    public PlayerStatsId(String playerId, int teamYear) {
        this.playerId = playerId;
        this.teamYear = teamYear;
    }
    // equals() and hashCode()
}