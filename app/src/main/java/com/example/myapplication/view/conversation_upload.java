package com.example.myapplication.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.L;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.data.entity.Diary;
import com.example.myapplication.databinding.ActivityUploadBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class conversation_upload extends BaseActivity<ActivityUploadBinding> {
    private Uri selectedUri = null;

    @Override
    protected int layoutRes() {
        return R.layout.activity_upload;
    }

    @Override
    protected void onViewCreated() {
        L.i("::::::::::UploadActivity");

        binding.btnPhotoSelect.setOnClickListener(v -> {
            //사진 버튼 클릭시..
            ImagePicker.with(conversation_upload.this)
                    .crop()
                    .compress(1024)
                    .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        });

        binding.btnUpload.setOnClickListener(v -> {
            //업로드 버튼 클릭시..
            String content = binding.editContent.getText() == null ? "" : binding.editContent.getText().toString();

            Diary diary = new Diary(content, selectedUri == null ? "" : selectedUri.toString(), System.currentTimeMillis());
            compositeDisposable.add(diaryDao.createOrUpdate(diary)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());

            Toast.makeText(this, "업로드 완료 되었습니다", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) return;

            Uri uri = data.getData();

            if (uri != null) {
                L.i("::::::uri " + uri);
                selectedUri = uri;
                binding.ivPhoto.setImageURI(uri);
            }
        }
    }
}
