package testingUnit;

import Part1.Typist;
import java.util.HashMap;
import java.lang.Integer;

//This record will be used when mapping a test ID to a test case in the burnt out method
//a test case usually has two attributes: the value to test and a description specifiying its validity
record compositeTestCase (String description, int number) {};

//The purpose of this class is to Test the methods in the TypistClass
public class TypistTest {

    static Typist typist;

    public static void main(String [] args) {
        //This will be the method that calls the tests;
        //
        setup();
        test_cases_testBurnOut();

    }

    static void setup() {

        typist = new Typist('\u2460', "playerOne", 0.5);
    }

    static void testBurnOut (int turns) {

        typist.burnOut(turns);
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

            System.out.println(key);
            System.out.println(testCases.get(key).description());
            System.out.println(testCases.get(key).number());
        }

    }

}