package com.example.vivibook.bankingkharisma;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vivibook.bankingkharisma.sql.DatabaseHelper;

public class transfer extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;

    TextView textViewemail;
    TextView textViewmoneyold;
    TextView textViewmoneynew;

    EditText EditMoney;

    Button btnhitung;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        textViewemail = (TextView) findViewById(R.id.textemail);
        textViewmoneyold = (TextView) findViewById(R.id.textMoneyold);
        textViewmoneynew = (TextView) findViewById(R.id.textMoneynew);

        EditMoney = (EditText) findViewById(R.id.textInputEditTextMoney);

        btnhitung = (Button) findViewById(R.id.appCompatButtonHitung);

        String emailfromintent = getIntent().getStringExtra("EMAIL");

        textViewemail.setText(emailfromintent);

        openHelper = new DatabaseHelper(this);

        db=openHelper.getWritableDatabase();

        final String email = emailfromintent;
//        cursor = db.rawQuery("SELECT * FROM / WHERE ? = ?", new String[] {});
        String whereClause = " WHERE " + DatabaseHelper.COLUMN_USER_EMAIL + " =?";

        cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_USER + " Where "+ DatabaseHelper.COLUMN_USER_EMAIL +" =?" , new String[] {email});
        //" WHERE " + DatabaseHelper.COLUMN_USER_EMAIL


        if(cursor != null){
            if(cursor.getCount()>=0) {
                //Log.d("cursor", cursor.getCount())
                cursor.moveToNext();
                textViewemail.setText("Email : " + cursor.getString(2));
                textViewmoneyold.setText(cursor.getString(4));
            }
        }

        db.close();


    btnhitung.setOnClickListener(new View.OnClickListener() {
        public String tampung1;
        public String tampung2;
        public Double tampung3;
        public Double tampung4;
        public Double tampung5;
        public String tampung6;
        @Override
        public void onClick(View view) {
            tampung1 = textViewmoneyold.getText().toString();
            tampung2 = EditMoney.getText().toString();

            tampung3 = Double.parseDouble(tampung1);
            tampung4 = Double.parseDouble(tampung2);

            tampung5 = tampung3 - tampung4 ;

            textViewmoneynew.setText("RP. " + tampung5.toString());
        }
    });


    }


}
