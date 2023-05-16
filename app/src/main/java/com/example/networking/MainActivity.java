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

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    //private final String JSON_FILE = "mountains.json";
    private String mountainsJSON;
    private ArrayList<Mountain> mountainArrayList;
    private Gson gson;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* get JSON data from webservice */

        new JsonTask(this).execute(JSON_URL);

        /* Read in recyclerview */
        recyclerView = findViewById(R.id.recyclerView);
    }

    /* parse JSON data */
    @Override
    public void onPostExecute(String json) {
        gson = new Gson();
        Type type = new TypeToken<ArrayList<Mountain>>() {}.getType();
        mountainArrayList = gson.fromJson(json, type);
        Log.d("onPostExecute(): ", mountainArrayList.toString());

        MountainViewAdapter mAdapter = new MountainViewAdapter(this, mountainArrayList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



}
