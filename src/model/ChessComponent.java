package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类是一个抽象类，主要表示8*8棋盘上每个格子的棋子情况，当前有两个子类继承它，分别是EmptySlotComponent(空棋子)和RookChessComponent(车)。
 */
public abstract class ChessComponent extends JComponent {

    /**
     * CHESSGRID_SIZE: 主要用于确定每个棋子在页面中显示的大小。
     * <br>
     * 在这个设计中，每个棋子的图案是用图片画出来的（由于国际象棋那个棋子手动画太难了）
     * <br>
     * 因此每个棋子占用的形状是一个正方形，大小是50*50
     */

    private static final Dimension CHESSGRID_SIZE = new Dimension(1080 / 4 * 3 / 8, 1080 / 4 * 3 / 8);
    private Color[] BACKGROUND_COLORS = {Color.WHITE, Color.BLACK};
//    private static final Color[] BACKGROUND_COLORS1 = {Color.GREEN, Color.YELLOW};
//    private static final Color[] BACKGROUND_COLORSTheme2 = {Color.GREEN, Color.BLACK};

    private Image image1;
    private Image image2;

    /**
     * handle click event
     */
    private ClickController clickController;
    protected ChessComponent[][] chessComponents = new  ChessComponent[8][8];

    public void setChessComponents(ChessComponent[][] chessComponents) {
        this.chessComponents = chessComponents;
    }

    /**
     * chessboardPoint: 表示8*8棋盘中，当前棋子在棋格对应的位置，如(0, 0), (1, 0), (0, 7),(7, 7)等等
     * <br>
     * chessColor: 表示这个棋子的颜色，有白色，黑色，无色三种
     * <br>
     * selected: 表示这个棋子是否被选中
     */
    private ChessboardPoint chessboardPoint;
    protected final ChessColor chessColor;
    private boolean selected;
    private boolean canBeMoved;
    private boolean entered;
    protected String name;
    private boolean attacked;
    private boolean jiangjun;

    public void setBACKGROUND_COLORS(Color[] BACKGROUND_COLORS) {
        this.BACKGROUND_COLORS = BACKGROUND_COLORS;
    }

    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public boolean isJiangjun() {
        return jiangjun;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public void setJiangjun(boolean jiangjun) {
        this.jiangjun = jiangjun;
    }

    protected ChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLocation(location);
        setSize(size, size);
        this.chessboardPoint = chessboardPoint;
        this.chessColor = chessColor;
        this.selected = false;
        this.canBeMoved=false;
        this.entered=false;
        this.clickController = clickController;
        this.jiangjun=false;
    }

    public ChessboardPoint getChessboardPoint() {
        return chessboardPoint;
    }

    public void setChessboardPoint(ChessboardPoint chessboardPoint) {
        this.chessboardPoint = chessboardPoint;
    }

    public ChessColor getChessColor() {
        return chessColor;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * @param another 主要用于和另外一个棋子交换位置
     *                <br>
     *                调用时机是在移动棋子的时候，将操控的棋子和对应的空位置棋子(EmptySlotComponent)做交换
     */
    public void swapLocation(ChessComponent another) {
        ChessboardPoint chessboardPoint1 = getChessboardPoint(), chessboardPoint2 = another.getChessboardPoint();
        Point point1 = getLocation(), point2 = another.getLocation();
        setChessboardPoint(chessboardPoint2);
        setLocation(point2);
        another.setChessboardPoint(chessboardPoint1);
        another.setLocation(point1);
        repaint();
//        System.out.println(BACKGROUND_COLORS[0]);
//        System.out.println(BACKGROUND_COLORS[1]);
    }

    /**
     * @param e 响应鼠标监听事件
     *          <br>
     *          当接收到鼠标动作的时候，这个方法就会自动被调用，调用所有监听者的onClick方法，处理棋子的选中，移动等等行为。
     */
    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            //System.out.printf("Click [%d,%d]\n", chessboardPoint.getX(), chessboardPoint.getY());
            clickController.onClick(this);
        }

        if (e.getID() == MouseEvent.MOUSE_ENTERED){
            this.entered=true;
            this.repaint();
        }
        if (e.getID() == MouseEvent.MOUSE_EXITED){
            this.entered=false;
            this.repaint();
        }
    }//输出选中的棋子的坐标
    //可以在这个地方写显示canMoveTo的方法

    /**
     * @param chessboard  棋盘
     * @param destination 目标位置，如(0, 0), (0, 7)等等
     * @return this棋子对象的移动规则和当前位置(chessboardPoint)能否到达目标位置
     * <br>
     * 这个方法主要是检查移动的合法性，如果合法就返回true，反之是false
     */
    public abstract boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination);

    /**
     * 这个方法主要用于加载一些特定资源，如棋子图片等等。
     *
     * @throws IOException 如果一些资源找不到(如棋子图片路径错误)，就会抛出异常
     */
    public abstract void loadResource() throws IOException;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Color squareColor = BACKGROUND_COLORS[(chessboardPoint.getX() + chessboardPoint.getY()) % 2];
        g.setColor(squareColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
//        System.out.println(BACKGROUND_COLORS[0]);
//        System.out.println(BACKGROUND_COLORS[1]);
        repaint();
//        if((chessboardPoint.getX()+chessboardPoint.getY())%2==1) {
//            g.drawImage(image1, 0, 0, this.getWidth(), this.getWidth(), null);
//        }else {
//            g.drawImage(image2,0,0,this.getWidth(),this.getWidth(),null);
//        }




        if (canBeMoved){
            g.setColor(Color.GRAY);
            g.drawRect(getWidth()/8,getHeight()/8,getWidth()*3/4,getHeight()*3/4);
        }

        if (entered){
            g.setColor(Color.darkGray);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }else {
//            g.setColor(squareColor);
//            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        if(isAttacked()){// Highlights the model if selected.
            g.setColor(Color.green);
            g.fillOval(21, 21, getWidth()/3 , getHeight()/3);
        }
        if (isJiangjun()){
            g.setColor(Color.RED);
            g.fillRect(20,20,getWidth()/3,getHeight()/3);
        }

    }

    public List<ChessboardPoint> getPointsCanMoveTo(ChessComponent[][] chessboard) {
        List<ChessboardPoint> points = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessboardPoint destination = new ChessboardPoint(i,j);
                if(canMoveTo(chessboard,destination)==true&&
                        chessboard[i][j].getChessColor()!= chessboard[chessboardPoint.getX()][chessboardPoint.getY()].chessColor) {
                    points.add(new ChessboardPoint(i,j));
                }
            }
        }
        return points;
    }

    public List<ChessboardPoint> jiangjunGJY(ChessComponent[][] chessboard){
        List<ChessboardPoint> point = new ArrayList<>();
        int x0=0;int y0=0;int judge1=-1;
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (chessComponents[i][j].getChessColor()!=chessboard[chessboardPoint.getX()][chessboardPoint.getY()].chessColor&&
                        chessComponents[i][j].getChessColor()!=null&&
                        (chessComponents[i][j].getName()=="K"||chessComponents[i][j].getName()=="k")){
                    x0=i;y0=j;}
            }
        }
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (chessComponents[i][j].getChessColor()==chessboard[chessboardPoint.getX()][chessboardPoint.getY()].chessColor){
                    List<ChessboardPoint> points = chessComponents[i][j].getPointsCanMoveTo(chessboard);
                    if (points.contains(new ChessboardPoint(x0,y0))){
                        judge1=1;
                    }
                }
            }
        }
        if (judge1==1){
            point.add(new ChessboardPoint(x0,y0));
        }
        return point;
    }




}
