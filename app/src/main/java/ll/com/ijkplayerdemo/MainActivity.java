package ll.com.ijkplayerdemo;

import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Half;
import android.util.Log;
import android.widget.TableLayout;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.widget.media.IjkVideoView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "IjkPlayerDemo";
    IjkVideoView ijkVideoView;
    private TableLayout mHudView;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        setContentView(R.layout.activity_main);
        ijkVideoView = findViewById(R.id.my_video);
        mHudView = findViewById(R.id.hud_view);
//        ijkVideoView.setHudView(mHudView);
        ijkVideoView.setVideoPath(Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/video_test.mp4");
        ijkVideoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.d(TAG, "onInfo i " + i + ",i1 " + i1 );
                if(i == IMediaPlayer.MEDIA_INFO_FIND_STREAM_INFO) {
//                    ijkVideoView.setBackgroundColor(Color.BLACK);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ijkVideoView.start();
                            ijkVideoView.setBackgroundColor(Color.TRANSPARENT);
                        }
                    }, 2000);

                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkVideoView.stopPlayback();
    }
}
