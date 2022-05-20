package view;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class startNewListener implements ActionListener {
    private final JFrame menuFrame;

    public startNewListener(JFrame menuFrame){
        this.menuFrame=menuFrame;
    }

    public void actionPerformed(ActionEvent e){
        new Music_Click();
        menuFrame.setVisible(false);
        newGameOption.fatherFrame = menuFrame;
        newGameOption.main(null);
        return;
    }

}
