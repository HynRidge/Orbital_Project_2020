package com.example.orbital;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orbital.Model.groupModel;

import java.util.ArrayList;


public class GroupsFragment extends Fragment {


    RecyclerView groupRecView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_groups, container, false);

        groupRecView = view.findViewById(R.id.groupRecView);
        groupRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<groupModel> groups = new ArrayList<>();


        groupRecViewAdapter adapter = new groupRecViewAdapter();
        adapter.setGroups(groups);

        groupRecView.setAdapter(adapter);
        return view;
    }
}
