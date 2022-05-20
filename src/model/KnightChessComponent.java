//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

import controller.ClickController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import view.ChessboardPoint;

public class KnightChessComponent extends ChessComponent {
    List<ChessboardPoint> arrayList=new LinkedList<>();
    private static Image KNIGHT_WHITE;
    private static Image KNIGHT_BLACK;
    private Image rookImage;

    public void loadResource() throws IOException {
        if (KNIGHT_WHITE == null) {
            KNIGHT_WHITE = ImageIO.read(new File("./images/knight-white.png"));
        }

        if (KNIGHT_BLACK == null) {
            KNIGHT_BLACK = ImageIO.read(new File("./images/knight-black.png"));
        }

    }

    private void initiateRookImage(ChessColor color) {
        try {
            this.loadResource();
            if (color == ChessColor.WHITE) {
                this.rookImage = KNIGHT_WHITE;
            } else if (color == ChessColor.BLACK) {
                this.rookImage = KNIGHT_BLACK;
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        this.initiateRookImage(color);
        if (this.chessColor == ChessColor.BLACK) {
            this.name = "N";
        } else {
            this.name = "n";
        }

    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = this.getChessboardPoint();
        int dx = Math.abs(source.getX() - destination.getX());
        int dy = Math.abs(source.getY() - destination.getY());
        return dx == 1 && dy == 2 || dx == 2 && dy == 1;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.rookImage, 0, 0, this.getWidth(), this.getHeight(), this);
        g.setColor(Color.BLACK);
        if (this.isSelected()) {
            g.setColor(Color.RED);
            g.drawOval(0, 0, this.getWidth(), this.getHeight());
        }

    }
}
