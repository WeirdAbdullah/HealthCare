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

import com.example.healthcare.LabTestDetails;
import com.example.healthcare.R;
import com.example.healthcare.model.LabTestModel;

import java.util.ArrayList;

public class Recycler extends RecyclerView.Adapter<Recycler.ViewHolder> {
    Context context;
    ArrayList<LabTestModel> labs;
    public Recycler(Context context, ArrayList<LabTestModel> labs){
        this.context = context;
        this.labs = labs;
    }
    @NonNull
    @Override
    public Recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.labtest_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler.ViewHolder holder, int position) {
        holder.img.setImageResource(labs.get(position).img);
        holder.txt.setText(labs.get(position).name);
//        holder.price.setText(labs.get(position).price);
        holder.price.setText(labs.get(position).price);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, labs.get(position).name, Toast.LENGTH_SHORT).show();
                Intent i;
                i = new Intent(context , LabTestDetails.class);

                i.putExtra("lab_img", labs.get(position).img);
                //i.putExtra("special", doc.get(position).special);
                i.putExtra("lab_test_name", labs.get(position).name);
                i.putExtra("lab_test_info", labs.get(position).test_details);
                i.putExtra("price", labs.get(position).price);
                i.putExtra("no_of_test", labs.get(position).no_of_test);


                context.startActivity(i);


            }
        });;



    }
    @Override
    public int getItemCount() {
        return labs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView img;
        Button btn;
        TextView price, no_of_test, lab_test_details, test_time;
        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt = itemView.findViewById(R.id.test_name);
            img = itemView.findViewById(R.id.test_image);
            btn = itemView.findViewById(R.id.book_test_button);
            price = itemView.findViewById(R.id.test_price);
            no_of_test = itemView.findViewById(R.id.test_includes);
            lab_test_details = itemView.findViewById(R.id.test_description);
            test_time = itemView.findViewById(R.id.report_time);
        }
    }
}
