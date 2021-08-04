import java.sql.*;
import java.util.Scanner;
import java.io.File;

/**
 * This class represents a populator for rushing and receiving players in the uga-stats sql database
 * 
 * all stats were gathered from https://www.sports-reference.com/
 */
public class PopulateRushingReceivingPlayers{
    public static void main(String args[]){
        String url = "jdbc:mysql://localhost:3306/uga-stats?serverTimezone=UTC";
        try{
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your MySQL username:");
            String user = input.nextLine();
            System.out.println("Enter your MySQL password:");
            String password = input.nextLine(); // need to get this depending on how its graded
            input.close();
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to uga-stats sucessfull.");

            //create main statement object
            PreparedStatement player_statement = connection.prepareStatement("insert into player(player_id, first_name, last_name) values (?, ?, ?)");
            PreparedStatement team_has_player_statement = connection.prepareStatement("insert into team_has_player(team_year, player_player_id) values (?, ?)");
            PreparedStatement player_stats_statement = connection.prepareStatement("insert into player_stats(player_player_id, team_year, rushing_attempts, rushing_yards, rushing_yards_per_attempt, rushing_touchdowns, receiving_catches, receiving_yards, receiving_yards_per_catch, receiving_touchdowns) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            System.out.println("Statement object created.");

            File rush_receive_data = new File("rush_receive_data.txt");
            /*
            File pass_stats = new File("passing_data.txt");
            File rush_receive_stats = new File("rush_receive_data.txt");
            File kicking_data = new File("kicking_data.txt");
            */
            //populate team/head_coach table

            Scanner rush_receive_stats = new Scanner(rush_receive_data);
            int iteration = 0;
            String year = "";
            while(rush_receive_stats.hasNext()){
                String player_stats = rush_receive_stats.nextLine();
                char [] split = player_stats.toCharArray();
                String player_id = "";
                String first_name = "";
                String last_name = "";
                String rushing_attempts = "";
                String rushing_yards = "";
                String rushing_yards_per_attempt = "";
                String rushing_touchdowns = "";
                String receiving_catches = "";
                String receiving_yards = "";
                String receiving_yards_per_catch = "";
                String receiving_touchdowns = "";
                int commaCount = 0; //to split stats
                boolean spaceRead = false; //spaceRead for splitting player name
                boolean slashRead = false;
                boolean dontAdd = false;
                //populate stats from team
                for(char s : split){
                    //ignore separator lines
                    if(s == '='){
                        dontAdd = true;
                        break;
                    }
                    //ignore lines with stat headers
                    if(split[0] == 'R'){
                        dontAdd = true;
                        break;
                    }
                    //count commas
                    if(s == ','){
                        commaCount++;
                        continue;
                    }
                    //get year
                    if(split.length == 4 || split.length == 5){
                        dontAdd = true;
                        year = new String(split);
                        break;
                    }
                    if(commaCount == 0){
                        continue;
                    }
                    //player name and id
                    if(commaCount == 1){
                        if(s == ' '){
                            spaceRead = true;
                            continue;
                        }
                        if(s == '\\'){
                            slashRead = true;
                            continue;
                        }
                        if(slashRead){
                            player_id = player_id + s;
                            continue; 
                        }
                        if(spaceRead){
                            last_name = last_name + s;
                        }
                        else{
                            first_name = first_name + s;
                        }
                    }
                    if(commaCount == 2){
                        rushing_attempts = rushing_attempts + s;
                    }
                    if(commaCount == 3){
                        rushing_yards = rushing_yards + s;
                    }
                    if(commaCount == 4){
                        rushing_yards_per_attempt = rushing_yards_per_attempt + s;
                    }
                    if(commaCount == 5){
                        rushing_touchdowns = rushing_touchdowns + s;
                    }
                    if(commaCount == 6){
                        receiving_catches = receiving_catches + s;
                    }
                    if(commaCount == 7){
                        receiving_yards = receiving_yards + s;
                    }
                    if(commaCount == 8){
                        receiving_yards_per_catch = receiving_yards_per_catch + s;
                    }
                    if(commaCount == 9){
                        receiving_touchdowns = receiving_touchdowns + s;
                    }
                    if(commaCount >= 10){
                        break;
                    }
            }
                //need to check if coach already exists before creating him
                //System.out.println("coach_id: {" + coach_id + "}");
                if(dontAdd){
                    continue;
                }

                int cyear = Integer.parseInt(year);
                int crushing_attempts = (rushing_attempts.equals("")) ? 0 : Integer.parseInt(rushing_attempts);
                int crushing_yards = (rushing_yards.equals("")) ? 0 : Integer.parseInt(rushing_yards);
                float crushing_yards_per_attempt = (rushing_yards_per_attempt.equals("")) ? 0 : Float.parseFloat(rushing_yards_per_attempt);
                int crushing_touchdowns = (rushing_touchdowns.equals("")) ? 0 : Integer.parseInt(rushing_touchdowns);
                int creceiving_catches = (receiving_catches.equals("")) ? 0 : Integer.parseInt(receiving_catches);
                int creceiving_yards = (receiving_yards.equals("")) ? 0 : Integer.parseInt(receiving_yards);
                float creceiving_yards_per_catch = (receiving_yards_per_catch.equals("")) ? 0 : Float.parseFloat(receiving_yards_per_catch);
                int creceiving_touchdowns = (receiving_touchdowns.equals("")) ? 0 : Integer.parseInt(receiving_touchdowns);

                System.out.println("year: {" + year + "}");
                System.out.println("player_id: {" + player_id + "}");
                System.out.println("first_name: {" + first_name + "}");
                System.out.println("last_name: {" + last_name + "}");
                System.out.println("rushing_attempts: {" + rushing_attempts + "}");
                System.out.println("rushing_yards: {" + rushing_yards + "}");
                System.out.println("rushing_yards_per_attempt: {" + rushing_yards_per_attempt + "}");
                System.out.println("rushing_touchdowns: {" + rushing_touchdowns + "}");
                System.out.println("receiving_catches: {" + receiving_catches + "}");
                System.out.println("receiving_yards: {" + receiving_yards + "}");
                System.out.println("receiving_yards_per_catch: {" + receiving_yards_per_catch + "}");
                System.out.println("receiving_touchdowns: {" + receiving_touchdowns + "}");

                
                //check if player exists
                PreparedStatement player_exists_statement = connection.prepareStatement("select * from player where player_id = ?");
                player_exists_statement.setString(1, player_id);
                ResultSet player_exists = player_exists_statement.executeQuery();
                Boolean add_player = false;
                //if there are no entries make a brand new player
                if(player_exists.next() == false){
                    add_player = true;
                }
                //execute coach query
                if(add_player){
                    //PreparedStatement player_statement = connection.prepareStatement("insert into player(player_id, first_name, last_name) values (?, ?, ?)");
                    player_statement.setString(1, player_id);
                    player_statement.setString(2, first_name);
                    player_statement.setString(3, last_name);
                    player_statement.executeUpdate();
                    System.out.println("player has sucesfully updated.");
                }

                //find if player needs to be added to team
                PreparedStatement player_on_team_statement = connection.prepareStatement("select * from team_has_player where team_year = ? and player_player_id = ?");
                player_on_team_statement.setInt(1, cyear);
                player_on_team_statement.setString(2, player_id);
                ResultSet player_on_team = player_on_team_statement.executeQuery();
                Boolean add_to_team = false;
                //if there are no entries make a brand new player
                if(player_on_team.next() == false){
                    add_to_team = true;
                }
                //execute coach query
                if(add_to_team){
                    //execute team_has_player update
                    //PreparedStatement team_has_player_statement = connection.prepareStatement("insert into team_has_player(team_year, player_player_id) values (?, ?)");
                    team_has_player_statement.setInt(1, cyear);
                    team_has_player_statement.setString(2, player_id);
                    team_has_player_statement.executeUpdate();
                    System.out.println("team_has_player has sucesfully updated.");
                }

                //find if player needs to be added to team
                PreparedStatement player_has_stats_statement = connection.prepareStatement("select * from player_stats where team_year = ? and player_player_id = ?");
                player_has_stats_statement.setInt(1, cyear);
                player_has_stats_statement.setString(2, player_id);
                ResultSet player_has_stats = player_has_stats_statement.executeQuery();
                Boolean has_stats = true;
                //if there are no entries make a brand new player_stats
                if(player_has_stats.next() == false){
                    has_stats = false;
                }
                
                if(has_stats == false){
                    //execute player_stats update
                    //PreparedStatement player_stats_statement = connection.prepareStatement("insert into player_stats(player_player_id, team_year, rushing_attempts, rushing_yards, rushing_yards_per_attempt, rushing_touchdowns, receiving_catches, receiving_yards, receiving_yards_per_catch, receiving_touchdowns) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    player_stats_statement.setString(1, player_id);
                    player_stats_statement.setInt(2, cyear);
                    player_stats_statement.setInt(3, crushing_attempts);
                    player_stats_statement.setInt(4, crushing_yards);
                    player_stats_statement.setFloat(5, crushing_yards_per_attempt);
                    player_stats_statement.setInt(6, crushing_touchdowns);
                    player_stats_statement.setInt(7, creceiving_catches);
                    player_stats_statement.setInt(8, creceiving_yards);
                    player_stats_statement.setFloat(9, creceiving_yards_per_catch);
                    player_stats_statement.setInt(10, creceiving_touchdowns);
                    player_stats_statement.executeUpdate();
                    System.out.println("player_stats has sucesfully updated.");
                }
                else{
                    //update their existing stats
                    PreparedStatement player_stats_update_statement = connection.prepareStatement("update player_stats set rushing_attempts = ?, rushing_yards = ?, rushing_yards_per_attempt = ?, rushing_touchdowns = ?, receiving_catches = ?, receiving_yards = ?, receiving_yards_per_catch = ?, receiving_touchdowns = ? where team_year = ? and player_player_id = ? ");
                    player_stats_update_statement.setInt(1, crushing_attempts);
                    player_stats_update_statement.setInt(2, crushing_yards);
                    player_stats_update_statement.setFloat(3, crushing_yards_per_attempt);
                    player_stats_update_statement.setInt(4, crushing_touchdowns);
                    player_stats_update_statement.setInt(5, creceiving_catches);
                    player_stats_update_statement.setInt(6, creceiving_yards);
                    player_stats_update_statement.setFloat(7, creceiving_yards_per_catch);
                    player_stats_update_statement.setInt(8, creceiving_touchdowns);
                    player_stats_update_statement.setInt(9, cyear);
                    player_stats_update_statement.setString(10, player_id);
                    player_stats_update_statement.executeUpdate();
                    System.out.println("player_stats has sucesfully updated.");
                }

                System.out.println("=================================================================");  
                iteration++;
            }
        //if connecting to the database and executing the queries fails
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}