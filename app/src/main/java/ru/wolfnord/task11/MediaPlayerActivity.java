package ru.wolfnord.task11;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private EditText audioUrlEditText;
    private Button playButton;
    private Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        audioUrlEditText = findViewById(R.id.musicUrl);
        playButton = findViewById(R.id.play_button);
        stopButton = findViewById(R.id.pause_button);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());

        playButton.setOnClickListener(v -> playAudio());

        stopButton.setOnClickListener(v -> stopAudio());
    }

    private void playAudio() {
        String audioUrl = audioUrlEditText.getText().toString();
        if (!audioUrl.isEmpty()) {
            try {
                mediaPlayer.setDataSource(audioUrl);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(mp -> {
                    mediaPlayer.start();
                    playButton.setEnabled(false);
                    stopButton.setEnabled(true);
                });
            } catch (IOException e) {
                Log.e("mediaPlayer", "audioUrl is Empty");
            }
        }
    }

    private void stopAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            playButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}