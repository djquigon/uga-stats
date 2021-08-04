package com.techzilla.ugastats.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * Represents a team_stats object from the uga-stats database
 */
@Entity
@Table(name = "team_stats")
public class TeamStats {

    @Id
    @Column(name = "team_year", nullable = false)
    private int year;

    @Column(name = "wins",nullable = false)
    private int wins;

    @Column(name = "losses",nullable = false)
    private int losses;

    @Column(name = "ties",nullable = false)
    private int ties;

    @Column(name = "win_percentage",nullable = false)
    private float winPercentage;

    @Column(name = "simple_rating_system",nullable = false)
    private float srs;

    @Column(name = "strength_of_schedule",nullable = false)
    private float sos;

    @Column(name = "preseason_rank")
    private int preRank;

    @Column(name = "highest_rank")
    private int highRank;

    @Column(name = "postseason_rank")
    private int postRank;

    @Column(name = "bowl_appearance", length=45)
    private String bowl;

    @Column(name = "off_total_yards")
    private int offTotalYards;

    @Column(name = "def_total_yards")
    private int defTotalYards;


    
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
    public int getWins() {
        return this.wins;
    }

    
    /** 
     * @param wins
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    
    /** 
     * @return int
     */
    public int getLosses() {
        return this.losses;
    }

    
    /** 
     * @param losses
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }

    
    /** 
     * @return int
     */
    public int getTies() {
        return this.ties;
    }

    
    /** 
     * @param ties
     */
    public void setTies(int ties) {
        this.ties = ties;
    }

    
    /** 
     * @return float
     */
    public float getWinPercentage() {
        return this.winPercentage;
    }

    
    /** 
     * @param winPercentage
     */
    public void setWinPercentage(float winPercentage) {
        this.winPercentage = winPercentage;
    }

    
    /** 
     * @return float
     */
    public float getSrs() {
        return this.srs;
    }

    
    /** 
     * @param srs
     */
    public void setSrs(float srs) {
        this.srs = srs;
    }

    
    /** 
     * @return float
     */
    public float getSos() {
        return this.sos;
    }

    
    /** 
     * @param sos
     */
    public void setSos(float sos) {
        this.sos = sos;
    }

    
    /** 
     * @return int
     */
    public int getPreRank() {
        return this.preRank;
    }

    
    /** 
     * @param preRank
     */
    public void setPreRank(int preRank) {
        this.preRank = preRank;
    }

    
    /** 
     * @return int
     */
    public int getHighRank() {
        return this.highRank;
    }

    
    /** 
     * @param highRank
     */
    public void setHighRank(int highRank) {
        this.highRank = highRank;
    }

    
    /** 
     * @return int
     */
    public int getPostRank() {
        return this.postRank;
    }

    
    /** 
     * @param postRank
     */
    public void setPostRank(int postRank) {
        this.postRank = postRank;
    }

    
    /** 
     * @return String
     */
    public String getBowl() {
        return this.bowl;
    }

    
    /** 
     * @param bowl
     */
    public void setBowl(String bowl) {
        this.bowl = bowl;
    }

    
    /** 
     * @return int
     */
    public int getOffTotalYards() {
        return this.offTotalYards;
    }

    
    /** 
     * @param offTotalYards
     */
    public void setOffTotalYards(int offTotalYards) {
        this.offTotalYards = offTotalYards;
    }

    
    /** 
     * @return int
     */
    public int getDefTotalYards() {
        return this.defTotalYards;
    }

    
    /** 
     * @param defTotalYards
     */
    public void setDefTotalYards(int defTotalYards) {
        this.defTotalYards = defTotalYards;
    }

}