package Part2;
import Part1.Typist;

import java.awt.*;


public class GuiTypist extends Typist{

    private String TypingStyle;
    private String KeyBoardType;
    private Color colour;
    private String Accessory;
    private String symbol;

    //accessor methods for all fields
    //
    protected void setTypingStyle(String typingStyle) {
        if(!(typingStyle.equals("Touch Typist") || typingStyle.equals("Hunt and Peck") ||
                typingStyle.equals("Phone thumbs") || typingStyle.equals("Voice-to-text"))) {
            this.TypingStyle = "Touch Typist";
            return;
        }

        this.TypingStyle = typingStyle;
    }

    protected void setKeyBoardType(String keyBoard) {
        if(!(keyBoard.equals("Mechanical") || keyBoard.equals("Membrane")
                || keyBoard.equals("Touchscreen") || keyBoard.equals("Stenography"))) {
            return;
        }

        this.KeyBoardType = keyBoard;
    }

    protected void setColour(String colour_) {

        if (colour_.equals("YELLOW")) {
            this.colour = Color.YELLOW;
        }
        else if (colour_.equals("BLUE")) {
            this.colour = Color.BLUE;
        }
        else if (colour_.equals("GREEN")) {
            this.colour = Color.GREEN;
        }
        else if (colour_.equals("RED")) {
            this.colour = Color.RED;
        }
        else if (colour_.equals("MAGENTA")) {
            this.colour = Color.MAGENTA;
        }
        else {
            this.colour = Color.RED;
        }
    }

    protected void setAccessories(String Accessory) {
        this.Accessory = Accessory;
        if (!(Accessory.equals("Wrist Support") || Accessory.equals("Energy Drink") ||
                Accessory.equals("Noise-Cancelling"))) {
            this.Accessory = "Wrist Support";
        }
    }

    protected String getTypingStyle() {
        return this.TypingStyle;
    }

    protected String getKeyBoardType() {
        return this.KeyBoardType;
    }

    protected Color getColour() {
        return this.colour;
    }

    protected String getAccessory() {
        return this.Accessory;
    }

    public GuiTypist(String typistSymbol, String typistName, double typistAccuracy, String typingStyle,
                     String keyBoardType, String colour, String Accessory) {


        super(typistName, typistAccuracy);
        setSymbol(typistSymbol);
        setAccessories(Accessory);
        setColour(colour);
        setKeyBoardType(keyBoardType);
        setTypingStyle(typingStyle);

    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public String getSymbolString() {
        return this.symbol;
    }
}