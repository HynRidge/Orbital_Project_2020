package com.example.orbital;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orbital.Model.groupModel;

import java.util.ArrayList;

public class groupRecViewAdapter extends RecyclerView.Adapter<groupRecViewAdapter.ViewHolder> {

    ArrayList<groupModel> groups = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.groupName.setText(groups.get(position).getGroupName());
        holder.groupImage.setImageResource(groups.get(position).getGroupImage());
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public void setGroups(ArrayList<groupModel> groups) {
        this.groups = groups;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView groupName;
        private ImageView groupImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.group_name);
            groupImage = itemView.findViewById(R.id.group_image);

        }
    }
}
