package com.example.vivibook.bankingkharisma.activites;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vivibook.bankingkharisma.R;
import com.example.vivibook.bankingkharisma.sql.DatabaseHelper;
import com.example.vivibook.bankingkharisma.transfer;

/**
 * Created by VIVIBOOK on 1/26/2018.
 */

public class UsersActivity extends AppCompatActivity {

    private TextView textViewName;

    private AppCompatButton appCompatButtonprofile;
    private AppCompatButton appCompatButtontransfer;
    private AppCompatButton appCompatButtonbeli;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);



        appCompatButtonprofile = (AppCompatButton) findViewById(R.id.appCompatButtonprofile);
        appCompatButtontransfer = (AppCompatButton) findViewById(R.id.appCompatButtonTransfer);
        appCompatButtonbeli = (AppCompatButton) findViewById(R.id.appCompatButtonBeli);


        textViewName = (TextView) findViewById(R.id.text1);


        final String nameFromIntent = getIntent().getStringExtra("EMAIL");

        textViewName.setText("Welcome " + nameFromIntent);

        appCompatButtonprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailintent = new Intent(UsersActivity.this,Profile.class);
                emailintent.putExtra("EMAIL",nameFromIntent);
                startActivity(emailintent);
            }
        });

        appCompatButtontransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailintent = new Intent(UsersActivity.this,transfer.class);
                emailintent.putExtra("EMAIL",nameFromIntent);
                startActivity(emailintent);
            }
        });
    }

}


