package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessMainframe extends JFrame implements ActionListener, MouseListener,Runnable {//后面加的一坨可以使之听从鼠标的响应
    //下方为自动生成的方法，暂时置之不理
    ChessManBehaviour play[]=new ChessManBehaviour[32];//象棋中有三十二个棋子，所以此处定义的数组个数为三十二
    JLabel image;//定义一个对象显示期盼
    Container con;//定义一个面板显示对象；
    JToolBar jmain;//定义一个工具栏显示相应的按钮；
    JButton anew,repent,exit;//定义按钮，分别是新游戏，悔棋，退出
    JLabel text;//定义文本提示框
    //接下来开始写构造函数；
    public  ChessMainframe(String title){//传入title是为了显示窗口的标题
        con=this.getContentPane();//得到窗口的内容面板，用来显示窗口的具体内容
        con.setLayout(null);//定义窗口的内容布局，采用绝对布局（括号里的），使得布局位置不会因为窗口的位置变化而变化
        this.setTitle(title);//设置标题
        jmain=new JToolBar();//新建工具栏显示按钮
        text=new JLabel("欢迎来到国际象棋");//定义一个文本显示具体的提示内容
        text.setToolTipText("信息提示");//当鼠标挪到text使会显示的文本
        //接着要定义三个按钮的功能
        anew=new JButton("新游戏");//本身显示在文本框里的字符
        anew.setToolTipText("重新开始新的一局");//当鼠标靠近时显示的东东
        exit=new JButton("退出");
        exit.setToolTipText("退出国际象棋系统");
        repent=new JButton("悔棋");
        repent.setToolTipText("返回到上次走棋的位置");
        //接下来要把主键添加到工具栏
        jmain.setLayout(new GridLayout(0,4));//工具栏采用三格布局
        jmain.add(anew);//工具栏把按钮添加进来
        jmain.add(repent);
        jmain.add(exit);
        jmain.add(text);
        //设置工具栏的大小
        jmain.setBounds(0,0,558,30);//起始坐标分别是0，0，工具栏的高度为30像素，宽度为558像素；
        con.add(jmain);//把工具栏添加到控制面板里面去
        drawChessMain();
        for(int i=0;i<2;i++){
            con.add(play[i]);
            play[i].addMouseListener(this);
        }
        //添加背景图片
        //为什么先布局棋子，再添加背景图片？----按照程序的设计，最先添加的永远再最上方
        con.add(image=new JLabel(new ImageIcon("image/main.gif")));
        image.setBounds(0,30,558,620);//因为有工具栏，所以y从30开始
        image.addMouseListener(this);//棋子要再1棋盘上移动，所以...
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width/2-288,screenSize.height/2-350);//这行和上一行使得位置居中
        this.setIconImage(new ImageIcon("image/红将，gif").getImage());
        this.setResizable(false);//窗体不能改变大小，给它一个false；
        this.setSize(558,676);//注意要加上工具栏和标题栏的高度
        this.setVisible(true);

    }
    //为了不使得代码过于冗长，把画棋盘和画棋子的过程抽取出来
    //画棋盘
    public void drawChessMain(){
        //定义两个变量，用于循环
        int i,k;
        Icon in=null;//显示图像
        in=new ImageIcon("image/黑车.gif");
        for(i=0,k=24;i<2;i++,k+=456){//一共有两个黑色的车，两个黑色的车的坐标如何定位？
            play[i]=new ChessManBehaviour(in);
            play[i].setBounds(k,56,55,55);
            play[i].setName("车1");//为了区分，所有的黑方都以1为后缀
        }
        //依照此法，即可完成所有的棋子的初始位置的放置


    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void run() {

    }
}
