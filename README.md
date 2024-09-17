# Hangman Game

This is a console-based "Hangman" game, developed as part of a homework assignment for the [Backend Academy 2024 by Tinkoff](https://edu.tinkoff.ru/). The game is implemented in Java using a project template provided by the Academy.

## Game Description

In this game, the player must guess a word by entering letters one at a time. For each incorrect attempt, a part of the "gallows" is displayed. The game continues until the player guesses the word or the entire figure is drawn on the gallows.

## Project Structure

The project is created using Apache Maven and follows a standard Java project structure. Here are the main files and directories:

- `pom.xml` — file for managing dependencies and build configuration.
- `src/` — the source code of the game, which includes:
  - `game/` — game logic (Game.java, GameSettings.java, HangmanStages.java).
  - `ui/` — user interface for the console version (ConsoleUserInterface.java).
  - `wordprovider/` — classes for word handling (FileWordProvider.java, CluedWord.java).
  - `data/` — dictionary file (dictionary.json).
- `test/` — tests for various components of the game.

Additionally, the project contains configurations for linters and code quality tools, such as Checkstyle, PMD, and SpotBugs.

## How to Run the Game

1. **Clone the repository:**
   ```bash
   git clone https://github.com/zavik001/hangman.git

2. **Navigate to the project directory:**
   ```bash
   cd hangman

3. **Build and run the project using Maven (for Unix-based systems - Linux, macOS, WSL):**
   ```bash
   ./mvnw clean verify

4. **Build and run the project using Maven (for Windows):**
   ```bash
   mvnw.cmd clean verify
