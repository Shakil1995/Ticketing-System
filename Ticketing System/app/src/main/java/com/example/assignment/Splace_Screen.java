package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class Splace_Screen extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        requestWindowFeature ( Window.FEATURE_NO_TITLE );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_splace__screen );
        progressBar = findViewById ( R.id.progresbar_ID );


        Thread thread = new Thread ( new Runnable () {
            @Override
            public void run() {
                doWork ();

                goHomepage ();
            }
        } );
        thread.start ();
    }

    public void doWork() {


        for (progress = 20; progress <= 100; progress = progress + 20) {
            try {
                Thread.sleep ( 1000 );
                progressBar.setProgress ( progress );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }

    }


    public void goHomepage() {
        Intent intent=new Intent ( Splace_Screen.this,ChapterViews.class);
        startActivity ( intent );
        finish ();






    }

}


