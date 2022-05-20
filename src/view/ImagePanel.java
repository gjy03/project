package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Panel是窗口里的一个面板，而ImagePanel就是我自己写的Panel。
// 为了在显示面板的时候可以顺便带上一张背景图片，我写一个新的ImagePanel类。它继承自已有的JPanel，修改了它绘图时的操作。
class ImagePanel extends JPanel {

    // 用File类型的变量表达图片的路径。
    // swing不能接受字符串作为路径，只能接受File类的变量。
    private File backgroundImageFile;

    // 设置背景图片
    public void setBackgroundImageFile(String fileStr) {
        backgroundImageFile = new File(fileStr);
    }


    public ImagePanel(String backgroundImageFileStr) {
        backgroundImageFile = new File(backgroundImageFileStr);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        // 调用父类构造器，在屏幕展示出所有添加进来的元素
        super.paintComponent(graphics);
        // 接下来的操作：添加一张背景图片
        // TODO：为什么要try catch?
        BufferedImage image = null;
        try {
            image = ImageIO.read(backgroundImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 在画出这个panel(面板)时，把背景图片也画出来。
        // 注意，如果想要背景图片在窗口拉伸时随之拉伸，要把图片的宽度和高度设置为this.getWidth(), this.getHeight()。
        graphics.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}