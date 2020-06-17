package com.example.orbital;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orbital.Model.chatModel;

import java.util.ArrayList;

public class chatRecViewAdapter extends RecyclerView.Adapter<chatRecViewAdapter.ViewHolder>{

    private ArrayList<chatModel> chat = new ArrayList<>();

    public chatRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chatName.setText(chat.get(position).getUserName());
        holder.chatImage.setImageResource(chat.get(position).getImagePath());
        holder.chatChats.setText(chat.get(position).getChatSnippet());
    }

    @Override
    public int getItemCount() {
        return chat.size();
    }

    public void setChat(ArrayList<chatModel> chat) {
        this.chat = chat;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView chatName;
        private ImageView chatImage;
        private TextView chatChats;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chatName = itemView.findViewById(R.id.chat_name);
            chatImage = itemView.findViewById(R.id.chat_image);
            chatChats = itemView.findViewById(R.id.chat_chats);
        }
    }
}
