package com.minty.deck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.minty.deck.databinding.ActivityMainBinding;
import com.minty.deck.databinding.ActivitySplashBinding;
import com.minty.deck.utils.Navigator;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // Check if view is mounted
        if (binding.getRoot() != null) {
            // Defer the navigation to the next thread after 3 seconds
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                   Navigator.pushAndRemove(SplashActivity.this, MainActivity.class);
                }
            }, TimeUnit.SECONDS.toMillis(3));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("SplashActivity", "onBackPressed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("SplashActivity", "onResume");
    }
}