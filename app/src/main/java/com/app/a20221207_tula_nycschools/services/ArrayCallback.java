package com.app.a20221207_tula_nycschools.services;

import java.util.List;

public interface ArrayCallback<T> {
    void onData(List<T> list);
    void onError(String msg);
}
