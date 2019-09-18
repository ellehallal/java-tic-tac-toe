# Java Tic Tac Toe

- [Java Tic Tac Toe](#java-tic-tac-toe)
  - [Description](#description)
  - [Preview](#preview)
  - [Features](#features)
  - [Getting Started](#getting-started)
  - [Language](#language)
  - [Testing](#testing)
  - [Edge Cases Considered](#edge-cases-considered)
  - [Related Blog Posts](#related-blog-posts)

## Description

 A Java application, which allows the user to play Tic Tac Toe on the command line.

## Preview

- Coming soon

## Features

 - Choose to play the following games:
    - Human vs Human
    - Human vs Computer
    - Human vs Unbeatable Computer
    - Computer vs Human
    - Computer vs Computer
    - Computer vs Unbeatable Computer
    - Unbeatable Computer vs Human
    - Unbeatable Computer vs Computer
    - Unbeatable Computer vs Unbeatable Computer
- Save the game state to a database during the game
- Resume a saved game

## Getting Started

- [Install Java, version 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
- [Install Maven, version 3.6.1](https://maven.apache.org/download.cgi)

 ```
git clone https://github.com/itsellej/ruby-tic-tac-toe
cd java_tic_tac_toe
mvn package
// Note: add database url, username and password
java -DDBURL=url -DDBUSERNAME=username -DDBPASSWORD=password -jar target/java-tic-tac-toe-1.0-SNAPSHOT.jar
 ```
 
Note: to save and retrieve games, a PostgreSQL database, with a table called "saved_games" will need to exist with the following columns set to `not null`:
- id: integer (primary key)
- game_name: text
- current_player_mark: text
- current_player_type: text
- opponent_mark: text
- opponent_type: text
- squares: text[]

## Language 

 The language used to create this application is [Java, version 11](https://docs.oracle.com/en/java/), using the build automation tool, [Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html).

## Testing

 The testing library used is [JUnit, version 5.4.2](https://junit.org/junit5/). 

## Edge Cases Considered
- Cases where the user is prompted to enter input again:
  - Game type selection: if `'start'` or `'existing'` is not entered
  - Loading a saved game: if the game name entered does not exist
  - Player type selection: if `'human'`, `'computer'` or `'unbeatable'` is not entered
  - Move selection: if 1-9, or `'save'` is not entered during game play

## Related Blog Posts

- [SOLID — The Liskov Substitution Principle](https://medium.com/@ellehallal/solid-the-liskov-substitution-principle-c8bc89d4c03d)
- [Interfaces, Abstract Classes and Concrete Classes](https://medium.com/@ellehallal/interfaces-abstract-classes-and-concrete-classes-13af02ae96cf)
- [What Is The Template Method Design Pattern?](https://medium.com/@ellehallal/what-is-the-template-method-design-pattern-845e1987d08e)
