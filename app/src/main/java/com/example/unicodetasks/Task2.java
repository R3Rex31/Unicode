package com.example.unicodetasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Task2 extends AppCompatActivity {

    String n, m, p;
    TextView text1, text2, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);
        final TextView text1 = findViewById(R.id.aname);
        final TextView text2 = findViewById(R.id.amail);
        final TextView text3 = findViewById(R.id.aphone);
        Button save = findViewById(R.id.save);
        Button delete = findViewById(R.id.delete);

        Intent intent = getIntent();
        n = intent.getStringExtra("name");
        m = intent.getStringExtra("mail");
        p = intent.getStringExtra("phone");


            SharedPreferences sp = getApplicationContext().getSharedPreferences("Pref", 0);
            SharedPreferences.Editor ed = sp.edit();
            if (sp.contains("name")) {
                n = sp.getString("name", "");
            }
            if (sp.contains("mail")) {
                m = sp.getString("mail", "");
            }
            if (sp.contains("phone")) {
                p = sp.getString("phone", "");
            }

            text1.setText(n);
            text2.setText(m);
            text3.setText(p);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getApplicationContext().getSharedPreferences("Pref", 0);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("name", n);
                ed.putString("mail", m);
                ed.putString("phone", p);
                ed.commit();
                Toast.makeText(Task2.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences sp = getApplicationContext().getSharedPreferences("Pref", 0);
                SharedPreferences.Editor ed = sp.edit();
                ed.remove("name");
                ed.remove("mail");
                ed.remove("phone");
                text1.setText("");
                text2.setText("");
                text3.setText("");
                ed.commit();
                Toast.makeText(Task2.this, "Data Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

}