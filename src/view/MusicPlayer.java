package view;



import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 根据jdk底层API实现的音乐播放器
 *
 * @功能
 * 1、只支持wav，且只能播放一首
 * 2、可循环播放，随时停止（并非暂停）
 * 3、支持一定范围内的音量调节
 *
 * 参考博客:
 * https://blog.csdn.net/qq_21907023/article/details/96174077
 * https://blog.csdn.net/fuckcdn/article/details/83725725
 *
 * @author passerbyYSQ
 * @create 2020年7月20日 下午4:05:50
 */
public class MusicPlayer {
    //	private AudioInputStream audioIn;
//	private SourceDataLine sourceDataLine;
    // wav文件的路径
    private File file;
    // 是否循环播放
    private volatile boolean isLoop = false;
    // 是否正在播放
    private volatile boolean isPlaying;
    // FloatControl.Type.MASTER_GAIN的值(可用于调节音量)
    private float newVolumn = 7;

    private PlayThread playThread;

    public MusicPlayer(String srcPath) {
        file = new File(srcPath);
    }

    /**
     * 播放音乐
     */
    public void play() {
        if (playThread == null) {
            playThread = new PlayThread();
            playThread.start();
        }
    }

    /**
     * 结束音乐（并非暂停）
     */
    public void over() {
        isPlaying = false;
        if (playThread != null) {
            playThread = null;
        }
    }

    /**
     * 设置循环播放
     * @param isLoop
     * @return	返回当前对象
     */
    public MusicPlayer setLoop(boolean isLoop) {
        this.isLoop = isLoop;
        return this;
    }

    /**
     * -80.0~6.0206测试,越小音量越小
     * @param newVolumn
     * @return	返回当前对象
     */

    /**
     * 异步播放线程
     */
    private class PlayThread extends Thread {

        @Override
        public void run() {
            isPlaying = true;
            do {

                SourceDataLine sourceDataLine = null;
                BufferedInputStream bufIn = null;
                AudioInputStream audioIn = null;
                try {
                    bufIn = new BufferedInputStream(new FileInputStream(file));
                    audioIn = AudioSystem.getAudioInputStream(bufIn); // 可直接传入file

                    AudioFormat format = audioIn.getFormat();
                    sourceDataLine = AudioSystem.getSourceDataLine(format);
                    sourceDataLine.open();
                    // 必须open之后
                    if (newVolumn != 7) {
                        FloatControl control = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
                        control.setValue(newVolumn);
                    }

                    sourceDataLine.start();
                    byte[] buf = new byte[512];
                    int len = -1;
                    while (isPlaying && (len = audioIn.read(buf)) != -1) {
                        sourceDataLine.write(buf, 0, len);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    if (sourceDataLine != null) {
                        sourceDataLine.drain();
                        sourceDataLine.close();
                    }
                    try {
                        if (bufIn != null) {
                            bufIn.close();
                        }
                        if (audioIn != null) {
                            audioIn.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } while (isPlaying && isLoop);
        }
    }
    public void main(String[] args){
        //MusicPlayer A=new MusicPlayer();
       // new MusicPlayer("musics/Nightbook.wav").over();
        MusicPlayer a=new MusicPlayer("musics/Nightbook.wav");
        //System.out.println("+++++++++++++++++++++++++=");
        a.over();

    }
}

