package com.example.nivi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String MY_URL="https://raw.githubusercontent.com/iranjith4/radius-intern-mobile/master/users.json";
    private ArrayList<String> mImageList=new ArrayList<>();
    private ArrayList<String> text1=new ArrayList<>();
    private ArrayList<String> text2=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        initView();
    }
    void initView(){
        RecyclerView recv=findViewById(R.id.recyclerview);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,mImageList,text1,text2);
        recv.setAdapter(adapter);
        recv.setLayoutManager(new LinearLayoutManager(this));
    }
    void loadData(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest sr=new StringRequest(Request.Method.GET,
                MY_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJSON(response);
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

            }
        }
        );

        requestQueue.add(sr);
    }
    public void parseJSON(String json){
        try {
            JSONObject j1=new JSONObject(json);
            JSONArray j=j1.getJSONArray("results");
            for(int i=0;i<j.length();i++){
                JSONObject jsonObject=j.getJSONObject(i);
                JSONObject name=jsonObject.getJSONObject("name");
                String str=name.getString("title").toUpperCase();
                String firstName=name.getString("first");
                String secondName=name.getString("last");
                String n=str+"."+firstName+" "+secondName;
                text1.add(n);
                JSONObject age=jsonObject.getJSONObject("dob");
                String a=age.getString("age");
                text2.add("Age :"+a);
                JSONObject img=jsonObject.getJSONObject("picture");
                String image=img.getString("medium");
                mImageList.add(image);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
