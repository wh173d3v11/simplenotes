package com.example.simplenotes;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPref{
    public String myname(){
        return "dinesh";
    }
    protected void savedata(Context context,ArrayList<String> notes) {
        SharedPreferences sharedPreferences  = context.getSharedPreferences("shared preferences",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(notes);
        editor.putString("my_notes",json);
        editor.apply();
    }
    protected ArrayList<String> loaddata(Context context){
        ArrayList<String> notes;
        SharedPreferences sharedPreferences  = context.getSharedPreferences("shared preferences",context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("my_notes",null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        notes = gson.fromJson(json, type);
        return notes;
    }
}
