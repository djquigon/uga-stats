import java.sql.*;
import java.util.Scanner;
import java.io.File;

/**
 * This class represents a populator for passing players in the uga-stats sql database
 * 
 * all stats were gathered from https://www.sports-reference.com/
 */
public class PopulatePassingPlayers{
    public static void main(String args[]){
        String url = "jdbc:mysql://localhost:3306/uga-stats?serverTimezone=UTC";
        try{
            //get connection
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
            PreparedStatement player_stats_statement = connection.prepareStatement("insert into player_stats(player_player_id, team_year, passing_completions, passing_attempts, passing_percentage, passing_yards, passing_yards_per_attempt, passing_touchdowns, passing_interceptions, passing_rating) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            System.out.println("Statement object created.");

            File passing_data = new File("passing_data.txt");
            /*
            File pass_stats = new File("passing_data.txt");
            File rush_receive_stats = new File("rush_receive_data.txt");
            File kicking_data = new File("kicking_data.txt");
            */
            //populate team/head_coach table

            Scanner passing_stats = new Scanner(passing_data);
            int iteration = 0;
            String year = "";
            while(passing_stats.hasNext()){
                String player_stats = passing_stats.nextLine();
                char [] split = player_stats.toCharArray();
                String player_id = "";
                String first_name = "";
                String last_name = "";
                String passing_completions = "";
                String passing_attempts = "";
                String passing_percentage = "";
                String passing_yards = "";
                String passing_yards_per_attempt = "";
                String passing_touchdowns = "";
                String passing_interceptions = "";
                String passing_rating = "";
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
                        passing_completions = passing_completions + s;
                    }
                    if(commaCount == 3){
                        passing_attempts = passing_attempts + s;
                    }
                    if(commaCount == 4){
                        passing_percentage= passing_percentage + s;
                    }
                    if(commaCount == 5){
                        passing_yards = passing_yards + s;
                    }
                    if(commaCount == 6){
                        passing_yards_per_attempt = passing_yards_per_attempt+ s;
                    }
                    if(commaCount == 7){
                        continue;
                    }
                    if(commaCount == 8){
                        passing_touchdowns = passing_touchdowns + s;
                    }
                    if(commaCount == 9){
                        passing_interceptions = passing_interceptions + s;
                    }
                    if(commaCount == 10){
                        passing_rating = passing_rating + s;
                    }
                }
                //need to check if coach already exists before creating him
                //System.out.println("coach_id: {" + coach_id + "}");
                if(dontAdd){
                    continue;
                }

                //set all values to correct data type
                int cyear = Integer.parseInt(year);
                int cpassing_completions = (passing_completions.equals("")) ? 0 : Integer.parseInt(passing_completions);
                int cpassing_attempts = (passing_attempts.equals("")) ? 0 : Integer.parseInt(passing_attempts);
                float cpassing_percentage = (passing_percentage.equals("")) ? 0 : Float.parseFloat(passing_percentage);
                int cpassing_yards = (passing_yards.equals("")) ? 0 : Integer.parseInt(passing_yards);
                float cpassing_yards_per_attempt = (passing_yards_per_attempt.equals("")) ? 0 : Float.parseFloat(passing_yards_per_attempt);
                int cpassing_touchdowns = (passing_touchdowns.equals("")) ? 0 : Integer.parseInt(passing_touchdowns);
                int cpassing_interceptions = (passing_interceptions.equals("")) ? 0 : Integer.parseInt(passing_interceptions);
                float cpassing_rating = (passing_rating.equals("")) ? 0 : Float.parseFloat(passing_rating);

                System.out.println("year: {" + cyear + "}");
                System.out.println("player_id: {" + player_id + "}");
                System.out.println("first_name: {" + first_name + "}");
                System.out.println("last_name: {" + last_name + "}");
                System.out.println("passing_completions: {" + cpassing_completions + "}");
                System.out.println("passing_attempts: {" + cpassing_attempts + "}");
                System.out.println("passing_percentage: {" + cpassing_percentage + "}");
                System.out.println("passing_yards: {" + cpassing_yards + "}");
                System.out.println("passing_yards_per_attempt: {" + cpassing_yards_per_attempt + "}");
                System.out.println("passing_touchdowns: {" + cpassing_touchdowns + "}");
                System.out.println("passing_interceptions: {" + cpassing_interceptions + "}");
                System.out.println("passing_rating: {" + cpassing_rating + "}");

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
                //execute coach query
                if(has_stats == false){
                    //execute player_stats update
                    //PreparedStatement player_stats_statement = connection.prepareStatement("insert into player_stats(player_player_id, team_year, passing_completions, passing_attempts, passing_percentage, passing_yards, passing_yards_per_attempt, passing_touchdowns, passing_interceptions, passing_rating) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    player_stats_statement.setString(1, player_id);
                    player_stats_statement.setInt(2, cyear);
                    player_stats_statement.setInt(3, cpassing_completions);
                    player_stats_statement.setInt(4, cpassing_attempts);
                    player_stats_statement.setFloat(5, cpassing_percentage);
                    player_stats_statement.setInt(6, cpassing_yards);
                    player_stats_statement.setFloat(7, cpassing_yards_per_attempt);
                    player_stats_statement.setInt(8, cpassing_touchdowns);
                    player_stats_statement.setInt(9, cpassing_interceptions);
                    player_stats_statement.setFloat(10, cpassing_rating);
                    player_stats_statement.executeUpdate();
                    System.out.println("player_stats has sucesfully updated.");
                }
                else{
                    //update their existing stats
                    PreparedStatement player_stats_update_statement = connection.prepareStatement("update player_stats set passing_completions = ?, passing_attempts = ?, passing_percentage = ?, passing_yards = ?, passing_yards_per_attempt = ?, passing_touchdowns = ?, passing_interceptions = ?, passing_rating = ? where team_year = ? and player_player_id = ? ");
                    player_stats_update_statement.setInt(1, cpassing_completions);
                    player_stats_update_statement.setInt(2, cpassing_attempts);
                    player_stats_update_statement.setFloat(3, cpassing_percentage);
                    player_stats_update_statement.setInt(4, cpassing_yards);
                    player_stats_update_statement.setFloat(5, cpassing_yards_per_attempt);
                    player_stats_update_statement.setInt(6, cpassing_touchdowns);
                    player_stats_update_statement.setInt(7, cpassing_interceptions);
                    player_stats_update_statement.setFloat(8, cpassing_rating);
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