package view;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class A {
    private int lin;
    private int curSec;
    public A(int lin)throws InterruptedException{
        this.lin = lin;
        this.curSec = lin;
        System.out.println("最后倒计时：" + lin + "秒");
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("倒计时:" + --curSec + "秒");
            }
        },0,1000);
        TimeUnit.SECONDS.sleep(lin);
        t.cancel();
        System.out.println("Game over!!!");
    }

    public static void main(String[] args) throws InterruptedException{
        new A(20);
    }
}