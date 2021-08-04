import java.sql.*;
import java.util.Scanner;
import java.io.File;

/**
 * This class represents a populator for null player stats in the uga-stats sql database
 * 
 * all stats were gathered from https://www.sports-reference.com/
 */
public class PopulateNullStats {
    public static void main(String args[]){ 
        String url = "jdbc:mysql://localhost:3306/uga-stats?serverTimezone=UTC";
        try{
            //get connection
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your MySQL username:");
            String user = input.nextLine();
            System.out.println("Enter your MySQL password:");
            String password = input.nextLine();
            input.close();
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to uga-stats sucessfull.");

            //create main statement object
            Statement create_statement = connection.createStatement();

            String sql = "UPDATE player_stats " +
            "SET passing_completions = COALESCE(passing_completions, 0)," +
            "passing_attempts = COALESCE(passing_attempts, 0)," +
            "passing_percentage = COALESCE(passing_percentage, 0)," +
            "passing_yards = COALESCE(passing_yards, 0)," +
            "passing_yards_per_attempt = COALESCE(passing_yards_per_attempt, 0)," +
            "passing_touchdowns = COALESCE(passing_touchdowns, 0)," +
            "passing_interceptions = COALESCE(passing_interceptions, 0)," +
            "passing_rating = COALESCE(passing_rating, 0)," +
            "rushing_attempts = COALESCE(rushing_attempts, 0)," +
            "rushing_yards = COALESCE(rushing_yards, 0)," +
            "rushing_yards_per_attempt = COALESCE(rushing_yards_per_attempt, 0)," +
            "rushing_touchdowns = COALESCE(rushing_touchdowns, 0)," +
            "receiving_catches = COALESCE(receiving_catches, 0)," +
            "receiving_yards = COALESCE(receiving_yards, 0)," +
            "receiving_yards_per_catch = COALESCE(receiving_yards_per_catch, 0)," +
            "receiving_touchdowns = COALESCE(receiving_touchdowns, 0)," +
            "extra_point_made = COALESCE(extra_point_made, 0)," +
            "extra_point_attempts = COALESCE(extra_point_attempts, 0)," +
            "extra_point_avg = COALESCE(extra_point_avg, 0)," +
            "field_goal_made = COALESCE(field_goal_made, 0)," +
            "field_goal_attempts = COALESCE(field_goal_attempts, 0)," +
            "field_goal_avg = COALESCE(field_goal_avg, 0)," +
            "punt_attempts = COALESCE(punt_attempts, 0)," +
            "punt_yards = COALESCE(punt_yards, 0)," +
            "punt_avg = COALESCE(punt_avg, 0)," +
            "tackles_solo = COALESCE(tackles_solo, 0)," +
            "tackles_assisted = COALESCE(tackles_assisted, 0)," +
            "tackles_total = COALESCE(tackles_total, 0)," +
            "tackles_loss = COALESCE(tackles_loss, 0)," +
            "sacks = COALESCE(sacks, 0)," +
            "interception_catches = COALESCE(interception_catches, 0)," +
            "interception_yards = COALESCE(interception_yards, 0)," +
            "interception_yards_per_catch = COALESCE(interception_yards_per_catch, 0)," +
            "interception_touchdowns = COALESCE(interception_touchdowns, 0)," +
            "passes_defended = COALESCE(passes_defended, 0)," +
            "fumbles_recovered = COALESCE(fumbles_recovered, 0)," +
            "fumble_yards = COALESCE(fumble_yards, 0)," +
            "fumble_touchdowns = COALESCE(fumble_touchdowns, 0)," +
            "fumbles_forced = COALESCE(fumbles_forced, 0);";
            create_statement.executeUpdate(sql);
            System.out.println("Null stats successfully fixed.");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}