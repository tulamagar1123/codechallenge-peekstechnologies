package com.app.a20221207_tula_nycschools.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.a20221207_tula_nycschools.services.ArrayCallback;
import com.app.a20221207_tula_nycschools.helper.Utils;
import com.app.a20221207_tula_nycschools.databinding.ActivityDetailBinding;
import com.app.a20221207_tula_nycschools.model.SchoolDAO;
import com.app.a20221207_tula_nycschools.model.SchoolModel;
import com.google.gson.Gson;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private Context context;
    SchoolModel model;
    ActivityDetailBinding binding;
    private boolean isDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getting data of selected school
        model = new Gson().fromJson(getIntent().getStringExtra("data"), SchoolModel.class);
        //initialize view
        init();
        //Api Calling
        getSchoolDetail();
      }

    private void init() {
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("School Detail");
        }
    }
    private void getSchoolDetail() {
        Utils.showProgressDialog(context);
        SchoolDAO.getInstance(context).getDetail(new ArrayCallback<SchoolModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onData(List<SchoolModel> list) {
                Utils.dismissProgressDialog();
                //matching selected school
                for (int i = 0; i < list.size(); i++) {
                    if (model.getDbn().equals(list.get(i).getDbn())) {
                        model.setNum_of_sat_test_takers(list.get(i).getNum_of_sat_test_takers());
                        model.setSat_critical_reading_avg_score(list.get(i).getSat_critical_reading_avg_score());
                        model.setSat_writing_avg_score(list.get(i).getSat_writing_avg_score());
                        model.setSat_math_avg_score(list.get(i).getSat_math_avg_score());
                        isDetail = true;
                    }
                }
                setData();
                binding.tvEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{model.getSchool_email()});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                        intent.putExtra(Intent.EXTRA_TEXT, "body");
                        startActivity(Intent.createChooser(intent, ""));
                    }
                });
            }


            @Override
            public void onError(String msg) {
                Utils.dismissProgressDialog();
                Log.e("onError", msg + "->");

            }
        });
    }

    private void setData() {
        if (isDetail) {
            binding.tvCriticalAvg.setText(model.getSat_critical_reading_avg_score());
            binding.tvMathAvg.setText(model.getSat_math_avg_score());
            binding.tvSatTestTakers.setText(model.getNum_of_sat_test_takers());
            binding.tvWritingAvg.setText(model.getSat_writing_avg_score());
        } else {
            binding.llLabel.setVisibility(View.GONE);
            binding.llMain.setVisibility(View.GONE);
        }
        binding.tvSchoolName.setText(model.getSchool_name());
        binding.tvEmail.setText(model.getSchool_email());
        binding.tvLanguage.setText(model.getLanguage_classes());
        binding.tvWebsite.setText(model.getWebsite());
        binding.tvTotalStudents.setText(model.getTotal_students());
        binding.tvCity.setText(model.getCity());
        binding.tvAttendance.setText(model.getAttendance_rate());
    }


}