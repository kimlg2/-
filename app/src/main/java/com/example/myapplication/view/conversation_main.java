package com.example.myapplication.view;

import android.content.Intent;

import com.example.myapplication.L;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.data.entity.Diary;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.gun0912.tedonactivityresult.TedOnActivityResult;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class conversation_main extends BaseActivity<ActivityMainBinding> {
    private conversation_DiaryAdapter adapter;
    private boolean isLike = false;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreated() {
        L.i("::MainActivity onViewCreated");

        adapter = new conversation_DiaryAdapter(this, new conversation_DiaryAdapter.Delegate() {
            @Override
            public void onHeartClick(int position,Diary diary) {
                if(diary.isScrap()){
                    diary.setScrapYn("N");
                }else{
                    diary.setScrapYn("Y");
                }

                adapter.updateItem(position,diary);
                updateDiary(diary);

            }

            @Override
            public void onReplyClick(Diary diary) {
                Intent intent = new Intent(conversation_main.this, conversation_Activity.class);
                intent.putExtra("EXTRA_DIARY_ITEM", diary);
                startActivity(intent);
            }
        });

        binding.rvContent.setHasFixedSize(true);
        binding.rvContent.setAdapter(adapter);


        loadDiaryItems();

        binding.fab.setOnClickListener(v -> {
            //하단 업로드 아이콘 클릭시시
            //업로드화면에서 이미지를 선택했다면
            TedOnActivityResult.with(this)
                    .setIntent(new Intent(conversation_main.this, conversation_upload.class))
                    .setListener((resultCode, data) -> {
                        if (resultCode == RESULT_OK) {
                            loadDiaryItems();
                        }
                    })
                    .startActivityForResult();
        });

    }

    private void loadDiaryItems() {
        compositeDisposable.add(
                diaryDao.getAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result -> {
                            adapter.updateItems(result);
                        }, error -> {

                        }));
    }

    private void updateDiary(Diary diary){
        compositeDisposable.add(diaryDao.createOrUpdate(diary)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

}