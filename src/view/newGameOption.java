package view;
import javax.swing.*;
import java.awt.*;

public class newGameOption {
    static JFrame fatherFrame;
    public newGameOption(){}
    public static void main(String[] args) {
        JFrame newGameOptionFrame = new JFrame("新游戏");
        newGameOptionFrame.setSize(400, 500);
        newGameOptionFrame.setLocationRelativeTo(null);
        newGameOptionFrame.setResizable(false);
        newGameOptionFrame.setDefaultCloseOperation(3);

        ImagePanel imagePanel = new ImagePanel("images/menu-background.jpeg");
        newGameOptionFrame.add(imagePanel);

        Dimension labelDimensionBig = new Dimension(400, 55);
        Dimension labelDimensionSmall = new Dimension(400, 20);
        Dimension buttonDimensionBig = new Dimension(130, 45);
        Dimension buttonDimensionSmall = new Dimension(150, 10);
        Font labelFont = new Font("标签字体", Font.BOLD, 38);
        Font buttonFont = new Font("按钮字体", Font.BOLD, 18);
        JLabel title = new JLabel("             新游戏         ");
        title.setFont(labelFont);
        title.setForeground(Color.RED);
        title.setPreferredSize(labelDimensionBig);


        JLabel blankBig=new JLabel(" ");
        blankBig.setFont(labelFont);
        blankBig.setPreferredSize(labelDimensionBig);
        JLabel blankSmall01=new JLabel(" ");
        blankSmall01.setFont(labelFont);
        blankSmall01.setPreferredSize(labelDimensionSmall);
        JLabel blankSmall02=new JLabel(" ");
        blankSmall02.setFont(labelFont);
        blankSmall02.setPreferredSize(labelDimensionSmall);
        JLabel blankSmall03=new JLabel(" ");
        blankSmall03.setFont(labelFont);
        blankSmall03.setPreferredSize(labelDimensionSmall);

        JButton singlePlayer = new JButton("单人游戏");
        singlePlayer.setPreferredSize(buttonDimensionBig);
        singlePlayer.setFont(buttonFont);
        JButton doublePlayers = new JButton("双人游戏");
        doublePlayers.setPreferredSize(buttonDimensionBig);
        doublePlayers.setFont(buttonFont);
        JButton goBack = new JButton("返回");
        goBack.setPreferredSize(buttonDimensionBig);
        goBack.setFont(buttonFont);


        imagePanel.add(blankSmall01);
        imagePanel.add(title);
        imagePanel.add(blankBig);
        imagePanel.add(singlePlayer);
        imagePanel.add(blankSmall02);
        imagePanel.add(doublePlayers);
        imagePanel.add(blankSmall03);
        imagePanel.add(goBack);

        startNewGoBackListener signOutNewGameOption = new startNewGoBackListener(newGameOptionFrame);
        goBack.addActionListener(signOutNewGameOption);

        singlePlayerListener startSingleGame = new singlePlayerListener(newGameOptionFrame);
        singlePlayer.addActionListener(startSingleGame);

        doublePlayerListener startDoubleGame = new doublePlayerListener(newGameOptionFrame);
        doublePlayers.addActionListener(startDoubleGame);

        newGameOptionFrame.setVisible(true);
    }
}
