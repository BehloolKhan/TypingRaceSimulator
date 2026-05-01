package Part2;
//This will host the GUI for my typing race
//

import java.awt.*;
import java.lang.Math;
import java.util.*;
import javax.swing.*; // will need this module for our GUI
import javax.swing.Timer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class GuiTypingRace
{
    private static DefaultListModel<String> passages;   //This will store information about all passages

    private static String currentPassage;//used to keep track of the current length of passage
    private static int passageLength; //used to keep track of the length of current passage
    private static int currentNumberOfPlayers;

    private static HashMap<String, GuiTypist> guiTypists; //keeps track of all the possible typists
    private static ArrayList<GuiTypist> currentGuiTypists = new ArrayList<GuiTypist>(6);


    private static JFrame frame; //this is the frame at that makes up the GUi
    private static JPanel mainPanel; //This is the panel that will be switched out

    private static final double MISTYPE_BASE_CHANCE = 0.3;
    private static  int    SLIDE_BACK_AMOUNT   = 2;
    private static final int    BURNOUT_DURATION     = 2;

    //following contains modes that Can be applied to everyone
    //
    private static boolean autoCorrectOn; // used to track weather auto correct is on or off
    private static boolean caffeineMode; // used to track weather caffeineMode is on or off
    private static boolean nightShift; //used to keep track of weather Night shift is on or off


    public static void main (String[] args) {

        //we add the 6 typists to the array
        initialiseTypists();
        setStarterPassages();
        displayFrame();

    }

    /**
     * Seats a typist at the given seat number (1, 2, or 3).
     * @param theTypist  the typist to seat
     */

    public static void addTypist(GuiTypist theTypist)
    {
        currentGuiTypists.add(theTypist);
    }

    /*
    * Method clears the players stored in the arrayList, if any, from previous round.
    * We will call this method at the end of the race
    */

    public static void clearPlayers() {
        currentGuiTypists.clear();
    }

    /**
     * Starts the typing race.
     * All typists are reset to the beginning, then the simulation runs
     * turn by turn until one typist completes the full passage.
     *
     * Note from Ty: "I didn't bother printing the winner at the end,
     * you can probably figure that out yourself."
     */
    public static void startRaceGUI(ArrayList<JTextPane> textAreas, JPanel panel_) {

        if (autoCorrectOn) {
            SLIDE_BACK_AMOUNT = 1;
        }

        if (caffeineMode) {
            for (int i = 0; i < currentGuiTypists.size(); ++i) {
                currentGuiTypists.get(i).setAccuracy(currentGuiTypists.get(i).getAccuracy() - 0.1);
            }
        }

        // Reset all typists to the start of the passage
        for (int i = 0; i < currentGuiTypists.size(); ++i) {
            currentGuiTypists.get(i).resetToStart();
        }

        Timer raceTimer = new Timer(200, null);
        raceTimer.addActionListener(e -> {

            boolean finished = false;

            // Advance each typist by one turn
            for (int i = 0; i < currentGuiTypists.size(); ++i) {
                advanceTypist(currentGuiTypists.get(i));
            }

            printRace(textAreas, panel_);

            for (GuiTypist typist : currentGuiTypists) {
                if (raceFinishedBy(typist)) {
                    finished = true;

                }
            }

            if (finished) {
                raceTimer.stop();


                raceTimer.start();
                // TODO (Task 2a): Print the winner's name here
                GuiTypist[] possibleWinners = new GuiTypist[currentGuiTypists.size()];

                int countOne = 0;
                int countTwo = 0;

                while (countOne < currentGuiTypists.size()) {
                    if (currentGuiTypists.get(countOne).getProgress() >= passageLength) {
                        possibleWinners[countTwo] = currentGuiTypists.get(countOne);
                        countTwo += 1;
                    }
                    countOne += 1;
                }

                ArrayList<GuiTypist> winners = new ArrayList<GuiTypist>(6);
                winners.add(possibleWinners[0]);

                int maxValue = possibleWinners[0].getProgress();

                for (int i = 1; i < countTwo; ++i) {

                    if (possibleWinners[i].getProgress() > maxValue) {
                        winners.clear();
                        maxValue = possibleWinners[i].getProgress();
                        winners.add(possibleWinners[i]);
                    } else if (possibleWinners[i].getProgress() == maxValue) {
                        winners.add(possibleWinners[i]);
                    }
                }

                //need to update the accuracy of the winners accordingly:

                for (int i = 0; i < winners.size(); ++i) {
                    winners.get(i).setPreviousAccuracy();
                    winners.get(i).setAccuracy(winners.get(i).getAccuracy() + 0.1);
                }
                //now need to get the name of winners
                //
                panel_.add(new JLabel("And the winner(s) is/are:... "));

                for (int i = 0; i < winners.size(); ++i) {
                    panel_.add(new JLabel(winners.get(i).getName()));
                    panel_.add(new JLabel(": Final Accuracy: " + winners.get(i).getAccuracy() + " (improved from " +
                            winners.get(i).getPreviousAccuracy() + " )"));
                }
                panel_.revalidate();
                panel_.repaint();

                GuiTypingRace.clearPlayers();
            }
        });
        raceTimer.start();
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
        if (theTypist.getProgress() >= GuiTypingRace.passageLength)
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
    public static void printRace(ArrayList<JTextPane> textAreas, JPanel panel_) {

        for (int i = 0; i < GuiTypingRace.currentGuiTypists.size(); ++i) {
            printSeat(GuiTypingRace.currentGuiTypists.get(i), textAreas.get(i), panel_);
        }

//        multiplePrint('=', passageLength + 3);
//        System.out.println();
//        System.out.println("  [~] = burnt out    [<] = just mistyped");
    }

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
    public static void printSeat(GuiTypist theTypist, JTextPane textPane, JPanel panel_)
    {
        textPane.setText(GuiTypingRace.currentPassage);

        int spacesBefore = theTypist.getProgress();
        int spacesAfter  = passageLength - theTypist.getProgress();

        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, theTypist.getColour());

        textPane.getStyledDocument().setCharacterAttributes(0, spacesBefore, attributes, true);

        panel_.revalidate();
        panel_.repaint();

//        System.out.print('|');
//        multiplePrint(' ', spacesBefore);
//
//        // Always show the typist's symbol so they can be identified on screen.
//        // Append ~ when burnt out so the state is visible without hiding identity.
//        System.out.print(theTypist.getSymbolString());
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
    }

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

    //method to initialise typists before all possible future games:
    //
    public static void initialiseTypists() {
        GuiTypingRace.guiTypists = new HashMap<String, GuiTypist>(6);
        String [] names = {"TURBO_FINGERS", "QWERTY_QUEEN", "HUNT_N_PECK", "MCQUEEN", "BOBBY", "JAMES"};
        for (int i = 0; i < 6; ++i) {
            GuiTypingRace.guiTypists.put(names[i], new GuiTypist("T", names[i], 0.5, "DEFAULT",
                    "DEFAULT", "null", "DEFAULT"));
        }
    }

    public static void displayFrame() {
        //let's add the frame
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

        typistButton.addActionListener(new CustomiseTypistListener(GuiTypingRace.frame));

        buttonsPanel.add(raceButton);
        buttonsPanel.add(typistButton);
        buttonsPanel.add(performanceButton);

        GuiTypingRace.mainPanel = new JPanel();
        GuiTypingRace.mainPanel.setSize(100, 100);
        GuiTypingRace.mainPanel.setLayout(new BoxLayout(GuiTypingRace.mainPanel, BoxLayout.Y_AXIS));

        GuiTypingRace.frame.add(buttonsPanel);
        GuiTypingRace.frame.add(GuiTypingRace.mainPanel);

        GuiTypingRace.frame.setSize(900, 600);
        GuiTypingRace.frame.setLocation(100, 100);
        GuiTypingRace.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when we press X button
        GuiTypingRace.frame.setVisible(true); //make sure the frame is displayed.
    }

    public static void setStarterPassages() {

        passages = new DefaultListModel<>();

        passages.addElement("The quick brown fox jumps over dog.");
        passages.addElement("Mountains stand tall under blue skies.");
        passages.addElement("Rain falls softly on the green grass.");

    }

    public static DefaultListModel<String> getPassages() {
        return GuiTypingRace.passages;
    }

    public static void setDifficultyModifiers(boolean autoCorrect_, boolean caffeineMode_, boolean nightShift_) {
        GuiTypingRace.autoCorrectOn = autoCorrect_;
        GuiTypingRace.caffeineMode = caffeineMode_;
        GuiTypingRace.nightShift = nightShift_;
    }

    public static void setPassageInfo(String passage_) {
        GuiTypingRace.currentPassage = passage_;
        GuiTypingRace.passageLength = passage_.length();

        if (GuiTypingRace.passageLength == 0) {
            GuiTypingRace.currentPassage = "The Default passage because no passage was selected";
            GuiTypingRace.passageLength = GuiTypingRace.currentPassage.length();
        }
    }

    public static void setNumberOfTypists(Integer selectedNumberOfTypists) {
        if (selectedNumberOfTypists == null) {
            GuiTypingRace.currentNumberOfPlayers = 2;
            return;
        }

        GuiTypingRace.currentNumberOfPlayers = selectedNumberOfTypists.intValue();
    }

    public static Set<String> getNamesOfTypists () {
        return GuiTypingRace.guiTypists.keySet();
    }

    public static int getCurrentNumberOfPlayers() {
        return GuiTypingRace.currentNumberOfPlayers;
    }

    public static JFrame getParentFrame() {
        return GuiTypingRace.frame;
    }

    public static HashMap<String, GuiTypist> getGuiTypists() {
        return GuiTypingRace.guiTypists;
    }

    public static void getPassageInfo() {
        System.out.println(GuiTypingRace.currentPassage);
        System.out.println(GuiTypingRace.passageLength);
    }

    public static String getPassage() {
        return GuiTypingRace.currentPassage;
    }
}
