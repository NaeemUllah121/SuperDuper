package com.zasa.superduper.eLearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.zasa.superduper.R;

public class YTPlayVideoActivity extends AppCompatActivity {

    String vId, videoTitle, videoDescription, videoDate;
    YouTubePlayerView youTubePlayerView;
    TextView title, date, description;
    NestedScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt_play_video);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//hide status bar
        //getSupportActionBar().hide();//hide action bar

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //play video horizontally



        title = findViewById(R.id.v_title);
        date = findViewById(R.id.v_date);
        description = findViewById(R.id.v_Dis);
        vId = getIntent().getStringExtra("videoId");
        videoTitle = getIntent().getStringExtra("videoTitle");
        videoDescription = getIntent().getStringExtra("videoDescription");
        videoDate = getIntent().getStringExtra("videoDate");

        String st_date = videoDate.replace("T", "  ");//replace  letter with space
        String st_newDate = st_date.replace("Z", "");//replace letters with space

        title.setText(videoTitle);
        date.setText(st_newDate);
        description.setText(videoDescription);





        mYoutubePlayer();

        mScrollView();




    }

    private void mScrollView() {

        mScrollView = findViewById(R.id.mScrollView);
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


                int scrolllX = v.getScrollX();
                int scrolllY = v.getScrollY(); // For ScrollView
                // Log.d("ScrollView", "scrollX_" + scrollX + "_scrollY_" + scrollY + "_oldScrollX_" + oldScrollX + "_oldScrollY_" + oldScrollY);
                //Do something

                Rect scrollBounds = new Rect();
                mScrollView.getHitRect(scrollBounds);

                if (youTubePlayerView.getLocalVisibleRect(scrollBounds)) {
                    if (!youTubePlayerView.getLocalVisibleRect(scrollBounds) || scrollBounds.height() < youTubePlayerView.getHeight()) {
                        Log.i("TAGG", "BTN APPEAR PARCIALY");
                    } else {
                        Log.i("TAGG", "BTN APPEAR FULLY!!!");
                        mAdjustAudio(10);
                    }
                } else {
                    Log.i("TAGG", "No");
                    mAdjustAudio(0);
                }
            }
        });
    }

    private void mAdjustAudio(int vol) {
        AudioManager audioManager;
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, vol, 0);
    }

    private void mYoutubePlayer() {

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        //youTubePlayerView.enterFullScreen();
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                 youTubePlayer.loadVideo(vId, 0);
                //youTubePlayer.loadVideo("S0Q4gqBUs7c", 0);
               /* if (a.equals("pause")) {
                    Toast.makeText(YTPlayVideoActivity.this, "pause call", Toast.LENGTH_SHORT).show();
                    youTubePlayer.pause();
                }
                if (a.equals("play")) {
                    youTubePlayer.play();
                    Toast.makeText(YTPlayVideoActivity.this, "play call", Toast.LENGTH_SHORT).show();
                }*/
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }

}