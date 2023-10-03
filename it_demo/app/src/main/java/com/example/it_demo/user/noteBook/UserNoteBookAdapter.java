package com.example.it_demo.user.noteBook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_demo.R;
import com.example.it_demo.value.Message;

import java.util.ArrayList;
import java.util.List;

public class UserNoteBookAdapter extends RecyclerView.Adapter<UserNoteBookAdapter.ViewHolder>{
    List<Message> messageList = new ArrayList<>();

    Context context;

    public UserNoteBookAdapter(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_note_title, tv_note_msg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_note_title = itemView.findViewById(R.id.tv_note_title);
            tv_note_msg = itemView.findViewById(R.id.tv_note_msg);
        }

        void setData(int position){
            tv_note_title.setText(String.valueOf(messageList.get(position).getTitle()));
            tv_note_msg.setText(messageList.get(position).getMsg());
        }

    }

    @NonNull
    @Override
    public UserNoteBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note_book,parent,false);
        return new UserNoteBookAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserNoteBookAdapter.ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return messageList != null ? messageList.size() : 0;
    }

    public void setMessageList(List<Message> messageList){
        this.messageList = messageList;
    }
}
