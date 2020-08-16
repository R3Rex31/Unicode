package com.example.unicodetasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Task3 extends AppCompatActivity
{
    EditText cname,cphone;
    ArrayList<String> contact = new ArrayList<>();
    ArrayList<String> contactd = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);
        cname = findViewById(R.id.cname);
        cphone = findViewById(R.id.cphone);

        ListView lvcontacts = findViewById(R.id.lvcontact);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contact);
        Button add = findViewById(R.id.add);
        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                contact.add(cname.getText().toString());
                contactd.add(cphone.getText().toString());
                adapter.notifyDataSetChanged();
                cname.setText("");
                cphone.setText("");
            }
        };
        add.setOnClickListener(onClickListener);
        lvcontacts.setAdapter(adapter);

        lvcontacts.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent = new Intent(getApplicationContext(),Task3b.class);
                intent.putExtra("name",contact.get(i));
                intent.putExtra("phone",contactd.get(i));
                startActivity(intent);
            }
        });
    }
}