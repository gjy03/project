package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class settingListener implements ActionListener {
    private final JFrame menuFrame;
    public settingListener(JFrame menuFrame){
        this.menuFrame=menuFrame;
    }
    public void actionPerformed(ActionEvent e){
        new Music_Click();
        menuFrame.setVisible(false);
        setting.fatherFrame=menuFrame;
        setting.main(null);

        return;

    }
}
