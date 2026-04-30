package Part2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartRaceListener implements ActionListener {

    JFrame parentFrame;
    JPanel mainPanel;

    public StartRaceListener(JFrame parentFrame_) {
        this.parentFrame = parentFrame_;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        GuiTypingRace.startRace();
    }
}