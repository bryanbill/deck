package com.minty.deck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthProvider;
import com.minty.deck.databinding.ActivityMainBinding;
import com.minty.deck.databinding.ActivitySplashBinding;
import com.minty.deck.utils.Navigator;
import com.minty.deck.utils.ServiceLocator;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Initialize FirebaseApp
        FirebaseApp.initializeApp(this);
        //OAuth Provider
        OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");
        mAuth = FirebaseAuth.getInstance();
        //Firebase Auth
        Task<AuthResult> pending = mAuth.getPendingAuthResult();
        if (pending != null) {
            pending.addOnSuccessListener(authResult -> {
                        new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                Log.d("SplashActivity", "onSuccess: " + authResult.getUser().getDisplayName());
                            }
                        };
                    })
                    .addOnFailureListener(e -> {
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Log.d("SplashActivity", "onFailure: " + e.getMessage());
                            }
                        };
                    });

        } else {
            mAuth.startActivityForSignInWithProvider(this, provider.build())
                    .addOnSuccessListener(authResult -> {
                        ServiceLocator.getInstance().getUserDao().setAuthResult(authResult);
                        // User is signed in.
                        Navigator.pushAndRemove(SplashActivity.this, MainActivity.class);
                    })
                    .addOnFailureListener(e -> {
                        // Handle failure.
                        Log.d("SplashActivity", "onCreate: " + e.getMessage());
                        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        }

//        // Check if view is mounted
//        if (binding.getRoot() != null) {
//            // Defer the navigation to the next thread after 3 seconds
//            Timer timer = new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                   Navigator.pushAndRemove(SplashActivity.this, MainActivity.class);
//                }
//            }, TimeUnit.SECONDS.toMillis(3));
//        }
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