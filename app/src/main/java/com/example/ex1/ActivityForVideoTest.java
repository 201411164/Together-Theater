package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;
import android.widget.MediaController;
import android.net.Uri;
import android.widget.FrameLayout;
import android.widget.LinearLayout;




public class ActivityForVideoTest extends AppCompatActivity {
    public static int user = 1;//사용자 식별번호
    int aW,bW,cW,aH,bH,cH = 0;//화면 분할을 위한 각 디바이스 가로세로 길이
    int sH;//기기 abc 중 가장 작은 높이값
    int W,H=0;//결정된 비디오뷰의 길이
    int aX,bX,cX = 0;//좌표 이동을 위한 각 기기의 X값


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_video_test);                                               //비디오뷰 생성을 위한 부분
        VideoView vv =(VideoView)findViewById(R.id.videoView1);
        Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test2);
        vv.setVideoURI(video);

        W=aW+bW+cW;
        H=(aW+bW+cW)/16*9;                                                                               //영상 배치를 위한 부분
        W=1920;//테스트용 임시값
        H=1080;//테스트용 임시값
        sH=1060;//테스트용 임시값
        if(H>sH) {
            H = sH;
            W = sH / 9 * 16;
            W=1920;
            H=1080;
            vv.setLayoutParams(new FrameLayout.LayoutParams(W, H));
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.info);
            linearLayout.addView(vv);

            vv.setX(aX);
        }
        else{
            return;
        }

        if(user==1) {                                                                                       //호스트 기기에만 미디어 컨트롤러가 나오도록 하는 부분
            final MediaController mc = new MediaController(this);
            vv.setMediaController(mc);
        }
        else {
            return;
        }
//앞으로 필요한 기능 - 별도의 변수 추가하여 각 기기마다 독립적 view 제공
//기기 배치를 위한 가이드라인 제공
//영상 일시정지 시 정지한 시간 다른 기기로 전송, 그 시간에 맞추어 정지
// 다시 재생 시 정해진 시간(위성시계 기준)에 동시 재생 시작
//화면 배치 알고리즘 완성
        //미디어 컨트롤러는 호스트기기에만 뜨도록 설정 (완료)
    }
}