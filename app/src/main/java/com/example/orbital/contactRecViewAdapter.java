package com.example.orbital;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orbital.Model.ContactModel;
import com.example.orbital.Model.chatModel;

import java.util.ArrayList;

public class contactRecViewAdapter extends RecyclerView.Adapter<contactRecViewAdapter.ViewHolder> {

    ArrayList<ContactModel> contacts = new ArrayList<>();

    public contactRecViewAdapter() {}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.contact_name.setText(contacts.get(position).getContact_name());
        //Need t
//        holder.contact_profile_image.setImageResource(contacts.get(position).getImage_path());
    }

    @Override
    public int getItemCount() {
        return this.contacts.size();
    }

    public void setContacts(ArrayList<ContactModel> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView contact_name;
        private ImageView contact_profile_image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_name = itemView.findViewById(R.id.contact_name);
            contact_profile_image = itemView.findViewById(R.id.contact_profile_image);
        }
    }
}



