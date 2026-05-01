# TypingRaceSimulator
This is my OOP project

#### dependancies:
* There are no external dependancies required
* All modules used are part of the standard java library


### Setting up files:
* When you download this project, mark the directory as the "Sources Root" so package statement are
relative to the TypingRaceSimulator folder, in intellij
* In VS code, you don't need to worry about this, but just incase, add teh root folder to the 
`java.project.sourcesPath`

### compiling files:
#### Part1:
* run the command `chcp 65001` if you are using windows, if you are using mac of linux
  don't worry, you don't have to do anything
* next run the command `javac Part1/*.java` to compile all files in current directory
* next run the command `java "-Dfile.encoding=UTF-8" Part1.TypingRace` as this is the class with the main method

#### testingUnit:
* run the command `chcp 65001` if you are using windows, if you are using mac of linux
  don't worry, yoy don't have to do anything
* next run the command `javac testingUnit/*.java` to compile all folder in current directory
* next run the command `java "-Dfile.encoding=UTF-8" testingUnit.TypistTest` as this is the class with the main method
* to test the method u want to test, below the `setup();` statement, call the method you want to test

#### Part2
* run the command `chcp 65001` if you are using windows, if you are using mac of linux
  don't worry, you don't have to do anything
* next run the command `javac Part2/*.java` to compile all files in current directory
* next run the command `java "-Dfile.encoding=UTF-8" Part2.GuiTypingRace` as this is the class with the main method

### navigating the GUI in part2:
#### first step:
* press the configure Race button
* select a passage
* Alternatively, enter your own custom passage and then press submit new passage and then select that passage or another one
* select number of typists
* select difficulty modifiers
* press submit all information

#### second step:
* Then for each typist, select the names of the typists at each seat
* note, selecting the same name is not allowed and then press display profiles
* After you have pressed this button, you cannot go back
* Enter your own customisations for each player and press submit information
* After this has been pressed, you cannot go back
* press start race button

#### last step:
* now n passages will be displayed where n is the number of typists
* For each passage, you will see a set portion of the passage higlighted with the color you selected for a given typist

**FINAL NOTE:** 
* This GUI only supports one race, 
* after this race has finished close the GUI and compile again If you want to have another race
* Also ignore the **view typists performance** button