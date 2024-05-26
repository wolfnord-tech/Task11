package ru.wolfnord.task11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.wolfnord.task11.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.webView.setOnClickListener(this);
        binding.mediaPlayer.setOnClickListener(this);
        binding.notify.setOnClickListener(this);
        binding.animation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.webView) {
            startActivity(new Intent(this, WebViewActivity.class));
        } else if (v.getId() == R.id.mediaPlayer) {
            startActivity(new Intent(this, MediaPlayerActivity.class));
        } else if (v.getId() == R.id.notify) {
            startActivity(new Intent(this, NotificationActivity.class));
        } else if (v.getId() == R.id.animation) {
            startActivity(new Intent(this, AnimationActivity.class));
        }
    }
}