package com.app.a20221207_tula_nycschools.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.a20221207_tula_nycschools.R;
import com.app.a20221207_tula_nycschools.model.SchoolModel;
import com.google.gson.Gson;

import java.util.List;

public class SchoolListAdapter extends RecyclerView.Adapter<SchoolListAdapter.VHolder> {
    Context context;
    List<SchoolModel> list;
    public SchoolListAdapter(Context context, List<SchoolModel> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.school_list_item, parent, false);
        return new VHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VHolder holder, @SuppressLint("RecyclerView") final int position) {
        SchoolModel model = list.get(position);
        holder.tvSchoolName.setText(model.getSchool_name());
        holder.tvParagraph.setText(model.getOverview_paragraph());
        holder.tvPhone.setText(model.getPhone_number());
        holder.tvLocation.setText(model.getLocation());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               context.startActivity(new Intent(context, DetailActivity.class).putExtra("data",new Gson().toJson(model)));
           }
       });
        holder.tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "http://maps.google.com/maps?q=loc:" +  model.getLatitude() + "," + model.getLocation();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                context.startActivity(intent);
            }
        });
        holder.tvPhone.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                //Dialing Phone Number
               Uri number = Uri.parse("tel:"+model.getPhone_number());
               Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
               context.startActivity(callIntent);
           }
       });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VHolder extends RecyclerView.ViewHolder {
        TextView tvSchoolName,tvPhone,tvLocation,tvParagraph;

        public VHolder(@NonNull View itemView) {
            super(itemView);
            tvSchoolName = itemView.findViewById(R.id.tvSchoolName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvParagraph = itemView.findViewById(R.id.tvParagraph);
        }
    }

}
