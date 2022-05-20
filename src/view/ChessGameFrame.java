package view;

import controller.GameController;
import model.ChessColor;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
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
    JLabel Label;
//    Chessboard chessboard;
//
//    public void PawnGetBoard(Chessboard chessboard){
//        this.chessboard=chessboard;
//    }
    public ChessGameFrame(int width, int height) {
        setTitle("国际象棋"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;//棋盘只有整个页面的4/5的大小

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        ImageIcon picture=new ImageIcon(".\\images\\背景图片.jpeg");
        addChessboard();
        Label=new JLabel(picture);
//        JLabel label=new JLabel(picture);        //add the picture to a label
        add(Label);
        //add the label to the frame
        setVisible(true);//set the window to visible

        MusicPlayer a=new MusicPlayer("musics/Nightbook.wav");
        a.play();
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
        String pa="images/加载页面2.jpeg";
        String pa2="images/主题3.jpeg";
        chessboard.changeChessBoard(pa,pa2);




        /**
         * 在游戏面板中添加标签
         */
        JLabel statusLabel;
        statusLabel = new JLabel("        黑方回合");
        statusLabel.setSize(160, 48);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 24));
        chessboard.changeLabel(statusLabel);
        add(statusLabel);
        add(new JPanel());

        /**
         * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
         */
        JButton button = new JButton("保存至存档一");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "保存成功！"));
        button.setLocation(HEIGTH-20, HEIGTH / 10 + 20);
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

        JButton Load = new JButton("保存至存档二");
        Load.setLocation(HEIGTH-20, HEIGTH / 10 + 100);
        Load.setSize(160, 48);
        Load.setFont(new Font("Rockwell", Font.BOLD, 16));
        add(Load);

       Load.addActionListener(e -> {
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
        Reset.setLocation(HEIGTH-20, HEIGTH / 10 +180);
        Reset.setSize(160, 48);
        Reset.setFont(new Font("Rockwell", Font.BOLD, 16));
        add(Reset);

        {
            JButton Regret = new JButton("悔棋");
            Regret.setLocation(HEIGTH-20, HEIGTH / 10 + 260);
            Regret.setSize(160, 48);
            Regret.setFont(new Font("Rockwell", Font.BOLD, 16));
            add(Regret);

            Regret.addActionListener(e -> {
                // System.out.println(chessboard.regretChessDataTure);
                chessboard.loadGame(chessboard.regretChessDataTure);
                repaint();
            });
        }

        JButton exit =new JButton("返回菜单");
        exit.setLocation(HEIGTH-20, HEIGTH / 10 + 340);
        exit.setSize(160, 48);
        exit.setFont(new Font("Rockwell", Font.BOLD, 16));
        add(exit);
        exit.addActionListener(e->{
            new Music_PowerOfGame().play();
            new Music_Click();
            new Music_PlayChess().stop();
            newGameOption.fatherFrame = this;
            newGameOption.main(null);
            this.setVisible(false);
        });

        JButton changeBackground=new JButton("更改棋盘");
        changeBackground.setLocation(HEIGTH-20, HEIGTH / 10 + 420);
        changeBackground.setSize(160, 48);
        changeBackground.setFont(new Font("Rockwell", Font.BOLD, 16));
        add(changeBackground);
        changeBackground.addActionListener(a->{
            Object[] optionsBackground={"棋盘1","棋盘2","棋盘3"};
            String BackGroundTheme=(String)JOptionPane.showInputDialog(null,"更换棋盘\n","更换",JOptionPane.PLAIN_MESSAGE,new ImageIcon("xx.png"),optionsBackground,"棋盘");
            if (BackGroundTheme == "棋盘1") {
                String path1="images/加载页面2.jpeg";
                String path2="images/主题3.jpeg";
                chessboard.changeChessBoard(path1,path2);
            }
        });

        JButton changeTheme=new JButton("更改背景");
        //JButton exit =new JButton("返回菜单");
        changeTheme.setLocation(HEIGTH-20, HEIGTH / 10 + 500);
        changeTheme.setSize(160, 48);
        changeTheme.setFont(new Font("Rockwell", Font.BOLD, 16));
        add(changeTheme);
        changeTheme.addActionListener(e->{
            Object[] options={"主题1","主题2","主题3"};
            String theme=(String)JOptionPane.showInputDialog(null,"更换主题\n","更换",JOptionPane.PLAIN_MESSAGE,new ImageIcon("xx.png"),options,"主题");
            if (theme == "主题1") {
                String path="images/背景图片.jpeg";
                changeTheme(path);
            }
            if(theme=="主题2"){
                String path="images/主题4.jpeg";
                changeTheme(path);
            }
            if(theme=="主题3"){
                String path="images/主题5.jpeg";
                changeTheme(path);
            }
        });

//        Object[] options={"骑士","皇后","大象","战车"};
//        int op = JOptionPane.showOptionDialog(null, "请选择升级成：", "升级",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[0]);


//        JButton changeBackground=new JButton("更改背景");
//        changeTheme.setLocation(HEIGTH, HEIGTH / 10 + 450);
//        changeTheme.setSize(160, 48);
//        changeTheme.setFont(new Font("Rockwell", Font.BOLD, 16));
//        add(changeTheme);
    }
    public void changeTheme(String path){
        remove(Label);
        ImageIcon theme1=new ImageIcon(path);
        Image a=theme1.getImage();
        Image dos=a.getScaledInstance(WIDTH,HEIGTH,Image.SCALE_FAST);
        ImageIcon Ture=new ImageIcon(dos);
        Label=new JLabel(Ture);
        Label.setBounds(0,0,getWidth(),getHeight());
        add(Label);
        Label.repaint();
    }
    public void changeBackGround(){

    }

    public static void main(String[] args){
        ChessGameFrame mainFrame = new ChessGameFrame(800, 608);
        mainFrame.setVisible(true);
    }
}
