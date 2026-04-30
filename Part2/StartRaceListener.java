package Part2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartRaceListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        GuiTypingRace.startRace();
    }
}