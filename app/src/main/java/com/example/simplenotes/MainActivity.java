package com.example.simplenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    ArrayList<String> notes;
    RecyclerView rv;
    MyAdapter myAdapter;
    SharedPref sharedPref;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init vars
        editText = findViewById(R.id.inputtext);
        button = findViewById(R.id.addbtn);
        rv = findViewById(R.id.rv_list);

        //sharedpref  init
        sharedPref = new SharedPref(); // Very Important, i you FOrget to Init.. u get NULL Error!!
        context = getApplicationContext();
        notes = sharedPref.loaddata(context);
        if (notes == null){
            notes=new ArrayList<String>();
        }

        //recycler view and adapter
        myAdapter = new MyAdapter(notes,this);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //after Clicking Button WHat to dooo!!
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputnotes = editText.getText().toString();
                if (inputnotes.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Can't Add ,The Notes Are Empty", Toast.LENGTH_SHORT).show();
                } else {
                    notes.add(inputnotes+"");
                    myAdapter.notifyItemInserted(notes.size());
                    sharedPref.savedata(context,notes);
                    Toast.makeText(MainActivity.this, "Added SuccessFully", Toast.LENGTH_SHORT).show();
                    editText.setText("");

                }
            }
        });
    }

}