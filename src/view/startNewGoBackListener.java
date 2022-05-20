package view;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class startNewGoBackListener implements ActionListener {
    private final JFrame newGameOptionFrame;
    public startNewGoBackListener(JFrame newGameOptionFrame){
        this.newGameOptionFrame=newGameOptionFrame;
    }
    public void actionPerformed(ActionEvent e){
        new Music_PowerOfGame().stop();
        new Music_Click();
        new Music_PowerOfGame().play();
        newGameOptionFrame.setVisible(false);
        gameMenu.fatherFrame = newGameOptionFrame;
        gameMenu.main(null);
        return;
    }

}
