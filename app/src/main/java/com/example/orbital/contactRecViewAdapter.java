package com.example.orbital;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orbital.Model.ContactModel;
import com.example.orbital.Model.chatModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class contactRecViewAdapter extends RecyclerView.Adapter<contactRecViewAdapter.ViewHolder> {

    ArrayList<ContactModel> contacts = new ArrayList<>();

    Context mContext;

    String BASE_URL = "http://172.31.123.95:8000/account/";
    int CURRENT_USER_ID = LoginActivity.USER_ID;
    int PRIVATE_CHAT = 1;

    int ROOM_ID;

    RequestQueue queue;
    public contactRecViewAdapter() {}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        queue = Volley.newRequestQueue(mContext);

        holder.contact_name.setText(contacts.get(position).getContact_name());
        holder.contact_profile_image.setImageResource(contacts.get(position).getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRoomCreated(position);

                Intent intent = new Intent(mContext,MessageActivity.class);
                intent.putExtra("contactId",contacts.get(position).getContact_id());
                intent.putExtra("contactNickname",contacts.get(position).getContact_name());
                intent.putExtra("contactImage",contacts.get(position).getImage());

                mContext.startActivity(intent);
            }
        });
    }

    private void checkRoomCreated(final int position) {
        String url = BASE_URL +"find-room/" + CURRENT_USER_ID + "/"  +contacts.get(position).getContact_id()+ "/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray.isNull(0)) {
                        createRoom(position);
                    } else {
                        JSONObject jsonObject =  jsonArray.getJSONObject(0);
                        ROOM_ID = jsonObject.getInt("room");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "Request to find room failed!!", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }


    private void createParticipants(int position) {
        String url = BASE_URL +"participants/";
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(CURRENT_USER_ID);
        idList.add(contacts.get(position).getContact_id());
        for(final int id :idList) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println("User " +id  + " added to participants");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mContext, "Try to create new participants but failed", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("room", Integer.toString(ROOM_ID));
                    params.put("user", Integer.toString(id));
                    return params;
                }
            };
            queue.add(stringRequest);
        }
    }

    private void createRoom(final int position) {
        String url = BASE_URL +"room/";
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Room Created");
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    ROOM_ID = jsonObject.getInt("id");
                    System.out.println(ROOM_ID);
                    createParticipants(position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "Try to create room but error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =  new HashMap<>();
                params.put("name",CURRENT_USER_ID + " and " + contacts.get(position).getContact_id());
                params.put("type",Integer.toString(PRIVATE_CHAT));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public int getItemCount() {
        return this.contacts.size();
    }

    public void setContacts(ArrayList<ContactModel> contacts, Context mContext) {
        this.contacts = contacts;
        this.mContext = mContext;
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



