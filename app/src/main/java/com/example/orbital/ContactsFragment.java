package com.example.orbital;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orbital.Model.ContactModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class ContactsFragment extends Fragment {

    String BASE_URL = "http://172.31.123.95:8000/account/";

    RequestQueue queue;

    int CURRENT_USER_ID = LoginActivity.USER_ID;

    ArrayList<Integer> contactsID = new ArrayList<>();

    RecyclerView contactRecView;

    ArrayList<ContactModel> contacts = new ArrayList<>();

    contactRecViewAdapter adapter = new contactRecViewAdapter();


//    @Override
//    public void onStart() {
//        super.onStart();
//    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        queue = Volley.newRequestQueue(requireContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        contactRecView = view.findViewById(R.id.contactRecView);
        contactRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        requestForCurrentUserContact();



        return view;
    }

    private void requestForCurrentUserContact() {
        contactsID.clear();
        String url = BASE_URL + "get-contact/" + CURRENT_USER_ID +"/";
        System.out.println("AFter URL");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("get current user contact succeed");
                try {
                    System.out.println("IN HERE");
                    JSONArray jsonArray = new JSONArray(response);
                    System.out.println("AFTER JSPON ARRAY");
                    for(int i = 0 ; i < jsonArray.length() ; i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        contactsID.add(jo.getInt("contact"));
                    }
                    getContactNickname();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Get Contact failed, please try again!!", Toast.LENGTH_SHORT).show();
                System.out.println(error.toString()+ " "+ 1);
            }
        });
        queue.add(stringRequest);
    }

    private void getContactNickname() {
        contacts.clear();
        for(final int contactID : contactsID) {
            String url = BASE_URL + "get-nickname/" + contactID +"/";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println("Get nickname succeed");
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject.getString("nickname").equals("")) {
                            contacts.add(new ContactModel("Anonymous User",R.drawable.defaultpic,contactID));
                        } else {
                            contacts.add(new ContactModel(jsonObject.getString("nickname"),R.drawable.defaultpic,contactID));
                        }

                        adapter.setContacts(contacts,getActivity());
                        contactRecView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Get Nickname failed, try again!!", Toast.LENGTH_SHORT).show();
                    System.out.println(error.toString() +" "+ 2);
                }
            });
            queue.add(stringRequest);
        }
    }

}
