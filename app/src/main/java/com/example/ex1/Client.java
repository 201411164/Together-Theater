package com.example.ex1;


import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;
import android.net.Uri;


public class Client extends AppCompatActivity {

    int user = 2;//사용자 식별번호, 호스트 기기에만 미디어컨트롤러가 나오도록 하기 위함(user 변수의 값이 1인 경우에만 나오게 함)
    int H = 1500;//결정된 레이아웃의 길이
    int W = 3000;
    int aW, bW, cW, aH, bH, cH = 0;
    int aX, bX, cX = 0;//좌표 이동을 위한 각 기기의 X값
    int aY, bY, cY = 0;//좌표 이동을 위한 각 기기의 Y값
    int stopTime = 0;
    VideoView vv;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        vv = findViewById(R.id.videoView1);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test2);
        vv.setVideoURI(video);
        ViewGroup.LayoutParams params = vv.getLayoutParams();
        params.height = H;
        params.width = W;
        vv.setLayoutParams(params);
        vv.setX(bX);
        vv.setY(bY);
    }

    public void pauseVideo() {
        vv.getCurrentPosition();
        vv.pause();
        stopTime = vv.getCurrentPosition();
        //vv.seekTo(stopTime);
    }
}
