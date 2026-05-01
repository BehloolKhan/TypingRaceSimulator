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
        JTextPane textPane;
        ArrayList<JTextPane> textPanes = new ArrayList<JTextPane>();

        for (int i = 1; i <= GuiTypingRace.getCurrentNumberOfPlayers(); ++i) {

            textPane = new JTextPane();
            textPane.setText(GuiTypingRace.getPassage());
            SimpleAttributeSet attributes = new SimpleAttributeSet();
            StyleConstants.setForeground(attributes, Color.GRAY);

            textPanes.add(textPane);
            this.mainPanel.add(textPane);
        }

        GuiTypingRace.startRace(textPanes, this.mainPanel);
        //Now we have to somehow update our typist text: ?
        this.mainPanel.revalidate();
        this.mainPanel.repaint();

    }

    public void setMainPanel() {
        Component mainPanel = this.parentFrame.getContentPane().getComponent(1);
        this.mainPanel = (JPanel) mainPanel;
    }
}
