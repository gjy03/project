package model;

import controller.ClickController;
import view.Chessboard;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PawnChessComponent extends ChessComponent{
    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;
    private Image pawnImage;
   // private final ClickController clickController = new ClickController(new Chessboard(3,3));

    public List<String> copyRegretChessDataTure;
    public List<String> copyRegretChessDataHelp;

    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
        }
    }

    private void initiateRookImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                pawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  List<String> getRegretChessDataTure(){
        String fileName = "regretChessDataTure.txt";
        List<String> help=new ArrayList<>();
        Stream<String> lines = null;
        try {
            lines = Files.lines(Paths.get(fileName));

        }catch(FileNotFoundException ex){

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        String[] m = {""};
        lines.forEach(ele -> {
            //System.out.println(ele);
            m[0] = m[0] +ele;
        });
        help.add(m[0].substring(1,9));
        help.add(m[0].substring(11,19));
        help.add(m[0].substring(21,29));
        help.add(m[0].substring(31,39));
        help.add(m[0].substring(41,49));
        help.add(m[0].substring(51,59));
        help.add(m[0].substring(61,69));
        help.add(m[0].substring(71,79));
        help.add(m[0].substring(81,82));
        copyRegretChessDataTure=help;
        //System.out.println(copyRegretChessDataTure+"_________我来啦___________+++++++++++++++");
        return help;
    }



    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateRookImage(color);

        if(chessColor==ChessColor.BLACK){
            this.name="P";
        }else {
            this.name="p";
        }
//        getRegretChessDataTure();
    }


    /**
     * 兵走棋规则
     */
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
       // System.out.println(copyRegretChessDataTure+"_________合法吗____________++++++__________-__________");

        Chessboard chessboard = new Chessboard(8,8);
        ChessboardPoint source = getChessboardPoint();
        int x1 = source.getX();int y1 = source.getY();
        int x2 = destination.getX();int y2 = destination.getY();
        if (chessComponents[x1][y1].getChessColor()==ChessColor.WHITE){
            if (x1==6){
                if (x2==4&&y2==y1&&chessComponents[x2][y2] instanceof EmptySlotComponent &&
                        chessComponents[5][y2] instanceof EmptySlotComponent){
                    return true;
                }
            }if (x1!=0){
                if (x2==x1-1&&y2==y1&&chessComponents[x2][y2] instanceof EmptySlotComponent){
                    return true;
                }if (x2==x1-1&&y2==y1-1&&chessComponents[x2][y2].getChessColor()==ChessColor.BLACK){
                    return true;
                }if (x2==x1-1&&y2==y1+1&&chessComponents[x2][y2].getChessColor()==ChessColor.BLACK){
                    return true;
                }
            }if (x1==3){
                if (x2 == 2 && y2 == y1 - 1 && chessComponents[x1][y2].getName() == "P" &&
                        chessComponents[x2][y2] instanceof EmptySlotComponent &&
                        (this.getRegretChessDataTure().get(1)).charAt(y2) == 'P') {
                    //chessComponents[3][y2] = new EmptySlotComponent(new ChessboardPoint(3,y2),new Point(3,y2),new ClickController(chessboard),1);
                   // this.remove(chessComponents[3][y2]);
                    //add(chessComponents[3][y2] = new EmptySlotComponent(chessComponents[3][y2].getChessboardPoint(), chessComponents[3][y2].getLocation(), clickController, 60));
                    return true;
                }if (x2 == 2 && y2 == y1 + 1 && chessComponents[x1][y2].getName() == "P" &&
                        chessComponents[x2][y2] instanceof EmptySlotComponent &&
                        (this.getRegretChessDataTure().get(1)).charAt(y2) == 'P') {
                    //this.remove(chessComponents[3][y2]);
                    //add(chessComponents[3][y2] = new EmptySlotComponent(chessComponents[3][y2].getChessboardPoint(), chessComponents[3][y2].getLocation(), clickController, 60));
                    return true;
                }
            }
        }else if (chessComponents[x1][y1].getChessColor()==ChessColor.BLACK){
            if (x1==1){
                if (x2==3&&y2==y1&&chessComponents[x2][y2] instanceof EmptySlotComponent &&
                        chessComponents[x2-1][y2] instanceof EmptySlotComponent){
                    return true;
                }
            }if (x1!=7){
                if (x2==x1+1&&y2==y1&&chessComponents[x2][y2] instanceof EmptySlotComponent){
                    return true;
                }if (x2==x1+1&&y2==y1-1&&chessComponents[x2][y2].getChessColor()==ChessColor.WHITE){
                    return true;
                }if (x2==x1+1&&y2==y1+1&&chessComponents[x2][y2].getChessColor()==ChessColor.WHITE){
                    return true;
                }
            }if (x1==4){
                if (x2 == 5 && y2 == y1 - 1 && chessComponents[4][y2].getChessColor() == ChessColor.WHITE &&
                        chessComponents[x2][y2] instanceof EmptySlotComponent &&
                        (this.getRegretChessDataTure().get(6)).charAt(y2) == 'p') {
                    //this.remove(chessComponents[4][y2]);
                    //add(chessComponents[4][y2] = new EmptySlotComponent(chessComponents[4][y2].getChessboardPoint(), chessComponents[4][y2].getLocation(), clickController,60));
                    return true;
                }if (x2 == 5 && y2 == y1 + 1 && chessComponents[4][y2].getChessColor() == ChessColor.WHITE &&
                        chessComponents[x2][y2] instanceof EmptySlotComponent &&
                        (this.getRegretChessDataTure().get(6)).charAt(y2) == 'p') {
                    //this.remove(chessComponents[4][y2]);
                    //this.remove(chessComponents[4][y2]);
                    //add(chessComponents[4][y2] = new EmptySlotComponent(chessComponents[4][y2].getChessboardPoint(), chessComponents[4][y2].getLocation(), clickController, 60));
                    return true;
                }
            }
        }else {
            return false;
        }

        return false;
    }
/*
    @Override
    public List<ChessboardPoint> canMoveTo() {

    }
*/
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
