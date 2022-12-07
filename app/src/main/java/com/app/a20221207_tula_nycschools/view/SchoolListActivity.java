package com.app.a20221207_tula_nycschools.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.app.a20221207_tula_nycschools.databinding.ActivitySchoolBinding;
import com.app.a20221207_tula_nycschools.services.ArrayCallback;
import com.app.a20221207_tula_nycschools.helper.Utils;
import com.app.a20221207_tula_nycschools.model.SchoolDAO;
import com.app.a20221207_tula_nycschools.model.SchoolModel;

import java.util.ArrayList;
import java.util.List;

public class SchoolListActivity extends AppCompatActivity {
    private Context context;
    List<SchoolModel> list;
    ActivitySchoolBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize view
        init();
        //Api Calling
        getSchoolList();
    }

    private void getSchoolList() {
        Utils.showProgressDialog(this);
        SchoolDAO.getInstance(this).getList(new ArrayCallback<SchoolModel>() {
            @Override
            public void onData(List<SchoolModel> data) {
                list=data;
                Utils.dismissProgressDialog();
                binding.rvList.setAdapter(new SchoolListAdapter(context,list));
            }
            @Override
            public void onError(String msg) {
                Utils.dismissProgressDialog();
            }
        });
    }

    private void init() {
        binding=ActivitySchoolBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context=this;
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("School List");
        }
        //Add listener when searching
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int k, int i1, int i2) {
                List<SchoolModel> searchList=new ArrayList<>();
                for (int i=0;i<list.size();i++){
                    if (list.get(i).getSchool_name().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        searchList.add(list.get(i));
                    }
                    binding.rvList.setAdapter(new SchoolListAdapter(context,searchList));
                }
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}