import java.sql.*;
import java.util.Scanner;
import java.io.File;

/**
 * This class represents a populator for defensive players in the uga-stats sql database
 * 
 * all stats were gathered from https://www.sports-reference.com/
 */
public class PopulateDefensivePlayers{
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
            PreparedStatement player_stats_statement = connection.prepareStatement("insert into player_stats(player_player_id, team_year, tackles_solo, tackles_assisted, tackles_total, tackles_loss, sacks, interception_catches, interception_yards, interception_yards_per_catch, interception_touchdowns, passes_defended, fumbles_recovered, fumble_yards, fumble_touchdowns, fumbles_forced) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            System.out.println("Statement object created.");

            File defensive_data = new File("defensive_data.txt");
            //populate team/head_coach table

            Scanner defensive_stats = new Scanner(defensive_data);
            int iteration = 0;
            String year = "";
            while(defensive_stats.hasNext()){
                String player_stats = defensive_stats.nextLine();
                char [] split = player_stats.toCharArray();
                String player_id = "";
                String first_name = "";
                String last_name = "";
                String tackles_solo = "";
                String tackles_assisted = "";
                String tackles_total = "";
                String tackles_loss = "";
                String sacks = "";
                String intereception_catches = "";
                String intereception_yards = "";
                String intereception_yards_per_catch = "";
                String intereception_touchdowns = "";
                String passes_defended = "";
                String fumbles_recovered = "";
                String fumble_yards = "";
                String fumble_touchdowns = "";
                String fumble_forced = "";
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
                    //Rk,Player,Solo,Ast,Tot,Loss,Sk,Int,Yds,Avg,TD,PD,FR,Yds,TD,FF
                    int player_year = Integer.parseInt(year); 
                    if(player_year > 2004){
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
                            tackles_solo = tackles_solo + s;
                        }
                        if(commaCount == 3){
                            tackles_assisted = tackles_assisted + s;
                        }
                        if(commaCount == 4){
                            tackles_total = tackles_total + s;
                        }
                        if(commaCount == 5){
                            tackles_loss = tackles_loss + s;
                        }
                        if(commaCount == 6){
                            sacks = sacks + s;
                        }
                        if(commaCount == 7){
                            intereception_catches = intereception_catches + s;
                        }
                        if(commaCount == 8){
                            intereception_yards = intereception_yards + s;
                        }
                        if(commaCount == 9){
                            intereception_yards_per_catch = intereception_yards_per_catch + s;
                        }
                        if(commaCount == 10){
                            intereception_touchdowns = intereception_touchdowns + s;
                        }
                        if(commaCount == 11){
                            passes_defended = passes_defended + s;
                        }
                        if(commaCount == 12){
                            fumbles_recovered = fumbles_recovered + s;
                        }
                        if(commaCount == 13){
                            fumble_yards = fumble_yards + s;
                        }
                        if(commaCount == 14){
                            fumble_touchdowns = fumble_touchdowns + s;
                        }
                        if(commaCount == 15){
                            fumble_forced = fumble_forced + s;
                        }
                    }
                    else{
                        //Rk,Player,Int,Yds,Avg,TD,PD,FR,Yds,TD,FF
                        if(player_year > 1999){
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
                                intereception_catches = intereception_catches + s;
                            }
                            if(commaCount == 3){
                                intereception_yards = intereception_yards + s;
                            }
                            if(commaCount == 4){
                                intereception_yards_per_catch = intereception_yards_per_catch + s;
                            }
                            if(commaCount == 5){
                                intereception_touchdowns = intereception_touchdowns + s;
                            }
                            if(commaCount == 6){
                                passes_defended = passes_defended + s;
                            }
                            if(commaCount == 7){
                                fumbles_recovered = fumbles_recovered + s;
                            }
                            if(commaCount == 8){
                                fumble_yards = fumble_yards + s;
                            }
                            if(commaCount == 9){
                                fumble_touchdowns = fumble_touchdowns + s;
                            }
                            if(commaCount == 10){
                                fumble_forced = fumble_forced + s;
                            }
                        }
                        //Rk,Player,Int,Yds,Avg,TD,PD
                        else{
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
                                intereception_catches = intereception_catches + s;
                            }
                            if(commaCount == 3){
                                intereception_yards = intereception_yards + s;
                            }
                            if(commaCount == 4){
                                intereception_yards_per_catch = intereception_yards_per_catch + s;
                            }
                            if(commaCount == 5){
                                intereception_touchdowns = intereception_touchdowns + s;
                            }
                            if(commaCount == 6){
                                passes_defended = passes_defended + s;
                            }
                        }
                    }

                }
                if(dontAdd){
                    continue;
                }
                //set all values to correct data types
                int cyear = Integer.parseInt(year);
                int ctackles_solo = (tackles_solo.equals("")) ? 0 : Integer.parseInt(tackles_solo);
                int ctackles_assisted = (tackles_assisted.equals("")) ? 0 : Integer.parseInt(tackles_assisted);
                int ctackles_total = (tackles_total.equals("")) ? 0 : Integer.parseInt(tackles_total);
                float ctackles_loss = (tackles_loss.equals("")) ? 0 : Float.parseFloat(tackles_loss);
                float csacks = (sacks.equals("")) ? 0 : Float.parseFloat(sacks);
                int cintereception_catches = (intereception_catches.equals("")) ? 0 : Integer.parseInt(intereception_catches);
                int cintereception_yards = (intereception_yards.equals("")) ? 0 : Integer.parseInt(intereception_yards);
                float cintereception_yards_per_catch = (intereception_yards_per_catch.equals("")) ? 0 : Float.parseFloat(intereception_yards_per_catch);
                int cintereception_touchdowns = (intereception_touchdowns.equals("")) ? 0 : Integer.parseInt(intereception_touchdowns);
                int cpasses_defended = (passes_defended.equals("")) ? 0 : Integer.parseInt(passes_defended);
                int cfumbles_recovered = (fumbles_recovered.equals("")) ? 0 : Integer.parseInt(fumbles_recovered);
                int cfumble_yards = (fumble_yards.equals("")) ? 0 : Integer.parseInt(fumble_yards);
                int cfumble_touchdowns = (fumble_touchdowns.equals("")) ? 0 : Integer.parseInt(fumble_touchdowns);
                int cfumble_forced = (fumble_forced.equals("")) ? 0 : Integer.parseInt(fumble_forced);


                System.out.println("year: {" + cyear + "}");
                System.out.println("player_id: {" + player_id + "}");
                System.out.println("first_name: {" + first_name + "}");
                System.out.println("last_name: {" + last_name + "}");
                System.out.println("tackles_solo: {" + ctackles_solo + "}");
                System.out.println("tackles_assisted: {" + ctackles_assisted + "}");
                System.out.println("tackles_total: {" + ctackles_total + "}");
                System.out.println("tackles_loss: {" + ctackles_loss + "}");
                System.out.println("sacks: {" + csacks + "}");
                System.out.println("intereception_catches: {" + cintereception_catches + "}");
                System.out.println("intereception_yards: {" + cintereception_yards + "}");
                System.out.println("intereception_yards_per_catch: {" + cintereception_yards_per_catch + "}");
                System.out.println("intereception_touchdowns: {" + cintereception_touchdowns + "}");
                System.out.println("passes_defended: {" + cpasses_defended + "}");
                System.out.println("fumbles_recovered: {" + cfumbles_recovered + "}");
                System.out.println("fumble_yards: {" + cfumble_yards + "}");
                System.out.println("fumble_touchdowns: {" + cfumble_touchdowns + "}");
                System.out.println("fumble_forced: {" + cfumble_forced + "}");

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

                //execute team_has_player update
                //PreparedStatement team_has_player_statement = connection.prepareStatement("insert into team_has_player(team_year, player_player_id) values (?, ?)");
                team_has_player_statement.setInt(1, cyear);
                team_has_player_statement.setString(2, player_id);
                team_has_player_statement.executeUpdate();
                System.out.println("team_has_player has sucesfully updated.");

                //execute player_stats update
                //PreparedStatement player_stats_statement = connection.prepareStatement("insert into player_stats(player_id, year, tackles_solo, tackles_assisted, tackles_total, tackles_loss, sacks, interception_catches, intereception_yards, intereception_yards_per_catch, intereception_touchdowns, passes_defended, fumbles_recovered, fumble_yards, fumble_touchdowns, fumble_forced) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                player_stats_statement.setString(1, player_id);
                player_stats_statement.setInt(2, cyear);
                player_stats_statement.setInt(3, ctackles_solo);
                player_stats_statement.setInt(4, ctackles_assisted);
                player_stats_statement.setInt(5, ctackles_total);
                player_stats_statement.setFloat(6, ctackles_loss);
                player_stats_statement.setFloat(7, csacks);
                player_stats_statement.setInt(8, cintereception_catches);
                player_stats_statement.setInt(9, cintereception_yards);
                player_stats_statement.setFloat(10, cintereception_yards_per_catch);
                player_stats_statement.setInt(11, cintereception_touchdowns);
                player_stats_statement.setInt(12, cpasses_defended);
                player_stats_statement.setInt(13, cfumbles_recovered);
                player_stats_statement.setInt(14, cfumble_yards);
                player_stats_statement.setInt(15, cfumble_touchdowns);
                player_stats_statement.setInt(16, cfumble_forced);
                player_stats_statement.executeUpdate();
                System.out.println("player_stats has sucesfully updated.");

                System.out.println("==================================================");
                iteration++;
            }
        //if connecting to the database and executing the queries fails
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}