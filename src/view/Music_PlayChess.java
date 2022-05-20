package view;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.URL;
class Music_PlayChess extends Frame{
    URL cb;
    File f = new File("musics/Nightbook.wav"); //引号里面的是音乐文件所在的绝对鹿筋
    AudioClip aau;

    public Music_PlayChess(){
        super();
    }
    public void play() {
        try {
            cb = f.toURL();
            aau = Applet.newAudioClip(cb);
            // TODO Auto-generated method stub
            aau.loop();
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
    public static void main(String args[]) {
        new Music_PlayChess().play();
    }
}