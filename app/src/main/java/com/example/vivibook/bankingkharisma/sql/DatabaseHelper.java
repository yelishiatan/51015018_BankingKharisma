package com.example.vivibook.bankingkharisma.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vivibook.bankingkharisma.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VIVIBOOK on 1/26/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserManager.db";

    public static final String TABLE_USER = "user";

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_PASSWORD = "user_password";
    public static final String COLUMN_USER_MONEY = "user_money";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS" + TABLE_USER;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    //    super(context, name, factory, version);
    //}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_USER + "( " +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_USER_EMAIL + " TEXT, " +
                COLUMN_USER_PASSWORD + " TEXT, " +
                COLUMN_USER_MONEY + " INTEGER " +
                ")";
        Log.e("query", query);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public List<User> findAll() {
        try {
            List<User> userList = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from " +
                    TABLE_USER, null);

            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    //user.setId(cursor.getInt(0));
                    user.setName(cursor.getString(0));
                    user.setEmail(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    user.setMoney(cursor.getString(3));
                    userList.add(user);
                } while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return userList;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(User user) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            // contentValues.put(COLUMN_USER_ID, user.getId());
            contentValues.put(COLUMN_USER_NAME, user.getName());
            contentValues.put(COLUMN_USER_EMAIL, user.getEmail());
            contentValues.put(COLUMN_USER_PASSWORD, user.getPassword());
            contentValues.put(COLUMN_USER_MONEY, user.getMoney());
            long rows = sqLiteDatabase.insert(TABLE_USER, null, contentValues);
            sqLiteDatabase.close();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean checkUser(String email, String password) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " =?" + " AND " + COLUMN_USER_PASSWORD + " =?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();

        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

}
