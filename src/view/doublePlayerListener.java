package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class doublePlayerListener implements ActionListener {
    private final JFrame newGameOptionFrame;
    public doublePlayerListener(JFrame newGameOptionFrame){
        this.newGameOptionFrame = newGameOptionFrame;
    }
    public void actionPerformed(ActionEvent e) {
        new Music_Click();
        new Music_PowerOfGame().stop();
        ChessGameFrame.fatherFrame = newGameOptionFrame;
        ChessGameFrame.main(null);
        newGameOptionFrame.setVisible(false);

    }
}
