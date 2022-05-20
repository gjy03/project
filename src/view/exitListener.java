package view;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


class exitListener implements ActionListener{
    private final JFrame menuFrame;
    public exitListener(JFrame menuFrame){
        this.menuFrame=menuFrame;
    }
    public void actionPerformed(ActionEvent e){
        new  Music_Click();
        menuFrame.dispatchEvent(new WindowEvent(menuFrame, WindowEvent.WINDOW_CLOSING));
    }
}
