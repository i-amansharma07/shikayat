package com.example.evidencemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.evidencemanagement.databinding.ActivityMainBinding;
import com.example.evidencemanagement.dept.DepatmentActivity;
import com.example.evidencemanagement.user.UserActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.buttonDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DepatmentActivity.class);

                startActivity(intent);
            }
        });

        binding.buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);

                startActivity(intent);
            }
        });
    }
}