package com.example.evidencemanagement.dept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.evidencemanagement.LoginActivity;
import com.example.evidencemanagement.R;
import com.example.evidencemanagement.databinding.ActivityDepatmentBinding;
import com.example.evidencemanagement.user.UserActivity;
import com.example.evidencemanagement.user.UserDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DepatmentActivity extends AppCompatActivity {
    private ActivityDepatmentBinding binding;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDepatmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DepatmentActivity.this, LoginActivity.class);
                intent.putExtra("code",2);
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
                            Intent intent = new Intent(DepatmentActivity.this, DepartmentDashboard.class);
                            intent.putExtra("code",2);
                            startActivity(intent);
                            finish();
                        }
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DepatmentActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });


    }
}