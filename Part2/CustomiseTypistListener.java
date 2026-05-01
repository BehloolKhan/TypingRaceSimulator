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


    }

    public void displaySeats(int number) {
        
        JLabel seatLabel = new JLabel("SeatNumber " + number);
        JComboBox typistToSelect = new JComboBox(this.typistsNames);
        typistToSelect.addActionListener(new SelectTypistListener(this.typistsNames));

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

    private DefaultListModel<String> typistNames;

    public SelectTypistListener(DefaultListModel<String> typistNames_) {
        this.typistNames = typistNames_;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object comboBox = e.getSource();
        JComboBox newComboBox = (JComboBox) comboBox;

        String valueSelected = (String) newComboBox.getSelectedItem();
        this.typistNames.removeElement(valueSelected);
    }

    private static boolean doesExits(String value) {
        for (String item : names) {
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private static void displayErrorm
}

class SubmitTypistListener implements ActionListener {

    private JPanel parentPanel;

    public SubmitTypistListener(JPanel parentPanel_) {
        this.parentPanel = parentPanel_;
    }

    private static void setCheckBoxes(JPanel parentPanel_, String [] options) {
        ButtonGroup G1 = new ButtonGroup();
        JRadioButton current;
        for (int i = 0; i < options.length; ++i) {
            current = new JRadioButton();
            current.setText(options[i]);
            G1.add(current);
            parentPanel_.add(current);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (String name: SelectTypistListener.getSelectedNames()) {
            GuiTypist currentTypist = GuiTypingRace.getGuiTypists().get(name);

            JPanel profilePanel = new JPanel();

            profilePanel.add(new JLabel("Profile: " + name));
            profilePanel.add(new JLabel("PlayersAccuracy " + currentTypist.getAccuracy()));

            //Then the options:
            profilePanel.add(new JLabel("Typing Style: "));

            setCheckBoxes(profilePanel, new String[] {"Touch Typist", "Hunt and Peck",
                    "Phone thumbs", "Voice-to-text"});

            //then they key board type
            profilePanel.add(new JLabel("Select from different Key Boards"));

            setCheckBoxes(profilePanel, new String [] {"Mechanical", "Membrane",
                    "TouchScreen", "Stenography"});
            profilePanel.add(new JLabel("Symbol"));
            profilePanel.add(new JLabel("Select Colour: "));

            Color [] colours = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.MAGENTA};
            DefaultComboBoxModel<Color> coloursToSelect = new DefaultComboBoxModel<>(colours);
            JComboBox<Color> coloursList = new JComboBox<>(coloursToSelect);

            profilePanel.add(coloursList);
        }
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

        person.setTypingStyle(this.typistStyle.getSelection().getActionCommand());
        person.setKeyBoardType(this.KeyBoardType.getSelection().getActionCommand());
        person.setSymbol(this.symbol.getText());

        person.setColour((String ) this.coloursList.getSelectedItem());
        person.setAccessories((String) this.accessoriesList.getSelectedItem());
    }
}