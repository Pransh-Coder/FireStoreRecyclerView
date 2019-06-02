package com.example.firestorerecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    public List<Users> userList;

    public UsersListAdapter(List<Users> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameText.setText(userList.get(position).getName());
        holder.age.setText(userList.get(position).getAge());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView nameText;
        public TextView age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;

            nameText=mView.findViewById(R.id.name_text);
            age=mView.findViewById(R.id.age);
        }
    }
}
