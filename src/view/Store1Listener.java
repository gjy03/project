package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class store1Listener implements ActionListener {
    private final JFrame menuFrame;
    public store1Listener(JFrame menuFrame){
        this.menuFrame = menuFrame;
    }
    public void actionPerformed(ActionEvent e) {
        new Music_Click();
        new Music_PowerOfGame().stop();
        ChessGameFrameStore1.fatherFrame = menuFrame;
        ChessGameFrameStore1.main(null);
        new Music_PlayChess();
        //   newGameOptionFrame.setVisible(false);
    }
}