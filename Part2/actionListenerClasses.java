package Part2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ConfigureRace implements ActionListener
{
    private JFrame parentFrame;
    private JPanel mainPanel;


    public ConfigureRace(JFrame parentFrame_) {
        this.parentFrame = parentFrame_;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //accessing the components of the parentFrame
        //The parent Frame has two components, the second is the mainPanel
        Component mainPanel = this.parentFrame.getContentPane().getComponent(1);
        this.mainPanel = (JPanel) mainPanel;


        configureRace();

    }

    public void configureRace() {

        JList<String> currentPassages = new JList<>(GuiTypingRace.getPassages());

        JScrollPane scrollPassages = new JScrollPane(currentPassages);
        this.mainPanel.add(scrollPassages);

        JLabel button = new JLabel("Add New passage");
        JTextArea newPassage = new JTextArea(2, 21); //making sure max passage length is 42
        JButton submitPassage = new JButton("Submit New Passage");

        //adding actionListener object to submitPassage
        //
        SubmitPassageListener buttonListener = new SubmitPassageListener(newPassage);
        submitPassage.addActionListener(buttonListener);

        this.mainPanel.add(button);
        this.mainPanel.add(newPassage);
        this.mainPanel.add(submitPassage);

        JLabel playersSelectLabel = new JLabel("Select number of players");
        Integer [] typists = {2, 3, 4, 5, 6};
        JComboBox typistList = new JComboBox(typists);

        JLabel difficultyLabel = new JLabel("Select difficulty modifier");

        JCheckBox autoCorrect = new JCheckBox("Auto-correct");
        JCheckBox caffeineMode = new JCheckBox("Caffeine Mode");
        JCheckBox nightShift = new JCheckBox("Night Shift");

        this.mainPanel.add(playersSelectLabel);
        this.mainPanel.add(typistList);
        this.mainPanel.add(difficultyLabel);

        this.mainPanel.add(autoCorrect);
        this.mainPanel.add(caffeineMode);
        this.mainPanel.add(nightShift);

        this.mainPanel.revalidate(); //making sure the panel updates
        this.mainPanel.repaint(); //making sure its redrawn
    };

}

//The following class will deal with what should happen when the corresponding UI
//
class SubmitPassageListener implements ActionListener {
    //going to need a pointer to the TextArea object because we need to grab the information from it
    private JTextArea textArea;
    public SubmitPassageListener(JTextArea textArea_) {
        this.textArea = textArea_;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String passage = this.textArea.getText();
        if (passage.isEmpty()) {
            return;
        }
        passage = passage.replaceAll("\\r?\\n", " "); //including new line character for both windows and Unix

        GuiTypingRace.getPassages().addElement(passage);
        this.textArea.setText("");
    }
}

class SubmitButtonListener implements ActionListener {
    private String passageSelected;
    private Integer numberOfRaces;

    private boolean autoCorrect;
    private boolean caffeineMode;
    private boolean NightShift;
    SubmitButtonListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //what is the purpose of this event
        //we need to grab the passage selected
        //The number of races selected
        //The check boxes

        //set the values in GuiTypingRace to corresponding values
    }
}

