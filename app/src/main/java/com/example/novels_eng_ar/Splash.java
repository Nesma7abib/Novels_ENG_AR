package com.example.novels_eng_ar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread thread=new Thread(){
            public void run(){
                try {
                    sleep(1000);
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();


                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        };
         thread.start();



    }
}
