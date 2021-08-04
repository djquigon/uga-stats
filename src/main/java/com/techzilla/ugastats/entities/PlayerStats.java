package com.techzilla.ugastats.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.techzilla.ugastats.repositories.PlayerStatsId;

import javax.persistence.Column;

/**
 * Represents a player_stats object from the uga-stats database
 */
@Entity
@Table(name = "player_stats")
@IdClass(PlayerStatsId.class)
public class PlayerStats {

    @Id
    @Column(name = "player_player_id", nullable = false)
    private String playerId;

    @Id
    @Column(name = "team_year", nullable = false)
    private int teamYear;

    @Column(name = "passing_completions")
    private int passingCompletions;

    @Column(name = "passing_attempts")
    private int passingAttempts;

    @Column(name = "passing_percentage")
    private float passingPercentage;

    @Column(name = "passing_yards")
    private int passingYards;

    @Column(name = "passing_yards_per_attempt")
    private float passingYPA;

    @Column(name = "passing_touchdowns")
    private int passingTouchdowns;

    @Column(name = "passing_interceptions")
    private int passingInterceptions;

    @Column(name = "passing_rating")
    private float passingRating;

    @Column(name = "rushing_attempts")
    private int rushingAttempts;

    @Column(name = "rushing_yards")
    private int rushingYards;

    @Column(name = "rushing_yards_per_attempt")
    private float rushingYPA;

    @Column(name = "rushing_touchdowns")
    private int rushingTouchdowns;

    @Column(name = "receiving_catches")
    private int receivingCatches;

    @Column(name = "receiving_yards")
    private int receivingYards;

    @Column(name = "receiving_yards_per_catch")
    private float receivingYPC;

    @Column(name = "receiving_touchdowns")
    private int receivingTouchdowns;

    @Column(name = "extra_point_made")
    private int extraPointMade;

    @Column(name = "extra_point_attempts")
    private int extraPointAttempts;

    @Column(name = "extra_point_avg")
    private float extraPointAverage;

    @Column(name = "field_goal_made")
    private int fieldGoalMade;

    @Column(name = "field_goal_attempts")
    private int fieldGoalAttempts;

    @Column(name = "field_goal_avg")
    private float fieldGoalAverage;

    @Column(name = "punt_attempts")
    private int puntAttempts;

    @Column(name = "punt_yards")
    private int puntYards;

    @Column(name = "punt_avg")
    private float puntAverage;

    @Column(name = "tackles_solo")
    private int tacklesSolo;

    @Column(name = "tackles_assisted")
    private int tacklesAssisted;

    @Column(name = "tackles_total")
    private int tacklesTotal;

    @Column(name = "tackles_loss")
    private float tacklesLoss;

    @Column(name = "sacks")
    private float sacks;

    @Column(name = "interception_catches")
    private int inerceptionCatches;

    @Column(name = "interception_yards")
    private int interceptionYards;

    @Column(name = "interception_yards_per_catch")
    private float interceptionYPC;

    @Column(name = "interception_touchdowns")
    private int interceptionTouchdowns;

    @Column(name = "passes_defended")
    private int passesDefended;

    @Column(name = "fumbles_recovered")
    private int fumblesRecovered;

    @Column(name = "fumble_yards")
    private int fumbleYards;

    @Column(name = "fumble_touchdowns")
    private int fumbleTouchdowns;

    @Column(name = "fumbles_forced")
    private int fumblesForced;


    
    /** 
     * @return String
     */
    public String getPlayerId() {
        return this.playerId;
    }

    
    /** 
     * @param playerId
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    
    /** 
     * @return int
     */
    public int getTeamYear() {
        return this.teamYear;
    }

    
    /** 
     * @param teamYear
     */
    public void setTeamYear(int teamYear) {
        this.teamYear = teamYear;
    }

    
    /** 
     * @return int
     */
    public int getPassingCompletions() {
        return this.passingCompletions;
    }

    
    /** 
     * @param passingCompletions
     */
    public void setPassingCompletions(int passingCompletions) {
        this.passingCompletions = passingCompletions;
    }

    
    /** 
     * @return int
     */
    public int getPassingAttempts() {
        return this.passingAttempts;
    }

    
    /** 
     * @param passingAttempts
     */
    public void setPassingAttempts(int passingAttempts) {
        this.passingAttempts = passingAttempts;
    }

    
    /** 
     * @return float
     */
    public float getPassingPercentage() {
        return this.passingPercentage;
    }

    
    /** 
     * @param passingPercentage
     */
    public void setPassingPercentage(float passingPercentage) {
        this.passingPercentage = passingPercentage;
    }

    
    /** 
     * @return int
     */
    public int getPassingYards() {
        return this.passingYards;
    }

    
    /** 
     * @param passingYards
     */
    public void setPassingYards(int passingYards) {
        this.passingYards = passingYards;
    }

    
    /** 
     * @return float
     */
    public float getPassingYPA() {
        return this.passingYPA;
    }

    
    /** 
     * @param passingYPA
     */
    public void setPassingYPA(float passingYPA) {
        this.passingYPA = passingYPA;
    }

    
    /** 
     * @return int
     */
    public int getPassingTouchdowns() {
        return this.passingTouchdowns;
    }

    
    /** 
     * @param passingTouchdowns
     */
    public void setPassingTouchdowns(int passingTouchdowns) {
        this.passingTouchdowns = passingTouchdowns;
    }

    
    /** 
     * @return int
     */
    public int getPassingInterceptions() {
        return this.passingInterceptions;
    }

    
    /** 
     * @param passingInterceptions
     */
    public void setPassingInterceptions(int passingInterceptions) {
        this.passingInterceptions = passingInterceptions;
    }

    
    /** 
     * @return float
     */
    public float getPassingRating() {
        return this.passingRating;
    }

    
    /** 
     * @param passingRating
     */
    public void setPassingRating(float passingRating) {
        this.passingRating = passingRating;
    }

    
    /** 
     * @return int
     */
    public int getRushingAttempts() {
        return this.rushingAttempts;
    }

    
    /** 
     * @param rushingAttempts
     */
    public void setRushingAttempts(int rushingAttempts) {
        this.rushingAttempts = rushingAttempts;
    }

    
    /** 
     * @return int
     */
    public int getRushingYards() {
        return this.rushingYards;
    }

    
    /** 
     * @param rushingYards
     */
    public void setRushingYards(int rushingYards) {
        this.rushingYards = rushingYards;
    }

    
    /** 
     * @return float
     */
    public float getRushingYPA() {
        return this.rushingYPA;
    }

    
    /** 
     * @param rushingYPA
     */
    public void setRushingYPA(float rushingYPA) {
        this.rushingYPA = rushingYPA;
    }

    
    /** 
     * @return int
     */
    public int getRushingTouchdowns() {
        return this.rushingTouchdowns;
    }

    
    /** 
     * @param rushingTouchdowns
     */
    public void setRushingTouchdowns(int rushingTouchdowns) {
        this.rushingTouchdowns = rushingTouchdowns;
    }

    
    /** 
     * @return int
     */
    public int getReceivingCatches() {
        return this.receivingCatches;
    }

    
    /** 
     * @param receivingCatches
     */
    public void setReceivingCatches(int receivingCatches) {
        this.receivingCatches = receivingCatches;
    }

    
    /** 
     * @return int
     */
    public int getReceivingYards() {
        return this.receivingYards;
    }

    
    /** 
     * @param receivingYards
     */
    public void setReceivingYards(int receivingYards) {
        this.receivingYards = receivingYards;
    }

    
    /** 
     * @return float
     */
    public float getReceivingYPC() {
        return this.receivingYPC;
    }

    
    /** 
     * @param receivingYPC
     */
    public void setReceivingYPC(float receivingYPC) {
        this.receivingYPC = receivingYPC;
    }

    
    /** 
     * @return int
     */
    public int getReceivingTouchdowns() {
        return this.receivingTouchdowns;
    }

    
    /** 
     * @param receivingTouchdowns
     */
    public void setReceivingTouchdowns(int receivingTouchdowns) {
        this.receivingTouchdowns = receivingTouchdowns;
    }

    
    /** 
     * @return int
     */
    public int getExtraPointMade() {
        return this.extraPointMade;
    }

    
    /** 
     * @param extraPointMade
     */
    public void setExtraPointMade(int extraPointMade) {
        this.extraPointMade = extraPointMade;
    }

    
    /** 
     * @return int
     */
    public int getExtraPointAttempts() {
        return this.extraPointAttempts;
    }

    
    /** 
     * @param extraPointAttempts
     */
    public void setExtraPointAttempts(int extraPointAttempts) {
        this.extraPointAttempts = extraPointAttempts;
    }

    
    /** 
     * @return float
     */
    public float getExtraPointAverage() {
        return this.extraPointAverage;
    }

    
    /** 
     * @param extraPointAverage
     */
    public void setExtraPointAverage(float extraPointAverage) {
        this.extraPointAverage = extraPointAverage;
    }

    
    /** 
     * @return int
     */
    public int getFieldGoalMade() {
        return this.fieldGoalMade;
    }

    
    /** 
     * @param fieldGoalMade
     */
    public void setFieldGoalMade(int fieldGoalMade) {
        this.fieldGoalMade = fieldGoalMade;
    }

    
    /** 
     * @return int
     */
    public int getFieldGoalAttempts() {
        return this.fieldGoalAttempts;
    }

    
    /** 
     * @param fieldGoalAttempts
     */
    public void setFieldGoalAttempts(int fieldGoalAttempts) {
        this.fieldGoalAttempts = fieldGoalAttempts;
    }

    
    /** 
     * @return float
     */
    public float getFieldGoalAverage() {
        return this.fieldGoalAverage;
    }

    
    /** 
     * @param fieldGoalAverage
     */
    public void setFieldGoalAverage(float fieldGoalAverage) {
        this.fieldGoalAverage = fieldGoalAverage;
    }

    
    /** 
     * @return int
     */
    public int getPuntAttempts() {
        return this.puntAttempts;
    }

    
    /** 
     * @param puntAttempts
     */
    public void setPuntAttempts(int puntAttempts) {
        this.puntAttempts = puntAttempts;
    }

    
    /** 
     * @return int
     */
    public int getPuntYards() {
        return this.puntYards;
    }

    
    /** 
     * @param puntYards
     */
    public void setPuntYards(int puntYards) {
        this.puntYards = puntYards;
    }

    
    /** 
     * @return float
     */
    public float getPuntAverage() {
        return this.puntAverage;
    }

    
    /** 
     * @param puntAverage
     */
    public void setPuntAverage(float puntAverage) {
        this.puntAverage = puntAverage;
    }

    
    /** 
     * @return int
     */
    public int getTacklesSolo() {
        return this.tacklesSolo;
    }

    
    /** 
     * @param tacklesSolo
     */
    public void setTacklesSolo(int tacklesSolo) {
        this.tacklesSolo = tacklesSolo;
    }

    
    /** 
     * @return int
     */
    public int getTacklesAssisted() {
        return this.tacklesAssisted;
    }

    
    /** 
     * @param tacklesAssisted
     */
    public void setTacklesAssisted(int tacklesAssisted) {
        this.tacklesAssisted = tacklesAssisted;
    }

    
    /** 
     * @return int
     */
    public int getTacklesTotal() {
        return this.tacklesTotal;
    }

    
    /** 
     * @param tacklesTotal
     */
    public void setTacklesTotal(int tacklesTotal) {
        this.tacklesTotal = tacklesTotal;
    }

    
    /** 
     * @return float
     */
    public float getTacklesLoss() {
        return this.tacklesLoss;
    }

    
    /** 
     * @param tacklesLoss
     */
    public void setTacklesLoss(float tacklesLoss) {
        this.tacklesLoss = tacklesLoss;
    }

    
    /** 
     * @return float
     */
    public float getSacks() {
        return this.sacks;
    }

    
    /** 
     * @param sacks
     */
    public void setSacks(float sacks) {
        this.sacks = sacks;
    }

    
    /** 
     * @return int
     */
    public int getInerceptionCatches() {
        return this.inerceptionCatches;
    }

    
    /** 
     * @param inerceptionCatches
     */
    public void setInerceptionCatches(int inerceptionCatches) {
        this.inerceptionCatches = inerceptionCatches;
    }

    
    /** 
     * @return int
     */
    public int getInterceptionYards() {
        return this.interceptionYards;
    }

    
    /** 
     * @param interceptionYards
     */
    public void setInterceptionYards(int interceptionYards) {
        this.interceptionYards = interceptionYards;
    }

    
    /** 
     * @return float
     */
    public float getInterceptionYPC() {
        return this.interceptionYPC;
    }

    
    /** 
     * @param interceptionYPC
     */
    public void setInterceptionYPC(float interceptionYPC) {
        this.interceptionYPC = interceptionYPC;
    }

    
    /** 
     * @return int
     */
    public int getInterceptionTouchdowns() {
        return this.interceptionTouchdowns;
    }

    
    /** 
     * @param interceptionTouchdowns
     */
    public void setInterceptionTouchdowns(int interceptionTouchdowns) {
        this.interceptionTouchdowns = interceptionTouchdowns;
    }

    
    /** 
     * @return int
     */
    public int getPassesDefended() {
        return this.passesDefended;
    }

    
    /** 
     * @param passesDefended
     */
    public void setPassesDefended(int passesDefended) {
        this.passesDefended = passesDefended;
    }

    
    /** 
     * @return int
     */
    public int getFumblesRecovered() {
        return this.fumblesRecovered;
    }

    
    /** 
     * @param fumblesRecovered
     */
    public void setFumblesRecovered(int fumblesRecovered) {
        this.fumblesRecovered = fumblesRecovered;
    }

    
    /** 
     * @return int
     */
    public int getFumbleYards() {
        return this.fumbleYards;
    }

    
    /** 
     * @param fumbleYards
     */
    public void setFumbleYards(int fumbleYards) {
        this.fumbleYards = fumbleYards;
    }

    
    /** 
     * @return int
     */
    public int getFumbleTouchdowns() {
        return this.fumbleTouchdowns;
    }

    
    /** 
     * @param fumbleTouchdowns
     */
    public void setFumbleTouchdowns(int fumbleTouchdowns) {
        this.fumbleTouchdowns = fumbleTouchdowns;
    }

    
    /** 
     * @return int
     */
    public int getFumblesForced() {
        return this.fumblesForced;
    }

    
    /** 
     * @param fumblesForced
     */
    public void setFumblesForced(int fumblesForced) {
        this.fumblesForced = fumblesForced;
    }
}