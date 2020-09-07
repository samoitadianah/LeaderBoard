package com.example.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.leaderboard.databinding.ActivityMainBinding;
import com.example.leaderboard.views.activities.Homepage;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        nextPage();
    }

    public void nextPage() {
        new Handler().postDelayed(() -> {
            //Navigate to next page.
            startActivity(new Intent(MainActivity.this, Homepage.class));
            finish();
        }, 1000);
    }
}
