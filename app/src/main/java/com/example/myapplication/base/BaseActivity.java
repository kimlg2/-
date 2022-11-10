package com.example.myapplication.base;


import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.DiaryDao;
import com.example.myapplication.data.dao.ReplyDao;

import io.reactivex.rxjava3.disposables.CompositeDisposable;


public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {
    @LayoutRes
    protected abstract int layoutRes();

    protected abstract void onViewCreated();


    protected B binding;


    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected DiaryDao diaryDao;
    protected ReplyDao replyDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, layoutRes());
        binding.setLifecycleOwner(this);
        diaryDao = AppDatabase.getInstance(getApplicationContext()).diaryDao();
        replyDao = AppDatabase.getInstance(getApplicationContext()).replyDao();
        onViewCreated();
    }


    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

}
