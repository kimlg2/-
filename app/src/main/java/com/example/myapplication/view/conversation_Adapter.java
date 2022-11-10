package com.example.myapplication.view;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseRecyclerAdapter;
import com.example.myapplication.base.BaseViewHolder;
import com.example.myapplication.data.entity.Reply;
import com.example.myapplication.databinding.ItemCommentRowBinding;

public class conversation_Adapter extends BaseRecyclerAdapter<Reply, conversation_Adapter.ViewHolder> {

    public conversation_Adapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }
    //댓글을 관장하는 adapter


    static class ViewHolder extends BaseViewHolder<ItemCommentRowBinding, Reply> {

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_comment_row);
        }

        @Override
        protected void bind(int index, Reply data) {
            binding.msgText.setText(data.content);
        }
    }
}
