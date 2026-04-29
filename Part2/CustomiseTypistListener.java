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

    @Override
    public void actionPerformed(ActionEvent e) {



    }

    public void refreshPanel() { //used to refresh panel and replace it with new version
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }
}