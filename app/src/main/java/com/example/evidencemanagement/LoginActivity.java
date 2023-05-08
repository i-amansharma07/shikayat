package com.example.evidencemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.evidencemanagement.databinding.ActivityLoginBinding;
import com.example.evidencemanagement.dept.DepartmentDashboard;
import com.example.evidencemanagement.user.UserActivity;
import com.example.evidencemanagement.user.UserDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    private int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        code = intent.getIntExtra("code",0);

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
                onBackPressed();
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
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
        auth.signInWithEmailAndPassword(email,pass)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            if(code==1){
                                Intent intent1 = new Intent(LoginActivity.this, UserDashboard.class);
                                startActivity(intent1);
                            }else{
                                Intent intent1 = new Intent(LoginActivity.this, DepartmentDashboard.class);
                                startActivity(intent1);
                            }
                            finish();
                        }
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });


    }
}