package com.example.healthcare.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcare.LabTestDetails;
import com.example.healthcare.R;
import com.example.healthcare.hadetails;
import com.example.healthcare.healtharticle;
import com.example.healthcare.model.hamodel;

import java.util.ArrayList;

public class RecyclerHA extends RecyclerView.Adapter<RecyclerHA.ViewHolder> {
    Context context;
    ArrayList<hamodel> labs;
    public RecyclerHA(Context context, ArrayList<hamodel> labs){
        this.context = context;
        this.labs = labs;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.harow, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img.setImageResource(labs.get(position).image);
        holder.title.setText(labs.get(position).title);
        holder.short_description.setText(labs.get(position).short_description);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, labs.get(position).name, Toast.LENGTH_SHORT).show();
                //Toast.makeText(context,"Done", Toast.LENGTH_SHORT).show();
                Intent i;
                i = new Intent(context , hadetails.class);

                i.putExtra("image", labs.get(position).image);
                i.putExtra("title", labs.get(position).title);
                i.putExtra("description", labs.get(position).long_description);



                context.startActivity(i);


            }
        });;



    }
    @Override
    public int getItemCount() {
        return labs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView short_description;
        ImageView img;
        Button btn;
        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.articleTitle);
            img = itemView.findViewById(R.id.articleImage);
            btn = itemView.findViewById(R.id.seeMoreButton);
            short_description = itemView.findViewById(R.id.articleDescription);

        }
    }
}

