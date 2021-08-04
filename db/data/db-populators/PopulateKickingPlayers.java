import java.sql.*;
import java.util.Scanner;
import java.io.File;

/**
 * This class represents a populator for kicking/punting players in the uga-stats sql database
 * 
 * all stats were gathered from https://www.sports-reference.com/
 */
public class PopulateKickingPlayers{
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
            PreparedStatement player_stats_statement = connection.prepareStatement("insert into player_stats(player_player_id, team_year, extra_point_made, extra_point_attempts, extra_point_avg, field_goal_made, field_goal_attempts, field_goal_avg, punt_attempts, punt_yards, punt_avg) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            System.out.println("Statement object created.");

            File kicking_data = new File("kicking_data.txt");

            Scanner kicking_stats = new Scanner(kicking_data);
            int iteration = 0;
            String year = "";
            while(kicking_stats.hasNext()){
                String player_stats = kicking_stats.nextLine();
                char [] split = player_stats.toCharArray();
                String player_id = "";
                String first_name = "";
                String last_name = "";
                String extra_point_made = "";
                String extra_point_attempts = "";
                String extra_point_avg = "";
                String field_goal_made = "";
                String field_goal_attempts = "";
                String field_goal_avg = "";
                String punt_attempts = "";
                String punt_yards = "";
                String punt_avg = "";
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
                        extra_point_made = extra_point_made + s;
                    }
                    if(commaCount == 3){
                        extra_point_attempts = extra_point_attempts + s;
                    }
                    if(commaCount == 4){
                        extra_point_avg = extra_point_avg + s;
                    }
                    if(commaCount == 5){
                        field_goal_made = field_goal_made + s;
                    }
                    if(commaCount == 6){
                        field_goal_attempts = field_goal_attempts + s;
                    }
                    if(commaCount == 7){
                        field_goal_avg = field_goal_avg + s;
                    }
                    if(commaCount == 8){
                        continue;
                    }
                    if(commaCount == 9){
                        punt_attempts = punt_attempts + s;
                    }
                    if(commaCount == 10){
                        punt_yards = punt_yards + s;
                    }
                    if(commaCount == 11){
                        punt_avg = punt_avg + s;
                    }
                }
                //need to check if coach already exists before creating him
                //System.out.println("coach_id: {" + coach_id + "}");
                if(dontAdd){
                    continue;
                }

                //set all values to correct data types
                int cyear = Integer.parseInt(year);
                int cextra_point_made = (extra_point_made.equals("")) ? 0 : Integer.parseInt(extra_point_made);
                int cextra_point_attempts = (extra_point_attempts.equals("")) ? 0 : Integer.parseInt(extra_point_attempts);
                float cextra_point_avg = (extra_point_avg.equals("")) ? 0 : Float.parseFloat(extra_point_avg);
                int cfield_goal_made = (field_goal_made.equals("")) ? 0 : Integer.parseInt(field_goal_made);
                int cfield_goal_attempts = (field_goal_attempts.equals("")) ? 0 : Integer.parseInt(field_goal_attempts);
                float cfield_goal_avg = (field_goal_avg.equals("")) ? 0 : Float.parseFloat(field_goal_avg);
                int cpunt_attempts = (punt_attempts.equals("")) ? 0 : Integer.parseInt(punt_attempts);
                int cpunt_yards = (punt_yards.equals("")) ? 0 : Integer.parseInt(punt_yards);
                float cpunt_avg = (punt_avg.equals("")) ? 0 : Float.parseFloat(punt_avg);

                System.out.println("year: {" + cyear + "}");
                System.out.println("player_id: {" + player_id + "}");
                System.out.println("first_name: {" + first_name + "}");
                System.out.println("last_name: {" + last_name + "}");
                System.out.println("extra_point_made: {" + cextra_point_made + "}");
                System.out.println("extra_point_attempts: {" + cextra_point_attempts + "}");
                System.out.println("extra_point_avg: {" + cextra_point_avg + "}");
                System.out.println("field_goal_made: {" + cfield_goal_made + "}");
                System.out.println("field_goal_attempts: {" + cfield_goal_attempts + "}");
                System.out.println("field_goal_avg: {" + cfield_goal_avg + "}");
                System.out.println("punt_attempts: {" + cpunt_attempts + "}");
                System.out.println("punt_yards: {" + cpunt_yards + "}");
                System.out.println("punt_avg: {" + cpunt_avg + "}");

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
                    //PreparedStatement player_stats_statement = connection.prepareStatement("insert into player_stats(player_player_id, team_year, extra_point_made, extra_point_attempts, extra_point_avg, field_goal_made, field_goal_attempts, field_goal_avg, punt_attempts, punt_yards, punt_avg) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    player_stats_statement.setString(1, player_id);
                    player_stats_statement.setInt(2, cyear);
                    player_stats_statement.setInt(3, cextra_point_made);
                    player_stats_statement.setInt(4, cextra_point_attempts);
                    player_stats_statement.setFloat(5, cextra_point_avg);
                    player_stats_statement.setInt(6, cfield_goal_made);
                    player_stats_statement.setInt(7, cfield_goal_attempts);
                    player_stats_statement.setFloat(8, cfield_goal_avg);
                    player_stats_statement.setInt(9, cpunt_attempts);
                    player_stats_statement.setInt(10, cpunt_yards);
                    player_stats_statement.setFloat(11, cpunt_avg);
                    player_stats_statement.executeUpdate();
                    System.out.println("player_stats has sucesfully updated.");
                }
                else{
                    //update their existing stats
                    PreparedStatement player_stats_update_statement = connection.prepareStatement("update player_stats set extra_point_made = ?, extra_point_attempts = ?, extra_point_avg = ?, field_goal_made = ?, field_goal_attempts = ?, field_goal_avg = ?, punt_attempts = ?, punt_yards = ?, punt_avg = ? where team_year = ? and player_player_id = ? ");
                    player_stats_update_statement.setInt(1, cextra_point_made);
                    player_stats_update_statement.setInt(2, cextra_point_attempts);
                    player_stats_update_statement.setFloat(3, cextra_point_avg);
                    player_stats_update_statement.setInt(4, cfield_goal_made);
                    player_stats_update_statement.setInt(5, cfield_goal_attempts);
                    player_stats_update_statement.setFloat(6, cfield_goal_avg);
                    player_stats_update_statement.setInt(7, cpunt_attempts);
                    player_stats_update_statement.setInt(8, cpunt_yards);
                    player_stats_update_statement.setFloat(9, cpunt_avg);
                    player_stats_update_statement.setInt(10, cyear);
                    player_stats_update_statement.setString(11, player_id);
                    player_stats_update_statement.executeUpdate();
                    System.out.println("player_stats has sucesfully updated.");
                }

                System.out.println("==================================================");        
                iteration++;
            }
        //if connecting to the database and executing the queries fails
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}