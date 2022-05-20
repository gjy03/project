package controller;

import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.URI;
import java.net.URL;
import javax.swing.JFrame;
class Music_Click extends JFrame{
    File f;
    URI uri;
    URL url;
    Music_Click(){
        try {f = new File("musics/落子声音.wav");
            uri = f.toURI();//解析地址
            url = uri.toURL();
            AudioClip aau;
            aau = Applet.newAudioClip(url);
            aau.play();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
        new controller.Music_Click();
    }


    void Music(){               //注意，java只能播放无损音质，如.wav这种格式
        try {
            f = new File("musics/落子声音.wav"); //绝对路径
            uri = f.toURI();
            url = uri.toURL(); //解析路径
            AudioClip aau;
            aau = Applet.newAudioClip(url);
            aau.loop();  //单曲循环
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}