package com.example.unicodetasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Task4b extends AppCompatActivity {
    String main, dets, city, temp, min, max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task4b);
        TextView tcity, tmain, tdets;
        Button back = findViewById(R.id.back);

        tcity = findViewById(R.id.tvcity);
        tmain = findViewById(R.id.tvw);
        tdets = findViewById(R.id.tvd);

        tcity.setText(Inten("city"));
        tmain.setText(Inten("main"));
        tdets.setText(Inten("desc"));

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(),Task4.class));
            }
        });
    }

    String Inten(String des)
    {
        Intent intent = getIntent();
        return intent.getStringExtra(des);
    }

}