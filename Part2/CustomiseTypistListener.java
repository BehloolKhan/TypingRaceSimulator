package Part2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomiseTypistListener implements ActionListener {
    private  JFrame parentFrame;
    private  JPanel mainPanel;

    public CustomiseTypistListener(JFrame parentFrame_) {
        setMainPanel(parentFrame_);
        setTypistsNames();
    }


    public void setMainPanel(JFrame parentFrame_) {
        Component mainPanel = parentFrame_.getContentPane().getComponent(1);
        this.mainPanel = (JPanel) mainPanel;
    }

    public void setTypistsNames() {
        for (String name: GuiTypingRace.getNamesOfTypists()) {typistsNames.addElement(name);}
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.mainPanel.removeAll(); //start with a blank canvis

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

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}