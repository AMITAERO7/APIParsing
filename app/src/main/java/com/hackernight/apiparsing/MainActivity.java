package com.hackernight.apiparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    private String url  = "https://jsonplaceholder.typicode.com/todos/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("JSON","Json:"+response.getString("title"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.getMessage());
            }
        });


        //JSONArrayRequest
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/users", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("JSONArray Response","Json:"+response);

                for (int i= 0;i<response.length();i++){
                    try {
                        JSONObject jsonObjectFromArray = response.getJSONObject(i);
                        Log.d("JSONArray Response","Response: "+jsonObjectFromArray.getString("title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JSONArray Error","Error:"+error);
            }
        });

        //requestQueue.add(jsonObjectRequest);
        requestQueue.add(jsonArrayRequest);
/*

        JsonArrayRequest jsonArrayRequest =  new JsonArrayRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/users", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d("Json Response",""+response);
                        for (int i = 0 ;i<2;i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Log.d("Username: "," "+jsonObject.getString("name"));

                                JSONObject addressjsonObject = jsonObject.getJSONObject("address");
                                Log.d("Street:",""+addressjsonObject.getString("street"));
                                JSONObject geojsonobject = addressjsonObject.getJSONObject("geo");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Json Error",""+error);
            }
        });
*/

        requestQueue.add(jsonArrayRequest);

    }

}