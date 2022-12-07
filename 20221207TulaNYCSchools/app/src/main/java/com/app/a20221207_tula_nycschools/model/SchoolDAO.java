package com.app.a20221207_tula_nycschools.model;

import android.content.Context;

import com.app.a20221207_tula_nycschools.services.APIRequest;
import com.app.a20221207_tula_nycschools.services.ArrayCallback;

public class SchoolDAO {

    private static SchoolDAO _this;
    Context context;

    public static SchoolDAO getInstance(Context context) {
        if (_this == null) {
            _this = new SchoolDAO();
        }
        _this.context = context;
        return _this;
    }

    public void getList( ArrayCallback<SchoolModel> callback) {
        new APIRequest(context).getArrayRequest("s3k6-pzi2.json" , SchoolModel.class, callback);
    }
    public void getDetail( ArrayCallback<SchoolModel> callback) {
        new APIRequest(context).getArrayRequest("f9bf-2cp4.json" , SchoolModel.class, callback);
    }
}
