package view;

import javax.swing.*;

import controller.GameController;
import model.ChessColor;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
public class ChessGameFrameStore1 extends JFrame {

    static JFrame fatherFrame;
    //    public final Dimension FRAME_SIZE ;
    private List<String> chessData=new ArrayList<>();
    private List<String> regretChessData;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private ChessColor currentColor;
    Image image;


    public ChessGameFrameStore1(int width, int height) {
        new Music_PlayChess().play();
        setTitle("国际象棋"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;//棋盘只有整个页面的4/5的大小

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        addChessboard();

        ImageIcon picture=new ImageIcon(".\\images\\背景图片.jpeg");
        JLabel label=new JLabel(picture);        //add the picture to a label
        add(label);                              //add the label to the frame
        setVisible(false);                    //set the window to visible

    }


    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {//棋盘的初始化
        Chessboard chessboard ;
        chessboard=new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        add(chessboard);

        JLabel statusLabel;
        statusLabel = new JLabel("        黑方回合");
        statusLabel.setSize(160, 48);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 24));
        chessboard.changeLabel(statusLabel);
        add(statusLabel);

        String fileName = "ChessData.txt";
        Stream<String> lines = null;
        try {
            lines = Files.lines(Paths.get(fileName));

        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(this, "读取文件有误");
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "载入信息有误！");
            ex.printStackTrace();
        }
        String[] m = {""};
        lines.forEach(ele -> {
            System.out.println(ele);
            m[0] = m[0] +ele;
        });
        if(m[0].length()!=83){
            JOptionPane.showMessageDialog(this, "棋盘信息有误！");
        }
        try {
            chessData.add(m[0].substring(1, 9));
            chessData.add(m[0].substring(11, 19));
            chessData.add(m[0].substring(21, 29));
            chessData.add(m[0].substring(31, 39));
            chessData.add(m[0].substring(41, 49));
            chessData.add(m[0].substring(51, 59));
            chessData.add(m[0].substring(61, 69));
            chessData.add(m[0].substring(71, 79));
            chessData.add(m[0].substring(81, 82));
        }catch (IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(this, "棋盘信息有误，请加载完毕后自动退出！");
        }
        if(m[0].charAt(81)!='b'&&m[0].charAt(81)!='w'){
            JOptionPane.showMessageDialog(this, "缺少下一步行棋方！");
        }

        chessboard.initializeChess();
        chessboard.loadGame(chessData);
        repaint();


        JButton button = new JButton("保存至存档一");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "保存成功！"));
        button.setLocation(HEIGTH, HEIGTH / 10 + 86);
        button.setSize(160, 48);
        button.setFont(new Font("Rockwell", Font.BOLD, 16));
        add(button);
        button.addActionListener(e -> {
            chessData= chessboard.StoreGame();
            File file = new File("ChessData.txt");
            try {
                FileOutputStream fos1=new FileOutputStream(file);
                OutputStreamWriter dos1=new OutputStreamWriter(fos1);
                dos1.write(String.valueOf(chessData));
                dos1.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        JButton button2 = new JButton("保存至存档二");
        button2.addActionListener((e) -> JOptionPane.showMessageDialog(this, "保存成功！"));
        button2.setLocation(HEIGTH, HEIGTH / 10 + 152);
        button2.setSize(160, 48);
        button2.setFont(new Font("Rockwell", Font.BOLD, 16));
        add(button2);
        button2.addActionListener(e -> {
            chessData= chessboard.StoreGame();
            File file = new File("ChessData2.txt");
            try {
                FileOutputStream fos1=new FileOutputStream(file);
                OutputStreamWriter dos1=new OutputStreamWriter(fos1);
                dos1.write(String.valueOf(chessData));
                dos1.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        JButton Reset = new JButton("重置");
        Reset.addActionListener((e) -> {
            int confirm=JOptionPane.showConfirmDialog(null,"是否重置","a",JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_NO_OPTION){
                chessboard.setCurrentColor(ChessColor.BLACK);
                chessboard.getChessComponents();
                //add(chessboard);
                chessboard.initializeChess();
                repaint();
            }
        });
        Reset.setLocation(HEIGTH, HEIGTH / 10 +218);
        Reset.setSize(160, 48);
        Reset.setFont(new Font("Rockwell", Font.BOLD, 16));
        add(Reset);

        {
            JButton Regret = new JButton("悔棋");
            Regret.setLocation(HEIGTH, HEIGTH / 10 + 284);
            Regret.setSize(160, 48);
            Regret.setFont(new Font("Rockwell", Font.BOLD, 16));
            add(Regret);

            Regret.addActionListener(e -> {
                System.out.println(chessboard.regretChessDataTure);
                chessboard.loadGame(chessboard.regretChessDataTure);
                repaint();
            });

            JButton exit = new JButton("返回菜单");
            exit.setLocation(HEIGTH, HEIGTH / 10 + 348);
            exit.setSize(160, 48);
            exit.setFont(new Font("Rockwell", Font.BOLD, 16));
            add(exit);
            exit.addActionListener(e -> {
                new Music_PowerOfGame().play();
                new Music_Click();
                new Music_PlayChess().stop();
                newGameOption.fatherFrame = this;
                newGameOption.main(null);
                this.setVisible(false);
            });
        }
    }

    public static void main(String[] args){
        ChessGameFrameStore1 mainFrame = new ChessGameFrameStore1(800, 608);
        mainFrame.setVisible(true);
    }
}

