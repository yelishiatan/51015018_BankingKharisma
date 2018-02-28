package com.example.vivibook.bankingkharisma.activites;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.vivibook.bankingkharisma.R;
import com.example.vivibook.bankingkharisma.sql.DatabaseHelper;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;

     TextView textViewemail;
     TextView textViewNama;
     TextView textViewPassword;
     TextView textViewmoney;
    TextView textViewid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewemail = (TextView) findViewById(R.id.textemail);
        textViewNama = (TextView) findViewById(R.id.textname);
        textViewPassword = (TextView) findViewById(R.id.textpassword);
        textViewmoney = (TextView) findViewById(R.id.textMoney);
        textViewid = (TextView) findViewById(R.id.textid);

        String emailfromintent = getIntent().getStringExtra("EMAIL");

        textViewemail.setText(emailfromintent);

        openHelper = new DatabaseHelper(this);

        db=openHelper.getWritableDatabase();

        String email = emailfromintent;
//        cursor = db.rawQuery("SELECT * FROM / WHERE ? = ?", new String[] {});
        String whereClause = " WHERE " + DatabaseHelper.COLUMN_USER_EMAIL + " =?";

        cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_USER + " Where "+ DatabaseHelper.COLUMN_USER_EMAIL +" =?" , new String[] {email});
                //" WHERE " + DatabaseHelper.COLUMN_USER_EMAIL


        if(cursor != null){
            if(cursor.getCount()>=0) {
                //Log.d("cursor", cursor.getCount())
                cursor.moveToNext();
                textViewid.setText("ID : " + cursor.getString(0));
                textViewNama.setText("Nama : " + cursor.getString(1));
                textViewemail.setText("Email : " + cursor.getString(2));
                textViewPassword.setText("Password : " + cursor.getString(3));
                textViewmoney.setText("Money : " + cursor.getString(4));
            }
        }

        db.close();


    }


}
