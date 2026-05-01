# TypingRaceSimulator
This is my OOP project

#### dependancies:
* There are no external dependancies required
* All modules used are part of the standard java library

### Just some quick reminders for myself:
* I am free to use JUnit 5 if you wish. 
If I do, make sure it is listed as a dependency 
in your README with clear instructions 
on how to set it up, so that your tests can be compiled 
and run from the command line without requiring a specific IDE.
* Since I have used package statements in my code,
I need to make sure this Read me file includes precise
instructions on how to compile and run my code correctly given 
my package structure so that processor can compile and run code
without difficulties
* The zip folder came with a reade me file -> use that aswell
* get contents from that read me file into yours

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



### compilation of files:
* `chcp 65001` - windows
* `javac testingUnit/TypistTest.java`
* `java "-Dfile.encoding=UTF-8" testingUnit.TypistTest`