package com.rightmove.viewtut;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CustomCircle myView;
    int angle=264,hrAngle=264;
    int i=-6;
    int hrTime=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView =  findViewById(R.id.cus_cir);

        RepeatColor rc=new RepeatColor();
        Thread thread=new Thread(rc);thread.start();

    }

    class RepeatColor implements Runnable{

        @Override
        public void run() {
            while(true){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Random rnd = new Random();
                        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                        myView.setCircle_color(color);
                        if(angle>360){angle=0;}
                        if(angle==270){i=0;hrAngle+=6;;hrTime++;if(hrTime>60)hrTime=0;myView.setValuesOnHr(hrAngle,hrTime);
                        }
                        myView.setValuesOn(angle,i);
                        angle+=6;i=i+1;
                    }
                });
                try {
                    Thread.sleep(1000);
                }catch (Exception e){e.printStackTrace();}
            }
        }
    }


}
