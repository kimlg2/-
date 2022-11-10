package com.example.myapplication.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.myapplication.L;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseRecyclerAdapter;
import com.example.myapplication.base.BaseViewHolder;
import com.example.myapplication.data.entity.Diary;
import com.example.myapplication.databinding.PostItemRowBinding;


public class conversation_DiaryAdapter extends BaseRecyclerAdapter<Diary, conversation_DiaryAdapter.ViewHolder> {


    public interface Delegate {
        void onHeartClick(int Position, Diary diary);

        void onReplyClick(Diary diary);
    }

    private Delegate delegate;

    public conversation_DiaryAdapter(Context context, Delegate delegate) {
        super(context);
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new ViewHolder(parent);
    }

    public class ViewHolder extends BaseViewHolder<PostItemRowBinding, Diary> {

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.post_item_row);
        }

        @Override
        protected void bind(int position, Diary data) {
            binding.tvTitle.setText(data.content);
            binding.ivHeart.setImageResource(data.isScrap() ? R.drawable.ic_baseline_favorite_24 : R.drawable.ic_baseline_favorite_border_24);

            L.i("::::::::::::data " + data);
            if(!data.path.isEmpty()){
                binding.ivPhoto.setVisibility(View.VISIBLE);
                Glide.with(binding.ivPhoto.getContext())
                        .load(data.path)
                        .into(binding.ivPhoto);
            }else{
                binding.ivPhoto.setVisibility(View.GONE);
            }
            binding.ivP2.setOnClickListener(v -> {
                if (delegate != null) delegate.onHeartClick(position,data);
            });


            binding.ivHeart.setOnClickListener(v -> {
                if (delegate != null) delegate.onHeartClick(position,data);
            });

            binding.ivReply.setOnClickListener(v -> {
                if (delegate != null) delegate.onReplyClick(data);
            });
        }


    }
}
