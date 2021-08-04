import os  
  
# Commands to execute
compile_team_coach = 'javac .\db-populators\PopulateTeamCoach.java'
run_team_coach = 'java -cp .;db-populators;mysql-connector-java-8.0.21.jar PopulateTeamCoach'
compile_defense = 'javac .\db-populators\PopulateDefensivePlayers.java'
run_defense = 'java -cp .;db-populators;mysql-connector-java-8.0.21.jar PopulateDefensivePlayers'
compile_kicking = 'javac .\db-populators\PopulateKickingPlayers.java'
run_kicking = 'java -cp .;db-populators;mysql-connector-java-8.0.21.jar PopulateKickingPlayers'
compile_passing = 'javac .\db-populators\PopulatePassingPlayers.java'
run_passing = 'java -cp .;db-populators;mysql-connector-java-8.0.21.jar PopulatePassingPlayers'
compile_rush_receive = 'javac .\db-populators\PopulateRushingReceivingPlayers.java'
run_rush_receive = 'java -cp .;db-populators;mysql-connector-java-8.0.21.jar PopulateRushingReceivingPlayers'
compile_positions = 'javac .\db-populators\PopulatePositions.java'
run_positions = 'java -cp .;db-populators;mysql-connector-java-8.0.21.jar PopulatePositions'
compile_null = 'javac .\db-populators\PopulateNullStats.java'
run_null = 'java -cp .;db-populators;mysql-connector-java-8.0.21.jar PopulateNullStats'
  
# Using os.system() method 
os.system(compile_team_coach)
os.system(run_team_coach)
os.system(compile_defense)
os.system(run_defense)
os.system(compile_kicking)
os.system(run_kicking)
os.system(compile_passing)
os.system(run_passing)
os.system(compile_rush_receive)
os.system(run_rush_receive)
os.system(compile_positions)
os.system(run_positions)
os.system(compile_null)
os.system(run_null)
print("Database setup complete. You should now be able to run the UGA Stats Spring Boot application.") 