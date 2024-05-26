package ru.wolfnord.task11;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.wolfnord.task11.databinding.ActivityAnimationBinding;

public class AnimationActivity extends AppCompatActivity {
    ActivityAnimationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnimationBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(binding.image, "rotation", 0f, 360f);
        rotationAnimator.setDuration(500);
        rotationAnimator.setInterpolator(new LinearInterpolator());

        binding.image.setOnClickListener(v -> {
            rotationAnimator.start();
        });
    }
}