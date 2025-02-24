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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcare.R;
import com.example.healthcare.doctordetails;
import com.example.healthcare.model.DoctorModel;

import java.util.ArrayList;

public class RecyclerDoctor extends RecyclerView.Adapter<RecyclerDoctor.ViewHolder> {
    Context context;
    ArrayList<DoctorModel> doc;

    public RecyclerDoctor(Context context, ArrayList<DoctorModel> doc) {
        this.context = context;
        this.doc = doc;
    }

    @NonNull
    @Override
    public RecyclerDoctor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.doctor_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDoctor.ViewHolder holder, int position) {
        holder.img.setImageResource(doc.get(position).img);
        holder.name.setText(doc.get(position).name);
        holder.deg.setText(doc.get(position).degree);
        holder.special.setText(doc.get(position).special);
        holder.rate.setText(doc.get(position).rate);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(context , doctordetails.class);
                i.putExtra("special", doc.get(position).special);
                i.putExtra("doc_photo", doc.get(position).img);
                i.putExtra("title", doc.get(position).degree);
                i.putExtra("adress", doc.get(position).adress);
                i.putExtra("rate", doc.get(position).rate);
                i.putExtra("bio", doc.get(position).Bio);
                i.putExtra("patient", doc.get(position).patience);
                i.putExtra("exp", doc.get(position).Experience);



                context.startActivity(i);
                //Toast.makeText(context, doc.get(position).name, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return doc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, special, deg, rate;
        ImageView img;
        Button btn;

        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
            deg = itemView.findViewById(R.id.degree);
            special = itemView.findViewById(R.id.special);
            rate = itemView.findViewById(R.id.scoreText);
            btn = itemView.findViewById(R.id.makebutton);
        }
    }
}
