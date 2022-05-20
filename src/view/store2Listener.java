package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class store2Listener implements ActionListener {
    private final JFrame menuFrame;
    public store2Listener(JFrame menuFrame){
        this.menuFrame = menuFrame;
    }
    public void actionPerformed(ActionEvent e) {
        new Music_Click();
        new Music_PowerOfGame().stop();

        ChessGameFrameStore2.fatherFrame = menuFrame;
        ChessGameFrameStore2.main(null);
        new Music_PlayChess();
        //   newGameOptionFrame.setVisible(false);
    }
}
