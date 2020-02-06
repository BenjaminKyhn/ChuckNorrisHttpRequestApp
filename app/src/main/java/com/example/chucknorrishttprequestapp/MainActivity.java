package com.example.chucknorrishttprequestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chucknorrishttprequestapp.ChuckNorrisAPICall;
import com.example.chucknorrishttprequestapp.R;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView mainText;
    Button mainButton;
    Button startButton;
    Button stopButton;
    Button nextButton;
    ImageView imageView;
    int currentSound = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.mainText);
        mainButton = findViewById(R.id.mainBtn);
        startButton = findViewById(R.id.startSoundBtn);
        stopButton = findViewById(R.id.stopSoundBtn);
        nextButton = findViewById(R.id.nextSoundBtn);
        imageView = findViewById(R.id.newImageView);

        final MediaPlayer[] list = new MediaPlayer[8];
        list[0] = MediaPlayer.create(this,R.raw.quote1);
        list[1] = MediaPlayer.create(this,R.raw.quote2);
        list[2] = MediaPlayer.create(this,R.raw.quote3);
        list[3] = MediaPlayer.create(this,R.raw.quote4);
        list[4] = MediaPlayer.create(this,R.raw.quote5);
        list[5] = MediaPlayer.create(this,R.raw.quote6);
        list[6] = MediaPlayer.create(this,R.raw.quote7);
        list[7] = MediaPlayer.create(this,R.raw.quote8);

        String pictureUrl = "https://www.pngitem.com/pimgs/m/157-1579710_picture-chuck-norris-hd-png-download.png";

        String url = "https://api.chucknorris.io/jokes/random";

        Picasso.get().load(pictureUrl).into(imageView);

        ChuckNorrisAPICall chuckNorrisAPICall = new ChuckNorrisAPICall();
        chuckNorrisAPICall.handleAPICall(url, mainText, this);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.chucknorris.io/jokes/random";

                ChuckNorrisAPICall chuckNorrisAPICall = new ChuckNorrisAPICall();
                chuckNorrisAPICall.handleAPICall(url, mainText, getApplicationContext());
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list[currentSound].start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list[currentSound].pause();
                list[currentSound].seekTo(0);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSound++;
                if (currentSound > list.length-1){
                    currentSound = 0;
                }
                list[currentSound].start();
            }
        });
    }
}
