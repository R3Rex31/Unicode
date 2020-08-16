package com.example.unicodetasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Task1 extends AppCompatActivity
{
    private int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);
        Button set = (Button) findViewById(R.id.set);
        Button clear = (Button) findViewById(R.id.clear);
        final EditText name = (EditText) findViewById(R.id.display);
        final EditText email = (EditText) findViewById(R.id.mail);
        final EditText phone = (EditText) findViewById(R.id.phone);
        Button imgload = (Button) findViewById(R.id.upload);
        imgload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        set.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String sname = name.getText().toString().trim();
                String smail = email.getText().toString().trim();
                String sphone = phone.getText().toString().trim();
                Intent intent = new Intent(getApplicationContext(),Task2.class);
                intent.putExtra("name",sname);
                intent.putExtra("mail",smail);
                intent.putExtra("phone",sphone);
                if(sname.isEmpty()||smail.isEmpty()||sphone.isEmpty()) 
                {
                    Toast.makeText(Task1.this, "Field is Empty", Toast.LENGTH_LONG).show();
                }
                else
                {
                    startActivity(intent);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                name.setText("");
                email.setText("");
                phone.setText("");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.pfp);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
