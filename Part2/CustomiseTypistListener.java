package Part2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomiseTypistListener implements ActionListener {
    private  JFrame parentFrame;
    private  JPanel parentPanel;


    @Override
    public void actionPerformed(ActionEvent e) {



    }

    public void refreshPanel() { //used to refresh panel and replace it with new version
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }
}