package Part2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomiseTypistListener implements ActionListener {
    private JFrame parentFrame;
    private JPanel mainPanel;

    public CustomiseTypistListener(JFrame parentFrame_) {
        this.parentFrame = parentFrame_;
    }


    public void setMainPanel() {
        Component mainPanel = this.parentFrame.getContentPane().getComponent(1);
        this.mainPanel = (JPanel) mainPanel;
    }

    public void setTypistsNames(DefaultComboBoxModel<String> typistsNames_) {
        for (String name: GuiTypingRace.getNamesOfTypists()) {typistsNames_.addElement(name);}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setMainPanel();

        this.mainPanel.removeAll(); //start with a blank canvis
        this.mainPanel.setLayout(new FlowLayout());

        if (GuiTypingRace.getCurrentNumberOfPlayers() == 0) {
            return; //exit early;
        }
        for (int i = 1; i <= GuiTypingRace.getCurrentNumberOfPlayers(); ++i) {
            displaySeats(i);
        }

        JButton button = new JButton("display profiles: ");
        button.addActionListener(new SubmitTypistListener(this.mainPanel));
        this.mainPanel.add(button);

        refreshPanel();
    }

    public void displaySeats(int number) {

        JLabel seatLabel = new JLabel("SeatNumber " + number);
        DefaultComboBoxModel<String> typists_ = new DefaultComboBoxModel<>();
        setTypistsNames(typists_);

        JComboBox typistToSelect = new JComboBox(typists_);
        typistToSelect.addActionListener(new SelectTypistListener());

        this.mainPanel.add(seatLabel);
        this.mainPanel.add(typistToSelect);

    }

    public void refreshPanel() { //used to refresh panel and replace it with new version
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }
}

//class that is used to implement listener object responsible for
//tracking which player is selected in a the drop down list
//
class SelectTypistListener implements ActionListener {

    private static ArrayList<String> names = new ArrayList<>(6);

    @Override
    public void actionPerformed(ActionEvent e) {
        Object comboBox = e.getSource();
        JComboBox newComboBox = (JComboBox) comboBox;

        String valueSelected = (String) newComboBox.getSelectedItem();

        if (doesExits(valueSelected)) {
            displayErrorMessage(valueSelected);
        }
        else {
            names.add(valueSelected);
        }
    }

    private static boolean doesExits(String value) {
        for (String item : names) {
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private static void displayErrorMessage(String value) {
        JOptionPane.showMessageDialog(GuiTypingRace.getParentFrame(), value + "is already selected",
                "duplicate Player error", JOptionPane.WARNING_MESSAGE);
    }

    public static ArrayList<String> getSelectedNames() {
        return names;
    }
}

class SubmitTypistListener implements ActionListener {

    private JPanel parentPanel;

    public SubmitTypistListener(JPanel parentPanel_) {
        this.parentPanel = parentPanel_;
    }

    private static ButtonGroup setCheckBoxes(JPanel parentPanel_, String [] options) {
        ButtonGroup G1 = new ButtonGroup();
        JRadioButton current;
        for (int i = 0; i < options.length; ++i) {
            current = new JRadioButton();
            current.setText(options[i]);
            current.setActionCommand(options[i]);
            G1.add(current);
            parentPanel_.add(current);
        }
        return G1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (String name: SelectTypistListener.getSelectedNames()) {
            GuiTypist currentTypist = GuiTypingRace.getGuiTypists().get(name);

            JPanel profilePanel = new JPanel();
            profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));

            profilePanel.add(new JLabel("Profile: " + name));
            profilePanel.add(new JLabel("PlayersAccuracy " + currentTypist.getAccuracy()));

            //Then the options:
            profilePanel.add(new JLabel("Typing Style: "));
            ButtonGroup typingStyles_ = setCheckBoxes(profilePanel, new String[] {"Touch Typist", "Hunt and Peck",
                    "Phone thumbs", "Voice-to-text"});

            //then they key board type
            profilePanel.add(new JLabel("Select from different Key Boards"));
            ButtonGroup keyBoardType_ = setCheckBoxes(profilePanel, new String [] {"Mechanical", "Membrane",
                    "TouchScreen", "Stenography"});


            profilePanel.add(new JLabel("Symbol: "));
            JTextField symbol = new JTextField();
            profilePanel.add(symbol);

            profilePanel.add(new JLabel("Select Colour: "));

            String [] colours = {"YELLOW", "BLUE", "GREEN", "RED", "MAGENTA"};
            DefaultComboBoxModel<String> coloursToSelect = new DefaultComboBoxModel<>(colours);
            JComboBox<String> coloursList = new JComboBox<>(coloursToSelect);

            profilePanel.add(coloursList);

            String [] accessories = {"Wrist support", "Energy Drink", "Noise-Cancelling HeadPhones"};
            JComboBox<String> accessoriesList = new JComboBox<>(accessories);

            profilePanel.add(new JLabel("Select Accessory"));
            profilePanel.add(accessoriesList);


            JButton submitButton = new JButton("Submit Typist - information ");
            submitButton.addActionListener(new SubmitTypistInfoListener(typingStyles_, keyBoardType_,
                    symbol, coloursList, accessoriesList, name));

            profilePanel.add(submitButton);

            this.parentPanel.add(profilePanel);
        }

        JButton submit = new JButton("Start Race: ");
        submit.addActionListener(new StartRaceListener(GuiTypingRace.getParentFrame()));

        this.parentPanel.add(submit);
        this.parentPanel.revalidate();
        this.parentPanel.repaint();
    }
}


//This class is responsible for processing the typist information
//
class SubmitTypistInfoListener implements ActionListener {

    private ButtonGroup typistStyle;
    private ButtonGroup KeyBoardType;
    private JTextField symbol;
    private JComboBox<String> coloursList;
    private JComboBox<String> accessoriesList;
    private String name;

    public SubmitTypistInfoListener(ButtonGroup typistStyle_, ButtonGroup keyBoardType_,
                                    JTextField symbol_, JComboBox<String> coloursList_,
                                    JComboBox<String> accessoriesList_, String name_) {

        this.typistStyle = typistStyle_;
        this.KeyBoardType = keyBoardType_;
        this.symbol = symbol_;
        this.coloursList = coloursList_;
        this.accessoriesList = accessoriesList_;
        this.name = name_;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        GuiTypist person = GuiTypingRace.getGuiTypists().get(this.name);

        try {
            person.setTypingStyle(this.typistStyle.getSelection().getActionCommand());
            person.setKeyBoardType(this.KeyBoardType.getSelection().getActionCommand());
        }

        catch (NullPointerException exception) {
            JOptionPane.showMessageDialog(GuiTypingRace.getParentFrame(), "Maks sure you have selected a keyoard type and a typist tyle",
                    "Not selected value", JOptionPane.WARNING_MESSAGE);
        }

        person.setSymbol(this.symbol.getText());
        person.setColour((String) this.coloursList.getSelectedItem());
        person.setAccessories((String) this.accessoriesList.getSelectedItem());

        GuiTypingRace.addTypist(person);
    }
}