package com.example.orbital;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orbital.Model.ContactModel;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {

    RecyclerView contactRecView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        contactRecView = view.findViewById(R.id.contactRecView);
        contactRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<ContactModel> contacts = new ArrayList<>();

        contactRecViewAdapter adapter = new contactRecViewAdapter();

        adapter.setContacts(contacts);

        contactRecView.setAdapter(adapter);

        return view;
    }
}
