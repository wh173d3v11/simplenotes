package com.example.simplenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    ArrayList<String> notes;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init vars
        editText = findViewById(R.id.inputtext);
        button = findViewById(R.id.addbtn);
        rv = findViewById(R.id.rv_list);
        notes = new ArrayList<String>();

        //after Clicking Button WHat to dooo!!
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputnotes = editText.getText().toString();
                if (inputnotes.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Can't Add The Notes Are Empty", Toast.LENGTH_SHORT).show();
                } else {
                    notes.add(inputnotes);
                    MyAdapter myAdapter = new MyAdapter(notes);
                    rv.setAdapter(myAdapter);
                    rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    Toast.makeText(MainActivity.this, "Added SuccessFully", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}