To setup the UGA Stats database, you'll need to do 2 things:

    1. Run the uga-stats.sql script in the client of your choice (workbench by running the script, command line by using the "source" command).
        -This will create the database and its associated tables
        -We attempted to create a java file to include in our python script that completed this, but we ran into issues regarding some of the statments used to create the database

    2. Run the uga_stats_setup.py python script
        -NOTE: You may need to install the most recently released JDK for Java SE 15 to run the java files used in this script, you'll know you need to do
        this if run into a JNI error. After this you may also need to add this to your PATH: "C:\Program Files\Java\jdk-15.0.1\bin;"
        -This will populate the database
        -This of course requires a version of python
        -When you run this file, you will be prompted a few times to enter the mysql username and password for the server you created the uga-stats database on
        -Please ensure you enter this correctly each time
        -Each time a separate part of the database will be populated with data we've collected in the .txt files in this directory
        -If the execution is not interupted by any errors, and sucessfully prints "Database setup complete", the database should now be populated
        
Once you complete these steps you can run the spring boot application in the environment of your choice. 
      
change application.properties
