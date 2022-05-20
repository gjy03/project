package view;
import javax.swing.*;
import java.awt.event.WindowEvent;

public class Login {
    public class login{}

    public static void main(String[] args) {
        JFrame loginFrame = new JFrame("游戏加载中……");
        loginFrame.setSize(400,280);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setResizable(false);
        ImagePanel imagePanel = new ImagePanel("images/加载页面2.jpeg");
        loginFrame.add(imagePanel);
        loginFrame.setVisible(true);
        try {
            Thread.currentThread().sleep(1800);
        }
        catch(Exception e){
        }

        loginFrame.dispatchEvent( new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
        gameMenu.fatherFrame = loginFrame;
        gameMenu.main(null);
        new Music_PowerOfGame().play();
    }
}
