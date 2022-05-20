package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class settingGoBackListener implements ActionListener {
    private final JFrame settingFrame;
    public settingGoBackListener(JFrame settingFrame){
        this.settingFrame=settingFrame;
    }
    public void actionPerformed(ActionEvent e){
        new Music_PowerOfGame().stop();
        new Music_Click();
        new Music_PowerOfGame().play();
        settingFrame.setVisible(false);
       gameMenu.fatherFrame=settingFrame;
       gameMenu.main(null);

        return;

    }
}
