package com.example.orbital;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.orbital.Model.chatModel;

import java.util.ArrayList;


public class ChatsFragment extends Fragment {

    RecyclerView chatRecView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        chatRecView = view.findViewById(R.id.chatRecView);
        chatRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Creating data manually
        ArrayList<chatModel> chats = new ArrayList<>();

        chats.add(new chatModel("Heinrich",R.drawable.brad_pitt, "Hi..."));
        chats.add(new chatModel("Heinrich",R.drawable.brad_pitt, "Hello..."));

        chatRecViewAdapter adapter = new chatRecViewAdapter();
        adapter.setChat(chats);

        chatRecView.setAdapter(adapter);

        return view;
    }

}
