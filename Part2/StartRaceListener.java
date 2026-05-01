package Part2;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartRaceListener implements ActionListener {

    JFrame parentFrame;
    JPanel mainPanel;

    public StartRaceListener(JFrame parentFrame_) {
        this.parentFrame = parentFrame_;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setMainPanel();
        this.mainPanel.removeAll();

        //adding the JTextPane that will be used to display our races.
        //
        JTextPane textArea = new JTextPane();


        GuiTypingRace.startRace();
    }

    public void setMainPanel() {
        Component mainPanel = this.parentFrame.getContentPane().getComponent(1);
        this.mainPanel = (JPanel) mainPanel;
    }
}
