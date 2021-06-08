package hcmute.edu.vn.mssv18110278.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

import java.sql.Blob;

import hcmute.edu.vn.mssv18110278.Security.PasswordHelpers;

public class DatabaseDriverAndroid extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DB_salecoffee24.db";

    public DatabaseDriverAndroid(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE ROLES "
                + "(ID INTEGER PRIMARY KEY NOT NULL,"
                + "NAME TEXT NOT NULL)";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE USERS "
                + "(ID INTEGER PRIMARY KEY NOT NULL,"
                + "USERNAME TEXT NOT NULL,"
                + "GMAIL TEXT NOT NULL,"
                + "ROLE INTERGER NOT NULL,"
                + "IMAGE BLOB    NOT NULL,"
                + "FOREIGN KEY(ROLE) REFERENCES ROLES(ID))";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE USERPW "
                + "(USERID INTEGER PRIMARY KEY NOT NULL,"
                + "PASSWORD CHAR(64),"
                + "FOREIGN KEY(USERID) REFERENCES USER(ID))";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE ITEMS "
                + "(ID INTEGER PRIMARY KEY NOT NULL,"
                + "NAME CHAR(64) NOT NULL,"
                + "PRICE TEXT NOT NULL)";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE INVENTORY "
                + "(ITEMID INTEGER PRIMARY KEY NOT NULL,"
                + "QUANTITY INTEGER NOT NULL,"
                + "FOREIGN KEY(ITEMID) REFERENCES ITEMS(ID))";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE SALES "
                + "(ID INTEGER PRIMARY KEY NOT NULL,"
                + "USERID INTEGER NOT NULL,"
                + "TOTALPRICE TEXT NOT NULL,"
                + "FOREIGN KEY(USERID) REFERENCES USERS(ID))";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE ITEMIZEDSALES "
                + "(SALEID INTEGER NOT NULL,"
                + "ITEMID INTEGER NOT NULL,"
                + "QUANTITY INTEGER NOT NULL,"
                + "FOREIGN KEY(SALEID) REFERENCES SALES(ID),"
                + "FOREIGN KEY(ITEMID) REFERENCES ITEMS(ID),"
                + "PRIMARY KEY(SALEID, ITEMID))";
        sqLiteDatabase.execSQL(sql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ROLES");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USERS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USERPW");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ITEMS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS INVENTORY");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SALES");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ITEMIZEDSALES");


        onCreate(sqLiteDatabase);
    }
    protected Cursor getRoles() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ROLES;", null);
    }
    protected String getRole(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT NAME FROM ROLES WHERE ID = ?",
                new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        String value = cursor.getString(cursor.getColumnIndex("NAME"));
        cursor.close();
        return value;

    }
    protected String getPassword(int userId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT PASSWORD FROM USERPW WHERE USERID = ?",
                new String[]{String.valueOf(userId)});
        cursor.moveToFirst();
        String result = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return result;
    }
    protected int getUserRole(String username) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT ROLE FROM USERS WHERE USERNAME = ?",
                new String[]{String.valueOf(username)});
        cursor.moveToFirst();
        int result = cursor.getInt(cursor.getColumnIndex("ROLE"));
        cursor.close();
        return result;
    }
    protected String getUserName(int userId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT USERNAME FROM USERS WHERE ID = ?",
                new String[]{String.valueOf(userId)});
        cursor.moveToFirst();
        String result = cursor.getString(cursor.getColumnIndex("USERNAME"));
        cursor.close();
        return result;
    }
    protected Cursor getUserDetails(String username) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE USERNAME = ?",
                new String[]{String.valueOf(username)});
    }
    protected long insertNewUser(String username, String email, String password, byte[] image, int role) {
        long id = insertUser(username, email, role ,image);
        insertPassword(password, (int) id);
        return id;
    }
    private long insertUser(String username, String email, int role, byte[] image) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", username);
        contentValues.put("GMAIL", email);
        contentValues.put("ROLE", role);
        contentValues.put("IMAGE", image);

        long id = sqLiteDatabase.insert("USERS", null, contentValues);
        sqLiteDatabase.close();

        return id;
    }
    private void insertPassword(String password, int userId) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        password = PasswordHelpers.passwordHash(password);
        contentValues.put("USERID", userId);
        contentValues.put("PASSWORD", password);
        sqLiteDatabase.insert("USERPW", null, contentValues);
        sqLiteDatabase.close();
    }
    protected long insertRole(String role) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", role);
        long id = sqLiteDatabase.insert("ROLES", null, contentValues);
        sqLiteDatabase.close();
        return id;
    }

}

