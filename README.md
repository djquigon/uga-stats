# uga-stats
A spring web app for UGA football statistics junkies. Users can create accounts to view and save their favorite players and stats.

## UGASTATS SETUP:

NOTE: There are visual instructions similar to this included in the demo video if any of this is unclear
    
* **Step 1: Creating and Populating the uga-stats database**

  * To create and popuate the uga-stats database, first navigate to the uga-stats/db/data directory
  * View the README.txt file in this directory for the rest of the instructions regarding the database

* **Step 2: Setting up the uga-stats spring boot application**
  * Open the term_project_techzilla directory in the spring IDE of your choice (eclipse extension, vscode extension, etc)
  * This IDE should recognize the uga-stats app in the spring boot dashboard
  * IMPORTANT: Before running the app, please navigate to the uga-stats/src/main/resources and open the application.properties file and change the username and password properties to yours for your MySQL server.

* **Step 3: Run the uga-stats spring boot application**
  * Click the start button in the spring boot dashboard contained in your IDE
  * Your debug console should state that the UGAStatsApplication started
  * Open up the browser of your choice and navigate to localhost:8080
  * You should be able to see our site!

## Database Schema:
![Database Schema](https://github.com/djquigon/uga-stats/blob/main/assets/ERDiagram.png?raw=true)
