package com.example.orbital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.orbital.Model.ContactModel;
import com.example.orbital.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orbital.Model.Message;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    final static int MSG_TYPE_LEFT = 1;
    final static int MSG_TYPE_RIGHT = 2;
    ArrayList<Message> messageList;

    private Context mContext;

    public MessageAdapter(){
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.outgoing_chat,parent,false);
            return new MessageAdapter.MessageViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.incoming_chat,parent,false);
            return new MessageAdapter.MessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageViewHolder holder, final int position) {
        Message message = messageList.get(position);
        holder.show_message.setText(message.getMessage());

        //Remember to set up for profile pic
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(messageList.get(position).getSenderID() == LoginActivity.USER_ID) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }

    public void setMessage(Context mContext, ArrayList<Message> messageList) {
        this.mContext = mContext;
        this.messageList =messageList;
        notifyDataSetChanged();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
        public CircleImageView profile_image;

        MessageViewHolder(View itemview){
            super(itemview);
            profile_image = itemview.findViewById(R.id.profile_image);
            show_message = itemview.findViewById(R.id.show_message_current_user);


        }
    }
}
