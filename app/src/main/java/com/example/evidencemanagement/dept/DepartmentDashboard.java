package com.example.evidencemanagement.dept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.evidencemanagement.R;
import com.example.evidencemanagement.adapter.ComplaintAdapter;
import com.example.evidencemanagement.databinding.ActivityDepartmentDashboardBinding;
import com.example.evidencemanagement.pojo.Complaints;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DepartmentDashboard extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> data = new ArrayList<>();

    private ActivityDepartmentDashboardBinding binding;
    private ComplaintAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDepartmentDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//       db.collection("complaints")
//               .get()
//               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                   @Override
//                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                       if (task.isSuccessful()) {
//                           for (QueryDocumentSnapshot document : task.getResult()) {
//                               tempObj = document.toObject(Complaints.class);
//                               Log.d("amannnnn",tempObj.toString());
//                               Log.d("maaaaaaa",""+tempObj.getComp());
//                               data.add(tempObj.getComp());
//                           }
//                       } else {
//                           Toast.makeText(DepartmentDashboard.this, "No Complaints", Toast.LENGTH_SHORT).show();
//                       }
////                       Toast.makeText(DepartmentDashboard.this, ""+data.toString(), Toast.LENGTH_SHORT).show();
//                       Log.d("amollllll", data.toString());
//                       adapter = new ComplaintAdapter(data);
//                       binding.iv.setAdapter(adapter);
//                   }
//               });

        db.collection("complaints")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                       for(QueryDocumentSnapshot doc:queryDocumentSnapshots){
                          String  tempObj = doc.get("complaint").toString();
                           data.add(tempObj);
                       }
                       Log.d("hellloooo",data.toString());
                        adapter = new ComplaintAdapter(data);
                        binding.iv.setAdapter(adapter);
                    }
                });



    }
}