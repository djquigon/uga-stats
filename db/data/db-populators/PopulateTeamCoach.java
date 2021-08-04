import java.sql.*;
import java.util.Scanner;
import java.io.File;

/**
 * This class represents a populator for teans and coaches in the uga-stats sql database
 * 
 * all stats were gathered from https://www.sports-reference.com/
 */
public class PopulateTeamCoach{
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
            PreparedStatement coach_statement = connection.prepareStatement("insert into head_coach(coach_id, first_name, last_name) values (?, ?, ?)");
            PreparedStatement team_statement = connection.prepareStatement("insert into team(year, head_coach_coach_id) values (?, ?)");
            PreparedStatement team_stats_statement = connection.prepareStatement("insert into team_stats(team_year, wins, losses, ties, win_percentage, simple_rating_system, strength_of_schedule, preseason_rank, highest_rank, postseason_rank, bowl_appearance) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            System.out.println("Statement objects created.");

            File team_data = new File("team_data.txt");

            //populate team/head_coach table

            Scanner team_stats = new Scanner(team_data);
            team_stats.nextLine(); //skip first line
            int iteration = 0;
            while(team_stats.hasNext()){
                String coach_id = "";
                String year = "";
                String wins = "";
                String losses = "";
                String ties = "";
                String win_percentage = "";
                String srs = "";
                String sos = "";
                String pre = "";
                String high = "";
                String post = "";
                String coach_first = "";
                String coach_last = "";
                String bowl = "";
                String team = team_stats.nextLine();
                char [] split = team.toCharArray();
                int commaCount = 0; //to split stats
                int spacesRead = 0; //spacesRead for splitting coach name
                //populate stats from team
                for(char s : split){
                    if(s == ','){
                        commaCount++;
                        continue;
                    }
                    if(commaCount == 0){
                        coach_id = coach_id + s;
                    }
                    if(commaCount == 1){
                        year = year + s;
                    }
                    if(commaCount == 2){
                        continue;
                    }
                    if(commaCount == 3){
                        wins = wins + s;
                    }
                    if(commaCount == 4){
                        losses = losses + s;
                    }
                    if(commaCount == 5){
                        ties = ties + s;
                    }
                    if(commaCount == 6){
                        win_percentage = win_percentage + s;
                    }
                    if(commaCount == 7){
                        srs = srs + s;
                    }
                    if(commaCount == 8){
                        sos = sos + s;
                    }
                    if(commaCount == 9){
                        pre = pre + s;
                    }
                    if(commaCount == 10){
                        high = high + s;
                    }
                    if(commaCount == 11){
                        post = post + s;
                    }
                    if(commaCount == 12){
                        if(s == ' '){
                            spacesRead++;
                            continue;
                        }
                        if(spacesRead == 0){
                            coach_first = coach_first + s;
                        }
                        if(spacesRead == 1){
                            coach_last = coach_last + s;
                        }
                    }
                    if(commaCount == 13){
                        bowl = bowl + s;
                    }
                    if(commaCount == 14){
                        break;
                    }             
                }

                //convert strings to correct data types
                int ccoach_id = Integer.parseInt(coach_id);
                int cyear = Integer.parseInt(year);
                int cwins = Integer.parseInt(wins);
                int closses = Integer.parseInt(losses);
                int cties = Integer.parseInt(ties);
                float cwin_percentage = Float.parseFloat(win_percentage);
                float csrs = Float.parseFloat(srs);
                float csos = Float.parseFloat(sos);
                int cpre = (pre.equals("")) ? 0 : Integer.parseInt(pre);
                int chigh = (high.equals("")) ? 0 : Integer.parseInt(high);
                int cpost = (post.equals("")) ? 0 : Integer.parseInt(post);
                /*reminder
                String coach_first = "";
                String coach_last = "";
                String bowl = "";
                */

                //print info about coach and team
                System.out.println("coach_id: {" + ccoach_id + "}");
                System.out.println("year: {" + cyear + "}");
                System.out.println("wins: {" + cwins + "}");
                System.out.println("losses: {" + closses + "}");
                System.out.println("ties: {" + cties + "}");
                System.out.println("win_percentage: {" + cwin_percentage + "}");
                System.out.println("srs: {" + csrs + "}");
                System.out.println("sos: {" + csos + "}");
                System.out.println("pre: {" + cpre + "}");
                System.out.println("high: {" + chigh + "}");
                System.out.println("post: {" + cpost + "}");
                System.out.println("coach_first: {" + coach_first + "}");
                System.out.println("coach_last: {" + coach_last + "}");
                System.out.println("bowl: {" + bowl + "}");

                //check if adding coach is necessary
                PreparedStatement coach_exists_statement = connection.prepareStatement("select coach_id from head_coach where first_name = ? and last_name = ?");
                coach_exists_statement.setString(1, coach_first);
                coach_exists_statement.setString(2, coach_last);
                ResultSet coach_exists = coach_exists_statement.executeQuery();
                Boolean add_coach = false;
                //if there are no entries make a brand new coach
                if(coach_exists.next() == false){
                    add_coach = true;
                }
                //if there are entries, get the already existing coaches' id to use for team
                else{
                    ccoach_id = coach_exists.getInt("coach_id");
                }
                //execute coach query
                if(add_coach){
                    //PreparedStatement coach_statement = connection.prepareStatement("insert into head_coach(coach_id, first_name, last_name) values (?, ?, ?)");
                    coach_statement.setInt(1, ccoach_id);
                    coach_statement.setString(2, coach_first);
                    coach_statement.setString(3, coach_last);
                    coach_statement.executeUpdate();
                    System.out.println("head_coach sucesfully updated.");
                }

                //execute team query
                //PreparedStatement team_statement = connection.prepareStatement("insert into team(year, head_coach_coach_id) values (?, ?)");
                team_statement.setInt(1, cyear);
                team_statement.setInt(2, ccoach_id);
                team_statement.executeUpdate();
                System.out.println("team sucesfully updated.");

                //execute team_stats query
                //PreparedStatement team_stats_statement = connection.prepareStatement("insert into team_stats(team_year, wins, losses, ties, win_percentage, simple_rating_system, strength_of_schedule, preseason_rank, highest_rank, postseason_rank, bowl_appearance) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                team_stats_statement.setInt(1, cyear);
                team_stats_statement.setInt(2, cwins);
                team_stats_statement.setInt(3, closses);
                team_stats_statement.setInt(4, cties);
                team_stats_statement.setFloat(5, cwin_percentage);
                team_stats_statement.setFloat(6, csrs);
                team_stats_statement.setFloat(7, csos);
                team_stats_statement.setInt(8, cpre);
                team_stats_statement.setInt(9, chigh);
                team_stats_statement.setInt(10, cpost);
                team_stats_statement.setString(11, bowl);
                team_stats_statement.executeUpdate();
                System.out.println("team_stats sucesfully updated.");

                System.out.println("======================================================");
                iteration++;
            }
            team_stats.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}