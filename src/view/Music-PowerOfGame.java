package view;

import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
class Music_PowerOfGame extends Frame{
    URL cb;
    File f = new File("musics/Thegameofpower.wav"); //引号里面的是音乐文件所在的绝对鹿筋
    AudioClip aau;

    public Music_PowerOfGame(){
        super();
    }
    public void play() {
        try {
            cb = f.toURL();
            aau = Applet.newAudioClip(cb);
            // TODO Auto-generated method stub
            aau.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //public static void main(String[] args){new Music_PowerOfGame().play();}

    public void stop() {
        // TODO Auto-generated method stub
        try {
            cb = f.toURL();
            aau = Applet.newAudioClip(cb);
            aau.stop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}