package com.example.myapplication.view;

import android.text.TextUtils;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.L;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.data.entity.Diary;
import com.example.myapplication.data.entity.Reply;
import com.example.myapplication.databinding.ActivityCommentBinding;
import com.example.myapplication.utils.KeyboardUtilKt;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class conversation_Activity extends BaseActivity<ActivityCommentBinding> {

    private conversation_Adapter adapter;

    private Diary selectedItem;


    @Override
    protected int layoutRes() {
        return R.layout.activity_comment;
    }

    @Override
    protected void onViewCreated() {
        L.i("::MainActivity onViewCreated");

        selectedItem = (Diary) getIntent().getSerializableExtra("EXTRA_DIARY_ITEM");

        adapter = new conversation_Adapter(this);
        binding.rvComment.setAdapter(adapter);
        binding.rvComment.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        loadReply(selectedItem.id);

        binding.viewSend.setOnClickListener(v -> {
            //채팅 종이비행기 아이콘 클릭시. 리스트뷰에 댓글을 추가시켜준다..


            //공백 유효성 체크후.
            if (TextUtils.isEmpty(binding.editWriteReply.getText())) {
                return;
            }


            KeyboardUtilKt.hideKeyboard(this);
            String content = binding.editWriteReply.getText().toString();

            //Comment 객체에 해당 데이터를 담아.
            Reply reply = new Reply(binding.editWriteReply.getText().toString(), System.currentTimeMillis(), selectedItem.id);

            //리스트뷰에 추가한다.
            adapter.addItem(reply);
            insertReply(reply);
            binding.rvComment.scrollToPosition(adapter.getItemCount() - 1);
            binding.editWriteReply.setText("");
        });
    }

    private void insertReply(Reply reply){
        compositeDisposable.add(replyDao.createOrUpdate(reply)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }


    private void loadReply(int boardNo) {
        compositeDisposable.add(
                replyDao.getAll(boardNo)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result -> {
                            L.i(":::result " + result);
                            adapter.updateItems(result);
                        }, error -> {

                        }));
    }
}