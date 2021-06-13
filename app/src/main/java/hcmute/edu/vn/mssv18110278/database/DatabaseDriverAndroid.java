package hcmute.edu.vn.mssv18110278.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

import java.sql.Blob;

import hcmute.edu.vn.mssv18110278.Security.PasswordHelpers;

import static android.os.Build.ID;

public class DatabaseDriverAndroid extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DB_salecoffee40.db";

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
                + "IMAGE BLOB  NOT NULL,"
                + "FOREIGN KEY(ROLE) REFERENCES ROLES(ID))";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE USERPW "
                + "(USERID INTEGER PRIMARY KEY NOT NULL,"
                + "PASSWORD CHAR(64),"
                + "FOREIGN KEY(USERID) REFERENCES USER(ID))";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE CATEGORY "
                + "(ID INTEGER PRIMARY KEY NOT NULL,"
                + "NAME CHAR(64) NOT NULL)";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE ITEMS "
                + "(ID INTEGER PRIMARY KEY NOT NULL,"
                + "IDCATEGORY INTEGER NOT NULL,"
                + "NAME CHAR(64) NOT NULL,"
                + "PRICE INTERGER NOT NULL,"
                + "DETAIL TEXT NOT NULL,"
                + "STATUS INTERGER  NOT NULL,"
                + "IMAGE BLOB  NOT NULL,"
                + "FOREIGN KEY(IDCATEGORY) REFERENCES CATEGORY(ID))";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE ORDERS "
                + "(ID INTEGER PRIMARY KEY NOT NULL,"
                + "USERID INTEGER NOT NULL,"
                + "TOTALPRICE TEXT NOT NULL,"
                + "STATE INTERGER NOT NULL,"
                + "DATE STRING,"
                + "ADDRESS STRING,"
                + "PHONE STRING,"
                + "FOREIGN KEY(USERID) REFERENCES USERS(ID))";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE DETAILORDERS "
                + "(IDORDER INTEGER  NOT NULL,"
                + "IDITEM INTEGER NOT NULL,"
                + "AMOUNT INTEGER NOT NULL,"
                + "TOTALPRICE TEXT NOT NULL,"
                + "STATUS INTERGER NOT NULL,"
                + "PRIMARY KEY(IDORDER, IDITEM),"
                + "FOREIGN KEY(IDITEM) REFERENCES ITEMS(ID),"
                + "FOREIGN KEY(IDORDER) REFERENCES ORDERS(ID))";
        sqLiteDatabase.execSQL(sql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ROLES");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USERS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USERPW");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CATEGORY");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ITEMS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ORDERS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DETAILORDERS");

        onCreate(sqLiteDatabase);
    }
    protected Cursor getRoles() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ROLES;", null);
    }
    public Cursor getNameCategory(String cate) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM CATEGORY WHERE NAME = ?",  new String[]{String.valueOf(cate)});
    }
    public Cursor getAllUer() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USERS;", null);
    }
    public Cursor getAllItem() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ITEMS;", null);
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
    protected Cursor getItem(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ITEMS WHERE ID = ?",  new String[]{String.valueOf(id)});

    }
    public Cursor getAllCategory() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM CATEGORY;", null);
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

    public void insertNewProduct(int id_category,String item_name, int item_price, String item_detail,int status ,byte[] image) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDCATEGORY", id_category);
        contentValues.put("NAME", item_name);
        contentValues.put("PRICE", item_price);
        contentValues.put("DETAIL", item_detail);
        contentValues.put("STATUS", status);
        contentValues.put("IMAGE", image);

        sqLiteDatabase.insert("ITEMS", null, contentValues);
        sqLiteDatabase.close();


    }

    public long insertNewCategory(String category) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", category);

        long id = sqLiteDatabase.insert("CATEGORY", null, contentValues);
        sqLiteDatabase.close();
        return id;

    }


    public void updateItem(int id,int idcate, String parse_employee_update_item_name, int parse_employee_update_item_price, String parse_employee_update_item_detail, int status) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDCATEGORY", idcate);
        contentValues.put("NAME", parse_employee_update_item_name);
        contentValues.put("PRICE", parse_employee_update_item_price);
        contentValues.put("DETAIL", parse_employee_update_item_detail);
        contentValues.put("STATUS", status);

        sqLiteDatabase.update("ITEMS", contentValues, "ID = ?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();

    }

    public Cursor getItembyName(String search) {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = " select * from ITEMS where NAME like ?";
        Cursor cursor = db.rawQuery(qry,new String[]{"%"+search+"%"});
        return cursor;
    }

    public void deleteItem(int ProductID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM ITEMS where ID = ?", new String[]{String.valueOf(ProductID)});
    }
}

