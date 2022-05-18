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
import javax.imageio.ImageIO;
import view.ChessboardPoint;

public class QueenChessComponent extends ChessComponent {
    private static Image QUEEN_WHITE;
    private static Image QUEEN_BLACK;
    private Image queenImage;

    public void loadResource() throws IOException {
        if (QUEEN_WHITE == null) {
            QUEEN_WHITE = ImageIO.read(new File("./images/queen-white.png"));
        }

        if (QUEEN_BLACK == null) {
            QUEEN_BLACK = ImageIO.read(new File("./images/queen-black.png"));
        }

    }

    private void initiateRookImage(ChessColor color) {
        try {
            this.loadResource();
            if (color == ChessColor.WHITE) {
                this.queenImage = QUEEN_WHITE;
            } else if (color == ChessColor.BLACK) {
                this.queenImage = QUEEN_BLACK;
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public QueenChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        this.initiateRookImage(color);
        if (this.chessColor == ChessColor.BLACK) {
            this.name = "Q";
        } else {
            this.name = "q";
        }

    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = this.getChessboardPoint();
        int x1 = source.getX();
        int y1 = source.getY();
        int x2 = destination.getX();
        int y2 = destination.getY();
        int i;
        if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            if (x1 < x2 && y1 < y2) {
                for(i = 1; i < x2 - x1; ++i) {
                    if (!(chessComponents[x1 + i][y1 + i] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            } else if (x1 > x2 && y1 > y2) {
                for(i = 1; i < x1 - x2; ++i) {
                    if (!(chessComponents[x2 + i][y2 + i] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            } else if (x1 < x2 && y1 > y2) {
                for(i = 1; i < x2 - x1; ++i) {
                    if (!(chessComponents[x2 - i][y2 + i] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            } else if (x1 > x2 && y1 < y2) {
                for(i = 1; i < x1 - x2; ++i) {
                    if (!(chessComponents[x2 + i][y2 - i] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            }
        } else {
            int row;
            if (source.getX() == destination.getX()) {
                i = source.getX();

                for(row = Math.min(source.getY(), destination.getY()) + 1; row < Math.max(source.getY(), destination.getY()); ++row) {
                    if (!(chessComponents[i][row] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            } else {
                if (source.getY() != destination.getY()) {
                    return false;
                }

                i = source.getY();

                for(row = Math.min(source.getX(), destination.getX()) + 1; row < Math.max(source.getX(), destination.getX()); ++row) {
                    if (!(chessComponents[row][i] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.queenImage, 0, 0, this.getWidth(), this.getHeight(), this);
        g.setColor(Color.BLACK);
        if (this.isSelected()) {
            g.setColor(Color.RED);
            g.drawOval(0, 0, this.getWidth(), this.getHeight());
        }

    }
}
