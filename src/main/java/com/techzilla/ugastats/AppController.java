package com.techzilla.ugastats;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import com.techzilla.ugastats.entities.HeadCoach;
import com.techzilla.ugastats.entities.Player;
import com.techzilla.ugastats.entities.PlayerStats;
import com.techzilla.ugastats.entities.Team;
import com.techzilla.ugastats.entities.TeamStats;
import com.techzilla.ugastats.entities.User;
import com.techzilla.ugastats.repositories.HeadCoachRepository;
import com.techzilla.ugastats.repositories.PlayerRepository;
import com.techzilla.ugastats.repositories.PlayerStatsRepository;
import com.techzilla.ugastats.repositories.TeamRepository;
import com.techzilla.ugastats.repositories.TeamStatsRepository;
import com.techzilla.ugastats.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
/**
 * This class represents a controller for the uga-stats web app.
 * It provides url mappings and models to our application.
 */
public class AppController {

    //repositories to query database
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private HeadCoachRepository headCoachRepo;

    @Autowired
    private TeamStatsRepository teamStatsRepo;

    @Autowired
    private PlayerStatsRepository playerStatsRepo;
    
    @GetMapping("")
    /**
     * Provides mapping for the home page
     * @return the html file to load
     */
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/coaches")
    /**
     * Provides mapping for the coaches page
     * @return the html file to load
     */
    public String viewCoaches(Model model){
        List<HeadCoach> coaches = headCoachRepo.findAll();
        model.addAttribute("coaches", coaches);
        model.addAttribute("teamRepo", teamRepo);

        return "coaches";
    }

    @GetMapping("/teams")
    /**
     * Provides mapping for the teams page
     * @return the html file to load
     */
    public String viewTeams(Model model){
        List<Team> teams = teamRepo.findAll();
        List<TeamStats> teamStats = teamStatsRepo.findAll();
        model.addAttribute("teams", teams);
        model.addAttribute("teamStats", teamStats);
        model.addAttribute("coachRepo", headCoachRepo);

        return "teams";
    }

    /**
     * Provides mapping for the players page
     * @return the html file to load
     */
    @GetMapping("/players")
    public String viewPlayers(Model model){
        List<Player> players = playerRepo.findAll();
        List<PlayerStats> playerStats = playerStatsRepo.findAll();
        model.addAttribute("players", players);
        model.addAttribute("playerStats", playerStats);

        return "players";
    }

    /**
     * Provides mapping for the about page
     * @return the html file to load
     */
    @GetMapping("/about")
    public String viewAbout(){
        return "about";
    }

    /**
     * Provides mapping for the registration page
     * @return the html file to load
     */
    @GetMapping("/registration")
    public String viewRegistration(Model model){
        //pass in new user
        model.addAttribute("user", new User());
        return "registration";
    }

    /**
     * Provides mapping for the login page
     * @return the html file to load
     */
    @GetMapping("/login")
    public String viewLogin(){
        return "login";
    }

    /**
     * Provides mapping for the process_registration page
     * @return the html file to load
     */
    @PostMapping("/process_registration")
    public String processRegistration(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);

        return "registration_success";
    }
}
