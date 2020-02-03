package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class home_page extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        requestWindowFeature ( Window.FEATURE_NO_TITLE );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView ( R.layout.home_page );
        progressBar =(ProgressBar) findViewById ( R.id.progresbarID );

        Thread thread= new Thread ( new Runnable () {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        } );
        thread.start ();
    }

    public void doWork() {
        for(progres=20; progres<=100;progres=progres+20){

            try {
                Thread.sleep ( 1000 );
                progressBar.setProgress ( progres );
            } catch (InterruptedException e) {
                e.printStackTrace ();

            }

      }

    }

    public void startApp(){
        Intent intent=new Intent ( home_page.this,MainActivity.class );
        startActivity ( intent );
        finish ();

    }
}
