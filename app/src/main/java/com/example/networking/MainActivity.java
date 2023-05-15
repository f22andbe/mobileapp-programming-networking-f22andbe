package com.example.networking;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "HTTPS_URL_TO_JSON_DATA_CHANGE_THIS_URL";
    private final String JSON_FILE = "mountains.json";
    private String mountainsJSON;
    private ArrayList<Mountain> mountainArrayList;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        new JsonFile(this, this).execute(JSON_FILE);
        gson = new Gson();
        mountainsJSON = readFile(JSON_FILE);

        Type type = new TypeToken<ArrayList<Mountain>>() {}.getType();
        mountainArrayList = gson.fromJson(mountainsJSON, type);
        Log.d("jsonfile", mountainsJSON);
        Log.d("arraylist", mountainArrayList.toString());

        for (int i=0; i < mountainArrayList.size(); i++){
            Log.d("loop: ", "name: " + mountainArrayList.get(i).getName() + "\nlocation: " + mountainArrayList.get(i).getLocation() +"\nheight: " +
                    mountainArrayList.get(i).getHeight());

        }

        MountainViewAdapter mAdapter = new MountainViewAdapter(this, mountainArrayList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
    }

    @SuppressWarnings("SameParameterValue")
    private String readFile(String fileName) {
        try {
            //noinspection CharsetObjectCanBeUsed
            return new Scanner(getApplicationContext().getAssets().open(fileName), Charset.forName("UTF-8").name()).useDelimiter("\\A").next();
        } catch (IOException e) {
            Log.e("MainActivity", "Could not read file: " + fileName);
            return null;
        }
    }

}
