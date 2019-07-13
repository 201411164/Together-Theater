package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;
import android.widget.MediaController;
import android.net.Uri;



public class ActivityForVideoTest extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_video_test);
        VideoView vv =(VideoView)findViewById(R.id.videoView1);
        Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test2);
        vv.setVideoURI(video);
        final MediaController mc=new MediaController(this);
        vv.setMediaController(mc);

    }
}