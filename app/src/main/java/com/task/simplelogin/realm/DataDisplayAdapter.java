package com.task.simplelogin.realm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.task.simplelogin.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

class DataDisplayAdapter extends RecyclerView.Adapter<DataDisplayAdapter.MyViewHolder> {
    private Context applicationContext;
    private List<DataModel> users;
    public DataDisplayAdapter(Context applicationContext, RealmResults<DataModel> users) {
        this.applicationContext = applicationContext;
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_display_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (users.get(position) != null) {
            holder.name.setText(users.get(position).getName());
            holder.age.setText(users.get(position).getAge());
            holder.city.setText(users.get(position).getCity());
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void updateData(RealmResults<DataModel> result) {
        users = new ArrayList<DataModel>(result);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,age,city;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            city = itemView.findViewById(R.id.city);
        }
    }
}
