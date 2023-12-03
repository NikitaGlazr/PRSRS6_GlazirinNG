package com.example.prsrs6_glazirinng;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView city, temp, condition;
    ImageView icon;
    ArrayList<Weather> wea= new ArrayList<Weather>();
    Adapter adapter;
    RecyclerView recyclerView;
    String gorod = "Novosibirsk", API_KEY = "84d7ede08ffd4d6f807154034230312";
    private RequestQueue mqueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.list);
        city=(TextView)findViewById(R.id.city);
        temp= (TextView)findViewById(R.id.temp);
        condition= (TextView)findViewById(R.id.condition);
        icon= (ImageView)findViewById(R.id.icon);
        getData();
    }

    public void getData()
    {
        mqueue= (RequestQueue) Volley.newRequestQueue(this);
        String URL = "https://api.weatherapi.com/v1/forecast.json?key="+API_KEY+ "&q="+gorod+"&days=7&aqi=no&alerts=no";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.d("mylog222222", "success" );
                    JSONObject location = response.getJSONObject("location");
                    String name = location.getString("name");
                    JSONObject current = response.getJSONObject("current");
                    JSONObject cond = current.getJSONObject("condition");
                    String text = cond.getString("text");
                    String url = "https:" + cond.getString("icon");
                    Picasso.get().load(url).fit().centerInside().into(icon);
                    condition.setText(text);
                    String temp_c = current.getString("temp_c");
                    temp.setText(temp_c);
                    city.setText(name);
                    JSONObject Forecast = response.getJSONObject("forecast");
                    JSONArray Forecastday = Forecast.getJSONArray("forecastday");

                    for(int i = 0; i<Forecastday.length(); i++)
                    {
                        JSONObject ForecastDayElement = Forecastday.getJSONObject(i);
                        String date = ForecastDayElement.getString("date");
                        JSONObject day = ForecastDayElement.getJSONObject("day");
                        JSONObject condition = day.getJSONObject("condition");
                        url = "https:" + condition.getString("icon");
                        String maxtemp_c = day.getString("maxtemp_c");
                        wea.add(new Weather(date,maxtemp_c,url));
                    }
                    adapter= new Adapter(MainActivity.this, wea);

                    recyclerView.setAdapter(adapter);
                } catch(JSONException e) {
                    Log.d("mylog222222", "error" );
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("mylog222222", error.getMessage());

            }
        });
        mqueue.add(request);
    }
}