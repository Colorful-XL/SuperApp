package com.example.superapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.superapp.R;
import com.example.supersdk.player.CustomVideoView;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        RelativeLayout relativeLayout = findViewById(R.id.texture_view);
        CustomVideoView customVideoView = new CustomVideoView(this , relativeLayout);
        customVideoView.setDataSource("http://stream4.iqilu.com/ksd/video/2020/02/17/87d03387a05a0e8aa87370fb4c903133.mp4");
        customVideoView.setListener(new CustomVideoView.ADVideoPlayerListener() {
            @Override
            public void onBufferUpdate(int time) {

            }

            @Override
            public void onClickFullScreenBtn() {

            }

            @Override
            public void onClickVideo() {

            }

            @Override
            public void onClickBackBtn() {

            }

            @Override
            public void onClickPlay() {

            }

            @Override
            public void onAdVideoLoadSuccess() {

            }

            @Override
            public void onAdVideoLoadFailed() {

            }

            @Override
            public void onAdVideoLoadComplete() {

            }
        });
        relativeLayout.addView(customVideoView);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" );
    }
}
