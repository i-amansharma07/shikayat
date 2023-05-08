package com.example.evidencemanagement.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evidencemanagement.R;
import com.example.evidencemanagement.pojo.Complaints;

import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder>{
    private ArrayList<String>  data;

    // RecyclerView recyclerView;
    public  ComplaintAdapter(ArrayList<String>  data) {
        this.data = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.complaint_list_view, parent, false);
        return new ViewHolder(listItem);
       }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.comp.setText(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView comp;

        public ViewHolder(View itemView) {
            super(itemView);
            this.comp = (TextView) itemView.findViewById(R.id.comp_text);

        }
    }
}

