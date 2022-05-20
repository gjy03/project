package view;

import javax.swing.*;
import java.awt.*;

public class setting {
    static JFrame fatherFrame;
    public setting(){}
    public static void main(String[] args){
        JFrame settingFrame = new JFrame("游戏主页");
        settingFrame.setSize(400,500);
        settingFrame.setLocationRelativeTo(null);
        settingFrame.setResizable(false);
        settingFrame.setDefaultCloseOperation(3);


        ImagePanel imagePanel = new ImagePanel("images/menu-background.jpeg");
        settingFrame.add(imagePanel);

        Dimension labelDimensionBig = new Dimension(400,40);
        Dimension labelDimensionSmall = new Dimension(400,12);
        Dimension buttonDimensionBig = new Dimension(130,40);
        Dimension buttonDimensionSmall = new Dimension(150,10);
        Font labelFont = new Font("标签字体",Font.BOLD,38);
        Font buttonFont = new Font("按钮字体",Font.BOLD,18);
        JLabel title = new JLabel("              设置         ");
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
        JLabel blankSmall04=new JLabel(" ");
        blankSmall04.setFont(labelFont);
        blankSmall04.setPreferredSize(labelDimensionSmall);
        JLabel blankSmall05=new JLabel(" ");
        blankSmall05.setFont(labelFont);
        blankSmall05.setPreferredSize(labelDimensionSmall);

        JButton backgroundSetting =new JButton("背景棋盘1");
        backgroundSetting.setFont(buttonFont);
        backgroundSetting.setPreferredSize(buttonDimensionBig);
        JButton musicSetting = new JButton("背景棋盘2");
        musicSetting.setFont(buttonFont);
        musicSetting.setPreferredSize(buttonDimensionBig);
        JButton goBack =new JButton("返回");
        goBack.setFont(buttonFont);
        goBack.setPreferredSize(buttonDimensionBig);

        imagePanel.add(blankSmall01);
        imagePanel.add(title);
        imagePanel.add(blankBig);
        imagePanel.add(backgroundSetting);
        imagePanel.add(blankSmall02);
        imagePanel.add(musicSetting);
        imagePanel.add(blankSmall03);
        imagePanel.add(goBack);

        settingGoBackListener goBackOut = new settingGoBackListener(settingFrame);
        goBack.addActionListener(goBackOut);
        settingFrame.setVisible(true);
    }
}
