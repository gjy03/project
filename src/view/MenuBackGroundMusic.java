package view;


import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.URI;
import java.net.URL;
import javax.swing.JFrame;
class MusicQuanYou extends JFrame{
    File f;
    URI uri;
    URL url;
    MusicQuanYou(){
        try {f = new File("musics/Nightbook.wav");
            uri = f.toURI();//解析地址
            url = uri.toURL();
            AudioClip aau;
            aau = Applet.newAudioClip(url);
            aau.loop();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    void MusicStop(){

    }



    public static void main(String args[]) {
        new MusicQuanYou();

    }

    void MusicQuanYou(){               //注意，java只能播放无损音质，如.wav这种格式
        try {
            f = new File("musics/Ludovico Einaudi - Nightbook 2.mp3"); //绝对路径
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