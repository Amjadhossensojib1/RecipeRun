package com.dalazu.reciperun.ui.video;

import android.os.Bundle;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dalazu.reciperun.R;
import com.dalazu.reciperun.utils.BaseActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoActivity extends BaseActivity {

    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        
        setupToolbar(R.id.toolbar);

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        String videoUrl = getIntent().getStringExtra("videoUrl");
        Log.d("URL", "Url: "+videoUrl.toString());

        if (videoUrl == null || videoUrl.isEmpty()) {
            Toast.makeText(this, "ভিডিও URL পাওয়া যায়নি", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String videoId = extractYoutubeVideoId(videoUrl);

        if (videoId == null) {
            Toast.makeText(this, "সঠিক ভিডিও URL নয়", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }

    // Helper method to extract YouTube video ID
    private String extractYoutubeVideoId(String url) {
        try {
            if (url.contains("youtube.com/watch?v=")) {
                return url.substring(url.indexOf("v=") + 2, url.indexOf("v=") + 13);
            } else if (url.contains("youtu.be/")) {
                return url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("/") + 12);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
