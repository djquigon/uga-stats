SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema uga-stats
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema uga-stats
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `uga-stats` DEFAULT CHARACTER SET utf8 ;
USE `uga-stats` ;

-- -----------------------------------------------------
-- Table `uga-stats`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uga-stats`.`player` (
  `player_id` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `unit` SET('Offense', 'Defense', 'Special Teams') NULL,
  PRIMARY KEY (`player_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uga-stats`.`head_coach`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uga-stats`.`head_coach` (
  `coach_id` INT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`coach_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uga-stats`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uga-stats`.`team` (
  `year` INT NOT NULL,
  `head_coach_coach_id` INT NOT NULL,
  PRIMARY KEY (`year`),
  INDEX `fk_team_head_coach_idx` (`head_coach_coach_id` ASC) VISIBLE,
  CONSTRAINT `fk_team_head_coach`
    FOREIGN KEY (`head_coach_coach_id`)
    REFERENCES `uga-stats`.`head_coach` (`coach_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uga-stats`.`player_stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uga-stats`.`player_stats` (
  `player_player_id` VARCHAR(45) NOT NULL,
  `team_year` INT NOT NULL,
  `passing_completions` INT NULL,
  `passing_attempts` INT NULL,
  `passing_percentage` FLOAT NULL,
  `passing_yards` INT NULL,
  `passing_yards_per_attempt` FLOAT NULL,
  `passing_touchdowns` INT NULL,
  `passing_interceptions` INT NULL,
  `passing_rating` FLOAT NULL,
  `rushing_attempts` INT NULL,
  `rushing_yards` INT NULL,
  `rushing_yards_per_attempt` FLOAT NULL,
  `rushing_touchdowns` INT NULL,
  `receiving_catches` INT NULL,
  `receiving_yards` INT NULL,
  `receiving_yards_per_catch` FLOAT NULL,
  `receiving_touchdowns` INT NULL,
  `extra_point_made` INT NULL,
  `extra_point_attempts` INT NULL,
  `extra_point_avg` FLOAT NULL,
  `field_goal_made` INT NULL,
  `field_goal_attempts` INT NULL,
  `field_goal_avg` FLOAT NULL,
  `punt_attempts` INT NULL,
  `punt_yards` INT NULL,
  `punt_avg` FLOAT NULL,
  `tackles_solo` INT NULL,
  `tackles_assisted` INT NULL,
  `tackles_total` INT NULL,
  `tackles_loss` FLOAT NULL,
  `sacks` FLOAT NULL,
  `interception_catches` INT NULL,
  `interception_yards` INT NULL,
  `interception_yards_per_catch` FLOAT NULL,
  `interception_touchdowns` INT NULL,
  `passes_defended` INT NULL,
  `fumbles_recovered` INT NULL,
  `fumble_yards` INT NULL,
  `fumble_touchdowns` INT NULL,
  `fumbles_forced` INT NULL,
  PRIMARY KEY (`player_player_id`, `team_year`),
  INDEX `fk_player_stats_team1_idx` (`team_year` ASC) VISIBLE,
  CONSTRAINT `fk_player_stats_player1`
    FOREIGN KEY (`player_player_id`)
    REFERENCES `uga-stats`.`player` (`player_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player_stats_team1`
    FOREIGN KEY (`team_year`)
    REFERENCES `uga-stats`.`team` (`year`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uga-stats`.`team_stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uga-stats`.`team_stats` (
  `team_year` INT NOT NULL,
  `wins` INT NOT NULL,
  `losses` INT NOT NULL,
  `ties` INT NOT NULL,
  `win_percentage` FLOAT NOT NULL,
  `simple_rating_system` FLOAT NOT NULL,
  `strength_of_schedule` FLOAT NOT NULL,
  `preseason_rank` INT NULL,
  `highest_rank` INT NULL,
  `postseason_rank` INT NULL,
  `bowl_appearance` VARCHAR(45) NULL,
  `off_total_yards` INT NULL,
  `def_total_yards` INT NULL,
  INDEX `fk_team_stats_team1_idx` (`team_year` ASC) VISIBLE,
  PRIMARY KEY (`team_year`),
  CONSTRAINT `fk_team_stats_team1`
    FOREIGN KEY (`team_year`)
    REFERENCES `uga-stats`.`team` (`year`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uga-stats`.`team_has_player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uga-stats`.`team_has_player` (
  `team_year` INT NOT NULL,
  `player_player_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`team_year`, `player_player_id`),
  INDEX `fk_team_has_player_player1_idx` (`player_player_id` ASC) VISIBLE,
  INDEX `fk_team_has_player_team1_idx` (`team_year` ASC) VISIBLE,
  CONSTRAINT `fk_team_has_player_team1`
    FOREIGN KEY (`team_year`)
    REFERENCES `uga-stats`.`team` (`year`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_team_has_player_player1`
    FOREIGN KEY (`player_player_id`)
    REFERENCES `uga-stats`.`player` (`player_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `uga-stats`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uga-stats`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
