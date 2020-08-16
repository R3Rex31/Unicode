package com.example.unicodetasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Task3b extends AppCompatActivity {

    String n,p;
    TextView fname,fphone;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3b);
        fname = findViewById(R.id.fname);
        fphone = findViewById(R.id.fphone);
        Intent intent = getIntent();
        n = intent.getStringExtra("name");
        p = intent.getStringExtra("phone");

        fname.setText(n);
        fphone.setText(p);
    }
}