import java.sql.*;
import java.util.Scanner;
import java.io.File;

/**
 * This class represents a populator for  player positions in the uga-stats sql database
 * 
 * all stats were gathered from https://www.sports-reference.com/
 */
public class PopulatePositions {
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
            PreparedStatement get_players_statement = connection.prepareStatement("select * from player_stats");

            //for if an entry has null (null + ? = null)
            PreparedStatement init_off_total_statement = connection.prepareStatement("update team_stats set off_total_yards = 0 where team_year > 0");
            PreparedStatement init_def_total_statement = connection.prepareStatement("update team_stats set def_total_yards = 0 where team_year > 0");
            init_off_total_statement.executeUpdate();
            init_def_total_statement.executeUpdate();

            PreparedStatement position_statement = connection.prepareStatement("update player set unit = CONCAT_WS(',', unit, ?) where player_id = ?");
            PreparedStatement off_total_statement = connection.prepareStatement("update team_stats set off_total_yards = off_total_yards + ? where team_year = ?");
            PreparedStatement def_total_statement = connection.prepareStatement("update team_stats set def_total_yards = def_total_yards + ? where team_year = ?");

            ResultSet players = get_players_statement.executeQuery();
            while(players.next()){
                
                //set player unit
                String player_id = players.getString("player_player_id");
                if(players.getInt("passing_completions") > 0 ||  players.getInt("passing_attempts") > 0 || players.getFloat("passing_percentage") > 0 ||  
                players.getInt("passing_yards") > 0 ||  players.getFloat("passing_yards_per_attempt") > 0 ||  players.getInt("passing_touchdowns") > 0 ||  
                players.getInt("passing_interceptions") > 0 || players.getFloat("passing_rating") > 0 || players.getInt("rushing_attempts") > 0 || 
                players.getInt("rushing_yards") > 0 || players.getFloat("rushing_yards_per_attempt") > 0 || players.getInt("rushing_touchdowns") > 0 || players.getInt("receiving_catches") > 0 || players.getInt("receiving_yards") > 0 || players.getFloat("receiving_yards_per_catch") > 0 || players.getInt("receiving_touchdowns") > 0){
                    position_statement.setString(1, "Offense");
                    position_statement.setString(2, player_id);
                    position_statement.executeUpdate();
                }
                if(players.getInt("tackles_solo") > 0 ||  players.getInt("tackles_assisted") > 0 || players.getInt("tackles_total") > 0 ||  
                players.getFloat("tackles_loss") > 0 ||  players.getFloat("sacks") > 0 ||  players.getInt("interception_catches") > 0 ||  
                players.getInt("interception_yards") > 0 || players.getFloat("interception_yards_per_catch") > 0 || players.getInt("interception_touchdowns") > 0 || 
                players.getInt("passes_defended") > 0 || players.getInt("fumbles_recovered") > 0 || players.getInt("fumble_yards") > 0 || players.getInt("fumble_touchdowns") > 0 || players.getInt("fumbles_forced") > 0){
                    position_statement.setString(1, "Defense");
                    position_statement.setString(2, player_id);
                    position_statement.executeUpdate();
                }
                if(players.getInt("extra_point_made") > 0 ||  players.getInt("extra_point_attempts") > 0 || players.getFloat("extra_point_avg") > 0 ||  
                players.getInt("field_goal_made") > 0 ||  players.getInt("field_goal_attempts") > 0 ||  players.getFloat("field_goal_avg") > 0 ||  
                players.getInt("punt_attempts") > 0 || players.getInt("punt_yards") > 0 || players.getFloat("punt_avg") > 0){
                    position_statement.setString(1, "Special Teams");
                    position_statement.setString(2, player_id);
                    position_statement.executeUpdate();
                }
                System.out.println("Player unit updated sucessfully.");


                //set off_total and def_total
                int team_year = players.getInt("team_year");

                int passing_yards = players.getInt("passing_yards");
                int rushing_yards = players.getInt("rushing_yards");
                int off_total_yards = passing_yards + rushing_yards;
                off_total_statement.setInt(1, off_total_yards);
                off_total_statement.setInt(2, team_year);
                off_total_statement.executeUpdate();
                System.out.println("Offensive total for " + team_year + " updated sucessfully.");

                int interception_yards = players.getInt("interception_yards");
                int fumble_yards = players.getInt("fumble_yards");
                int def_total_yards = interception_yards + fumble_yards;
                def_total_statement.setInt(1, def_total_yards);
                def_total_statement.setInt(2, team_year);
                def_total_statement.executeUpdate();
                System.out.println("Defensive total for " + team_year + " updated sucessfully.");

            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}


