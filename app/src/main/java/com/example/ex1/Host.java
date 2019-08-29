package com.example.ex1;


import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;
//import android.widget.MediaController;
import android.widget.Button;
import android.net.Uri;


public class Host extends AppCompatActivity {

    int user = 1;//사용자 식별번호, 호스트 기기에만 미디어컨트롤러가 나오도록 하기 위함(user 변수의 값이 1인 경우에만 나오게 함)
    int aW, bW, cW, aH, bH, cH = 0;//화면 분할을 위한 각 디바이스 가로세로 길이
    int sH = 0;//기기 a b c 중 가장 작은 높이값
    int H = 1500;//결정된 레이아웃의 길이
    int W = 3000;
    int aX, bX, cX = 0;//좌표 이동을 위한 각 기기의 X값
    int aY, bY, cY = 0;
    int stopTime = 0;
    VideoView vv;
    Button btnStart, btnPause;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        //시작,일시정지 버튼
        btnStart = (Button) findViewById(R.id.btnStart);
        btnPause = (Button) findViewById(R.id.btnPause);
        //비디오뷰 생성
        vv = findViewById(R.id.videoView1);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test2);
        vv.setVideoURI(video);

        //비디오뷰의 높이 설정을 위한 디바이스들 중 높이 최소값 구해 sH에 저장
        if (aH < bH) {
            if (aH < cH) {
                sH = aH;
            } else {
                sH = cH;
            }
        } else {
            if (cH < bH) {
                sH = cH;
            } else {
                sH = bH;
            }
        }
        //결정된 레이아웃의 높이, 넓이값 정의
        W = aW + bW + cW;
        H = W / 16 * 9;
        if (H > sH) {
            W = sH / 9 * 16;
        }
        //기기마다 다른 setX값 지정을 위함
        aX = 0;
        bX = -aW;
        cX = -aW - bW;

        //비디오뷰 사이즈 조절
        ViewGroup.LayoutParams params = vv.getLayoutParams();
        params.height = H;
        params.width = W;
        vv.setLayoutParams(params);
        vv.setX(aX);
        vv.setY(aY);
    }
    //user 변수의 값이 1일 경우(=호스트 기기일 경우) 미디어 컨트롤러 생성
    //public void mediaController () {
    //    if (user == 1) {
    //        MediaController mediaController = new MediaController(this);
    //        mediaController.setAnchorView(vv);
    //        mediaController.setPadding(0, 0, 0, 0);
    //        vv.setMediaController(mediaController);
    //    }
    //}


    public void StartButton(View v) {
        playVideo();
    }

    public void PauseButton(View v) {
        pauseVideo();
    }

    //영상 재생,정지 동기화
    //일시정지
    public void pauseVideo() {
        vv.getCurrentPosition();
        vv.pause();
        stopTime = vv.getCurrentPosition();
        //vv.seekTo(stopTime);
    }

    //재생
    //Handler 이용해 현재 시간으로부터 2000밀리세컨드 후에 재생 Method 호출(vv.start())
    public void playVideo() {
        //vv.seekTo(stopTime);
        vv.start();
    }
}
//일시정지
//호스트 기기에서 일시정지를 누른 경우 일시정지 된 시간을 stopTime 변수에 저장 후 stopSignal과 stopTime을 클라이언트 기기로 전송
//stopSignal을 받으면 stopTime 에 저장된 시간으로 영상 이동 후 일시정지

//다시 재생
//호스트 기기에서 재생을 누른 경우 현재시간을 구한 후 그로부터 +2sec 된 시간을 playTime 변수에 저장, playSignal과 함께 클라이언트 기기로 전송
//playSignal을 받으면 playTime 변수에 저장된 시간에 재생 시작
// 현재과제
////별도의 변수(int user) 추가하여 각 기기마다 독립적 액티비티 제공
////기기 배치를 위한 가이드라인 제공
////영상 일시정지 시 정지한 시간 다른 기기로 전송, 그 시간에 맞추어 정지
////다시 재생 시 정해진 시간(위성시계 기준)에 동시 재생 시작
////화면 배치 알고리즘 완성(레이아웃과 뷰 연결)
////미디어 컨트롤러는 호스트기기에만 뜨도록 설정
//