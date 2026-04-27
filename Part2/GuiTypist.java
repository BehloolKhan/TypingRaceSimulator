package Part2;
import Part1.Typist;


public class GuiTypist extends Typist{

    private String TypingStyle;
    private String KeyBoardType;
    private String colour;
    private String Accessories;

    //accessor methods for all fields
    //
    protected void setTypingStyle(String typingStyle) {
        if(!(typingStyle.equals("Touch Typist") || typingStyle.equals("Hunt and Peck") ||
                typingStyle.equals("Phone thumbs") || typingStyle.equals("Voice-to-text"))) {
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
        this.colour = colour_;
        if (colour_.equals("")) {
            this.colour = "red";
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

    protected String getColour() {
        return this.colour;
    }

    protected String getAccessory() {
        return this.Accessory;
    }
}