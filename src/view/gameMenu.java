package view;
import javax.swing.*;
import java.awt.*;

public class gameMenu {


    static JFrame fatherFrame;
    public gameMenu(){
    }

    public static void main(String[] args){

        JFrame menuFrame = new JFrame("游戏主页");
        menuFrame.setSize(400,500);
        menuFrame.setLocationRelativeTo(null);
//        menuFrame.setResizable(false);
//        menuFrame.setDefaultCloseOperation(3);


        ImagePanel imagePanel = new ImagePanel("images/menu-background.jpeg");
        menuFrame.add(imagePanel);

        Dimension labelDimensionBig = new Dimension(400,40);
        Dimension labelDimensionSmall = new Dimension(400,10);
        Dimension buttonDimensionBig = new Dimension(130,35);
        Dimension buttonDimensionSmall = new Dimension(150,10);
        Font labelFont = new Font("标签字体",Font.BOLD,38);
        Font buttonFont = new Font("按钮字体",Font.BOLD,18);
        JLabel title = new JLabel("           国际象棋         ");
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
        JLabel blankSmall06=new JLabel(" ");
        blankSmall06.setFont(labelFont);
        blankSmall06.setPreferredSize(labelDimensionSmall);

        imagePanel.add(blankSmall01);
        imagePanel.add(title);
        imagePanel.add(blankBig);

        JButton startNew = new JButton("新游戏");
        startNew.setPreferredSize(buttonDimensionBig);
        startNew.setFont(buttonFont);
        imagePanel.add(startNew);
        imagePanel.add(blankSmall02);
        JButton loadReserve = new JButton("加载存档一");
        loadReserve.setPreferredSize(buttonDimensionBig);
        loadReserve.setFont(buttonFont);
        imagePanel.add(loadReserve);
        imagePanel.add(blankSmall03);

        JButton loadReserve2 = new JButton("加载存档二");
        loadReserve2.setPreferredSize(buttonDimensionBig);
        loadReserve2.setFont(buttonFont);
        imagePanel.add(loadReserve2);
        imagePanel.add(blankSmall06);

        JButton rank = new JButton("用户排行榜");
        rank.setPreferredSize(buttonDimensionBig);
        rank.setFont(buttonFont);
        imagePanel.add(rank);
        imagePanel.add(blankSmall04);

        JButton individuation =new JButton("设置");
        individuation.setPreferredSize(buttonDimensionBig);
        individuation.setFont(buttonFont);
        imagePanel.add(individuation);
        imagePanel.add(blankSmall05);
        JButton exit = new JButton("退出游戏");
        exit.setPreferredSize(buttonDimensionBig);
        exit.setFont(buttonFont);
        imagePanel.add(exit);

        //以下这两行仅给新游戏按钮做了处理，没有动其他的
        startNewListener signInStartNew = new startNewListener(menuFrame);
        startNew.addActionListener(signInStartNew);

        exitListener exitOut =new exitListener(menuFrame);
        exit.addActionListener(exitOut);

        settingListener enterSetting = new settingListener(menuFrame);
        individuation.addActionListener(enterSetting);

        store2Listener enterStore2=new store2Listener(menuFrame);
        loadReserve2.addActionListener(enterStore2);

        store1Listener enterStore1=new store1Listener(menuFrame);
        loadReserve.addActionListener(enterStore1);
        menuFrame.setVisible(true);
    }
}
