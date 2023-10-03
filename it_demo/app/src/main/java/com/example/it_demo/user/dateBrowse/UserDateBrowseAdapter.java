package com.example.it_demo.user.dateBrowse;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_demo.R;

import java.util.ArrayList;
import java.util.List;

public class UserDateBrowseAdapter extends RecyclerView.Adapter<UserDateBrowseAdapter.ViewHolder>{

    List<TotalRecordData> totalRecordList = new ArrayList<>();

    Context context;

    public UserDateBrowseAdapter(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name,tv_value;
        ImageView img_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_value = itemView.findViewById(R.id.tv_value);
            img_item = itemView.findViewById(R.id.img_item);

        }

        void setData(int position){
            String imageName ="ic_" + totalRecordList.get(position).getType() + "_light";
            Log.e("UserDateBrowseAdapter", "setData: " + imageName);
            int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
            img_item.setImageResource(resId);
            tv_name.setText(totalRecordList.get(position).getName());
            tv_value.setText(String.valueOf(totalRecordList.get(position).getValue()));
        }
    }

    @NonNull
    @Override
    public UserDateBrowseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_date_browse,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDateBrowseAdapter.ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return totalRecordList != null ? totalRecordList.size() : 0;
    }

    public void setTotalRecordList(List<TotalRecordData> totalRecordList){
        this.totalRecordList = totalRecordList;
    }

    public void deleteItem(int position) {
        notifyItemRemoved(position);
    }
}

