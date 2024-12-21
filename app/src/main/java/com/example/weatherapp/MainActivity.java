package com.example.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


//    import from 54:56

//    39:27

    TextView cityName;
    Button search;
    TextView show;

    String url;

    class getWeather extends AsyncTask<String, void,String>{

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result=new StringBuilder();
            try{
                URL url=new URL(urls[0]);
                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
                urlConnection.connect();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName=findViewById(R.id.cityName);
        search=findViewById(R.id.search);
        show=findViewById(R.id.weather);

        final String[] temp={""};

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button Clicked! ", Toast.LENGTH_SHORT).show();
                String city=cityName.getText().toString();
                try {
                    if(city!=null){
                        url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=069b4aa36329f8b3dbb9ff87d0383e7c";
                    }else{
                        Toast.makeText(MainActivity.this, "Enter City", Toast.LENGTH_SHORT).show();
                    }
                    getWeather task=new getWeather();
                    temp[0]=task.execute(url).get();
                }catch (Exception e){
                    e.printStackTrace();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if (temp[0]==null){
                    show.setText("Can't find weather");
                }

            }
        });

    }
}