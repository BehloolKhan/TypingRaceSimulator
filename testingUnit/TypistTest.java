package testingUnit;

import Part1.Typist;
import java.util.HashMap;

//This record will be used when mapping a test ID to a test case in the burnt out method
//a test case usually has two attributes: the value to test and a description specifiying its validity
record compositeTestCase (String description, int number) {};
record compositeTestCaseDouble(String description, double number) {};

        //The purpose of this class is to Test the methods in the TypistClass
public class TypistTest {

    static Typist typist;

    public static void main(String [] args) {
        //This will be the method that calls the tests;
        //
        setup();
        testTypeCharacter();

    }

    static void setup() {

        typist = new Typist('\u2460', "playerOne", 0.5);
    }

    static void testBurnOut (int turns) {

        typist.burnOut(turns);
    }

    //used to display info about a typist states
    static void descriptionTypist() {
        System.out.println("The number of burnOut turns remaining is: " + typist.getBurnoutTurnsRemaining());
        System.out.println("The player is burnt out: " + typist.isBurntOut());

    }

    //This method will run the different test cases on the burnOut method:
    //
    static void test_cases_testBurnOut() {

        HashMap<String, compositeTestCase> testCases= new HashMap<String, compositeTestCase>(3);
        testCases.put("TB-001", new compositeTestCase("valid", 3));
        testCases.put("TB-002", new compositeTestCase("invalid", -2));
        testCases.put("TB-003", new compositeTestCase("boundary", 0));

        for (String key: testCases.keySet()) {

            System.out.print("Test case ID: "+ key);
            System.out.print("           ");
            System.out.println("Category: " + testCases.get(key).description());

            //now going to call the test case value on the burnOut method
            //
            testBurnOut(testCases.get(key).number());
            descriptionTypist();
            setup(); //as a way to reset the value of burn out and isBurnt out for testing methods
        }

    }

    static void testRecoverFromBurnOut (int initialBurntOut) {

        //first the player must be in a burnt OUT state
        System.out.println("Stats before");
        testBurnOut(initialBurntOut);
        descriptionTypist();

        System.out.println(" ");

        System.out.println("Stats after ");
        typist.recoverFromBurnout();
        descriptionTypist();

    }

    static void test_cases_recoverFromBurntOut () {
        HashMap<String, compositeTestCase> testCases= new HashMap<String, compositeTestCase>(2);
        testCases.put("TRB-001", new compositeTestCase("valid", 3));
        testCases.put("TRB-002", new compositeTestCase("boundary", 1));

        for (String key : testCases.keySet()) {

            System.out.println("***************************");
            System.out.print("Test ID: " + key);
            System.out.print("               ");
            System.out.println("Category: " + testCases.get(key).description());

            testRecoverFromBurnOut(testCases.get(key).number());
            setup();
        }
    }

    static void testTypeCharacter() {

        System.out.print("Test ID: TT-001");
        System.out.print("               ");
        System.out.println("Category: boundary/valid");
        System.out.println("Progress Before: " + typist.getProgress());
        System.out.println("Method be loading....");
        typist.typeCharacter();
        System.out.println("Progress after: " + typist.getProgress());
    }

    static void testSetAccuracy(double Accuracy) {
        System.out.println("Accuracy supplied: " + Accuracy);
        typist.setAccuracy(Accuracy);
        System.out.println("Accuracy set to: " + typist.getAccuracy());
    }

    static void test_cases_testSetAccuracy() {
        HashMap<String, compositeTestCaseDouble> testCases= new HashMap<String, compositeTestCaseDouble>(5);

        testCases.put("TRB-001", new compositeTestCaseDouble("valid", 0.5));
        testCases.put("TRB-002", new compositeTestCaseDouble("invalid", -0.1));
        testCases.put("TRB-003", new compositeTestCaseDouble("invalid", 1.1));
        testCases.put("TRB-004", new compositeTestCaseDouble("boundary", 0.0));
        testCases.put("TRB-005", new compositeTestCaseDouble("boundary", 1.0));

        for (String key: testCases.keySet()) {

            System.out.println("**************************");
            System.out.println("Test case ID: " + key + "                  Category: " + testCases.get(key).description());
            testSetAccuracy(testCases.get(key).number());
            setup();
        }
    }


}