package com.example.evidencemanagement.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.evidencemanagement.LoginActivity;
import com.example.evidencemanagement.R;
import com.example.evidencemanagement.databinding.ActivityMainBinding;
import com.example.evidencemanagement.databinding.ActivityUserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {
    private ActivityUserBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                intent.putExtra("code",1);
                startActivity(intent);
            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });


        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkErrors();
            }
        });




    }

    private void checkErrors() {
        if(binding.emailEt.getText().toString().isEmpty() || binding.passwordEt.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            registerUser(binding.emailEt.getText().toString(),binding.passwordEt.getText().toString());
        }
    }

    private void registerUser(String email, String pass) {
        auth.createUserWithEmailAndPassword(email,pass)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(UserActivity.this,UserDashboard.class);
                            intent.putExtra("code",1);
                            startActivity(intent);
                            finish();
                        }
                    }
                })

        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

}