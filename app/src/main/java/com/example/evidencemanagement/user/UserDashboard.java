package com.example.evidencemanagement.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evidencemanagement.R;
import com.example.evidencemanagement.databinding.ActivityUserDashboardBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserDashboard extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ActivityUserDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(UserDashboard.this);
                // ...Irrelevant code for customizing the buttons and title
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.complaint_dialog, null);
                dialogBuilder.setView(dialogView);

                EditText editText = (EditText) dialogView.findViewById(R.id.email_et);
                Button submit = (Button) dialogView.findViewById(R.id.submit_button);
                Button cancel = (Button) dialogView.findViewById(R.id.cancel_button_1);





                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.getWindow().setLayout(1000,1000);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String str = editText.getText().toString();
                        if(str.isEmpty()){
                            Toast.makeText(UserDashboard.this, "Enter your complaint please!!", Toast.LENGTH_SHORT).show();

                        }else{
                            //register complaint on firebase
                            // Create a new user with a first and last name
                            Map<String, String> user = new HashMap<>();
                            user.put("complaint", str);

                            db.collection("complaints")
                                    .add(user)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(UserDashboard.this, "Complaint Registered", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(UserDashboard.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });

            }
        });





    }
}