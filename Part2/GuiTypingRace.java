package Part2;
//This will host the GUI for my typing race
//

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*; // will need this module for our GUI

//The following will contain the classes used to perform the actions when the buttons are clicked
//lets start off with the class used for the ConfigureRace button
//
class ConfigureRace implements ActionListener
{
    private JFrame parentFrame;

    public ConfigureRace(JFrame parentFrame_) {
        this.parentFrame = parentFrame_;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //accessing the components of the parentFrame
        //The parent Frame has two components, the second is the mainPanel
        Component mainPanel = this.parentFrame.getContentPane().getComponent(1);
        this.mainPanel = (JPanel) mainPanel;
    }

        //configureRace():
        //Flow of configureRace:
        //display Frame -> This is another method
        //grab all information -> (event method) we probably do it by adding event listener to button which does all the
        //      Passage selected: -> then set it
        //      number of typists -> return value
        //      difficulty modifiers -> we will represent as a record:
        //                              fieldOne = tells us if its on/off
        //                              fieldTwo = value to adjust one of the slideBack or progress step by or accuracy
        // each difficulty modifier will be set as a field
        //return number of typists they want
    }
}

//record to hold information about a passage such as its length and contents
record Passage(String content, int length) {};

public class GuiTypingRace
{
    private static ArrayList<Passage> passages;   //This will store information about all passages

    private static GuiTypist [] guiTypists;
    private static JFrame frame; //this is the frame at that makes up the GUi
    private static JPanel mainPanel; //This is the panel that will be swicthed out
    // Accuracy thresholds for mistype and burnout events
    // (Ty tuned these values "by feel". They may need adjustment.)
    private static final double MISTYPE_BASE_CHANCE = 0.3;
    private static final int    SLIDE_BACK_AMOUNT   = 1; //might need to change to 2 since it could be halved
    private static final int    BURNOUT_DURATION     = 3;

    //following contains modes that Can be applied to everyone
    //
    private boolean autoCorrectOn; // used to track weather auto-correct is on or off
    private boolean caffeineMode; // used to track weather caffeineMode is on or off
    private boolean NightShift; //used to keep track of weather Night shift is on or off


    public static void main (String[] args) {

        //we add the 6 typists to the array
        initialiseTypists();
        displayFrame();

//        final int numberOfRaces = inputNumberRaces();
//        for (int i = 1; i <= numberOfRaces; ++i) {
//            int newPassageLength = TypingRace.generateRandomPassageLength();
//            setPassageLength(newPassageLength);
//            //ingore two lines of code above
//            //configureRace(); -> returns number of typists
//            //customiseTypists(numberTypists);
//            startRace();
//        }
    }

    /**
     * Seats a typist at the given seat number (1, 2, or 3).
     *
     * @param theTypist  the typist to seat
     * @param seatNumber the seat to place them in (1–3)
     */
    
//    public static void addTypist(Typist theTypist, int seatNumber)
//    {
//        if (seatNumber == 1)
//        {
//            seat1Typist = theTypist;
//        }
//        else if (seatNumber == 2)
//        {
//            seat2Typist = theTypist;
//        }
//        else if (seatNumber == 3)
//        {
//            seat3Typist = theTypist;
//        }
//        else
//        {
//            System.out.println("Cannot seat typist at seat " + seatNumber + " — there is no such seat.");
//        }
//    }

    /**
     * Starts the typing race.
     * All typists are reset to the beginning, then the simulation runs
     * turn by turn until one typist completes the full passage.
     *
     * Note from Ty: "I didn't bother printing the winner at the end,
     * you can probably figure that out yourself."
     */
//    public static void startRace()
//    {
//        boolean finished = false;
//
//        // Reset all typists to the start of the passage
//        // (Ty was in a hurry here)
//        seat1Typist.resetToStart();
//        seat2Typist.resetToStart();
//        seat3Typist.resetToStart();
//        while (!finished)
//        {
//            // Advance each typist by one turn
//            advanceTypist(seat1Typist);
//            advanceTypist(seat2Typist);
//            advanceTypist(seat3Typist);
//
//            // Print the current state of the race
//            printRace();
//
//            // Check if any typist has finished the passage
//            if ( raceFinishedBy(seat1Typist) || raceFinishedBy(seat2Typist) || raceFinishedBy(seat3Typist) )
//            {
//                finished = true;
//            }
//
//            // Wait 200ms between turns so the animation is visible
//            try {
//                TimeUnit.MILLISECONDS.sleep(200);
//            } catch (Exception e) {}
//        }
//
//        // TODO (Task 2a): Print the winner's name here
//        Typist [] arrayTypist = {seat1Typist, seat2Typist, seat3Typist};
//        Typist [] possibleWinners = new Typist[3];
//
//        int countOne = 0;
//        int countTwo = 0;
//        while (countOne < arrayTypist.length) {
//            if (arrayTypist[countOne].getProgress() >= passageLength) {
//                possibleWinners[countTwo] = arrayTypist[countOne];
//                countTwo += 1;
//            }
//            countOne+=1;
//        }
//
//        ArrayList<Typist> winners = new ArrayList<Typist>(3);
//        winners.add(possibleWinners[0]);
//
//        int maxValue = possibleWinners[0].getProgress();
//
//        for (int i = 1; i < countTwo; ++i) {
//
//            if (possibleWinners[i].getProgress() > maxValue) {
//                winners.clear();
//                maxValue = possibleWinners[i].getProgress();
//                winners.add(possibleWinners[i]);
//            }
//
//            else if (possibleWinners[i].getProgress() == maxValue) {
//                winners.add(possibleWinners[i]);
//            }
//        }
//
//        //need to update the accuracy of the winners accordingly:
//        //
//        for (int i = 0; i < winners.size(); ++i) {
//            winners.get(i).setPreviousAccuracy();
//            winners.get(i).setAccuracy(winners.get(i).getAccuracy() + 0.1);
//        }
//        //now need to get the name of winners
//        //
//        System.out.println("And the winner(s) is/are:... ");
//        for (int i = 0; i < winners.size(); ++i) {
//            System.out.print(winners.get(i).getName());
//            System.out.println(": Final Accuracy: " + winners.get(i).getAccuracy() + " (improved from " +
//                    winners.get(i).getPreviousAccuracy() + " )");
//            System.out.println(" ");
//        }


    }

    /**
     * Simulates one turn for a typist.
     *
     * If the typist is burnt out, they recover one turn's worth and skip typing.
     * Otherwise:
     *   - They may type a character (advancing progress) based on their accuracy.
     *   - They may mistype (sliding back) — the chance of a mistype should decrease
     *     for more accurate typists.
     *   - They may burn out — more likely for very high-accuracy typists
     *     who are pushing themselves too hard.
     *
     * @param theTypist the typist to advance
     */
    public static void advanceTypist(GuiTypist theTypist)
    {
        if (theTypist.isBurntOut())
        {
            // Recovering from burnout — skip this turn
            theTypist.recoverFromBurnout();
            return;
        }

        // Attempt to type a character
        if (Math.random() <= theTypist.getAccuracy())
        {
            theTypist.typeCharacter();
        }

        // Mistype check — the probability should reflect the typist's accuracy
        // The higher accuracy, the lower 1.0/accuracy is, meaning the lower the chance that condition
        // evaluates to true -> lower chance of slideBack

        if ( Math.random() <= ( (1.0 - theTypist.getAccuracy() ) * GuiTypingRace.MISTYPE_BASE_CHANCE ) )
        {
            theTypist.slideBack(GuiTypingRace.SLIDE_BACK_AMOUNT);
            theTypist.setMistyped();

        }

        // Burnout check — pushing too hard increases burnout risk
        // (probability scales with accuracy squared, capped at ~0.05)
        if (Math.random() < 0.05 * theTypist.getAccuracy() * theTypist.getAccuracy())
        {
            theTypist.burnOut(GuiTypingRace.BURNOUT_DURATION);
            //need to slightly decrease it
            theTypist.setPreviousAccuracy();
            theTypist.setAccuracy(theTypist.getAccuracy() - 0.05);
        }
    }

    /**
     * Returns true if the given typist has completed the full passage.
     *
     * @param theTypist the typist to check
     * @return true if their progress has reached or passed the passage length
     */
    public static boolean raceFinishedBy(GuiTypist theTypist)
    {
        // Ty was confident this condition was correct
        if (theTypist.getProgress() >= passageLength)
        {
            return true;

        }
        else
        {
            return false;
        }
    }

    /**
     * Prints the current state of the race to the terminal.
     * Shows each typist's position along the passage, burnout state,
     * and a WPM estimate based on current progress.
     */
    public static void printRace()
//    {
//        System.out.print('\u000C'); // Clear terminal
//
//        System.out.println("  TYPING RACE — passage length: " + passageLength + " chars");
//        multiplePrint('=', passageLength + 3);
//        System.out.println();
//
//        printSeat(seat1Typist);
//        System.out.println();
//
//        printSeat(seat2Typist);
//        System.out.println();
//
//        printSeat(seat3Typist);
//        System.out.println();
//
//        multiplePrint('=', passageLength + 3);
//        System.out.println();
//        System.out.println("  [~] = burnt out    [<] = just mistyped");
//    }

    /**
     * Prints a single typist's lane.
     *
     * Examples:
     *   |          ⌨           | TURBOFINGERS (Accuracy: 0.85)
     *   |    [zz]              | HUNT_N_PECK  (Accuracy: 0.40) BURNT OUT (2 turns)
     *
     * Note: Ty forgot to show when a typist has just mistyped. That would
     * be a nice improvement — perhaps a [<] marker after their symbol.
     *
     * @param theTypist the typist whose lane to print
     */
//    public static void printSeat(Typist theTypist)
//    {
//        int spacesBefore = theTypist.getProgress();
//        int spacesAfter  = passageLength - theTypist.getProgress();
//
//        System.out.print('|');
//        multiplePrint(' ', spacesBefore);
//
//        // Always show the typist's symbol so they can be identified on screen.
//        // Append ~ when burnt out so the state is visible without hiding identity.
//        System.out.print(theTypist.getSymbol());
//        if (theTypist.isBurntOut())
//        {
//            System.out.print('~');
//            spacesAfter--; // symbol + ~ together take two characters
//        }
//
//        if (theTypist.hasMistyped()) {
//            System.out.print("[<]");
//            spacesAfter-=3; //[<] = 3 characters
//
//        }
//
//        multiplePrint(' ', spacesAfter);
//        System.out.print('|');
//        System.out.print(' ');
//
//        // Print name and accuracy
//        if (theTypist.isBurntOut())
//        {
//            System.out.print(theTypist.getName()
//                    + " (Accuracy: " + theTypist.getAccuracy() + ")"
//                    + " BURNT OUT (" + theTypist.getBurnoutTurnsRemaining() + " turns)");
//        }
//        else if (theTypist.hasMistyped()) {
//            System.out.print(theTypist.getName()
//                    + " (Accuracy: " + theTypist.getAccuracy() + ")"
//                    + " <- just mistyped");
//            theTypist.exitMistyped(); //exit state
//        }
//        else
//        {
//            System.out.print(theTypist.getName()
//                    + " (Accuracy: " + theTypist.getAccuracy() + ")");
//        }
//    }

    /**
     * Prints a character a given number of times.
     *
     * @param aChar the character to print
     * @param times how many times to print it
     */
    public static void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }

    //method the sets length of passage before the start of each race
    public static void setPassageLength(int length) {
        final int minimumLength = 3;
        if (length < minimumLength) {
            GuiTypingRace.passageLength = 3;
            return;
        }
        GuiTypingRace.passageLength = length;
    }

    //used to generate random passage length;
    //
    public static int generateRandomPassageLength() {
        Random r = new Random();
        //making sure minimum length is 3
        return r.nextInt(47) + 3;
    }

    //method allows user to input integer answer to a question:
    //
    public static int inputInt(String Message) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(Message);
        String answer = scanner.nextLine();

        return Integer.parseInt(answer);
    }

    //allows the user to input the number of races they want:
    //until its greater than 0
    public static int inputNumberRaces() {
        int number = inputInt("Enter the number of races: ");
        while (number <= 0) {
            System.out.println("The number of races must be greater than 0");
            number = inputInt("Enter the number of races: ");
        }

        return number;
    }

    //configureRace():
    //Flow of configureRace:
    //display Frame -> This is another method
    //grab all information -> (event method) we probably do it by adding event listener to button which does all the
    //      Passage selected: -> then set it
    //      number of typists -> return value
    //      difficulty modifiers -> we will represent as a record:
    //                              fieldOne = tells us if its on/off
    //                              fieldTwo = value to adjust one of the slideBack or progress step by or accuracy
    // each difficulty modifier will be set as a field
    //return number of typists they want

    //method to initialise typists before all possible future games:
    //
    public static void initialiseTypists() {
        GuiTypingRace.guiTypists = new GuiTypist[6];
        String [] names = {"TURBO_FINGERS", "QWERTY_QUEEN", "HUNT_N_PECK", "MCQUEEN", "BOBBY", "JAMES"};
        for (int i = 0; i < 6; ++i) {
            GuiTypingRace.guiTypists[i] = new GuiTypist('T', names[i], 0.5, "DEFAULT",
                    "DEFAULT", "red", "DEFAULT");
        }
    }

    public static void displayFrame() {
        //let's add the buttons
        //
        GuiTypingRace.frame = new JFrame("Typing Race competition: ");
        GuiTypingRace.frame.setLayout(new BoxLayout(GuiTypingRace.frame.getContentPane(), BoxLayout.Y_AXIS));

        //Now the buttons panel
        //
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        //Now let's add the buttons
        //
        JButton raceButton = new JButton("Configure Race");
        JButton typistButton = new JButton("customiseTypists");
        JButton performanceButton = new JButton("view typists performance");

        //adding actionListeners for each of the buttons
        //
        ConfigureRace raceButtonListener = new ConfigureRace(GuiTypingRace.frame);
        raceButton.addActionListener(raceButtonListener);

        buttonsPanel.add(raceButton);
        buttonsPanel.add(typistButton);
        buttonsPanel.add(performanceButton);

        GuiTypingRace.mainPanel = new JPanel();
        GuiTypingRace.mainPanel.setSize(100, 100);

        GuiTypingRace.frame.add(buttonsPanel);
        GuiTypingRace.frame.add(GuiTypingRace.mainPanel);

        GuiTypingRace.frame.setSize(900, 900);
        GuiTypingRace.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when we press X button
        GuiTypingRace.frame.setVisible(true); //make sure the frame is displayed.
    }

    public static void setStarterPassages() {

        passages = new ArrayList<>(3);

        passages.add("The quick brown fox jumps over dog.");
        passages.add("Mountains stand tall under blue skies.");
        passages.add("Rain falls softly on the green grass.");

    }

    
}
