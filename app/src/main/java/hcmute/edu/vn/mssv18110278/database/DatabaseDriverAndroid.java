package hcmute.edu.vn.mssv18110278.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

import java.sql.Blob;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.Security.PasswordHelpers;
import hcmute.edu.vn.mssv18110278.Users.User.HomeActivity;

import static android.os.Build.ID;

public class DatabaseDriverAndroid extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DB_salecoffee12.db";

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
                + "TOTALPRICE INTERGER NOT NULL,"
                + "STATUS INTERGER NOT NULL,"
                + "DATE STRING,"
                + "ADDRESS STRING,"
                + "PHONE CHAR(64),"
                + "FOREIGN KEY(USERID) REFERENCES USERS(ID))";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE DETAILORDERS "
                + "(IDORDER INTEGER  NOT NULL,"
                + "IDITEM INTEGER NOT NULL,"
                + "AMOUNT INTEGER NOT NULL,"
                + "TOTALPRICE INTERGER NOT NULL,"
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
    public Cursor getallorder() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ORDERS WHERE STATUS = ?" , new String[]{String.valueOf(1)});
        return cursor;
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
    public Cursor getUserbyID(int userid) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE ID = ?",
                new String[]{String.valueOf(userid)});
    }
    public Cursor getbyIDOrder(int idOrder) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM DETAILORDERS WHERE IDORDER = ?",
                new String[]{String.valueOf(idOrder)});
    }
    public Cursor getDetailOrder(int idOrder, Item item) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM DETAILORDERS WHERE IDORDER = ? and IDITEM = ?",
                new String[]{String.valueOf(idOrder), String.valueOf(item.getId())});
    }
    protected Cursor getItem(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ITEMS WHERE ID = ?",  new String[]{String.valueOf(id)});

    }
    public Cursor getItembyCate(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ITEMS WHERE IDCATEGORY = ?",  new String[]{String.valueOf(id)});
    }
    public Cursor GetOrderIdOrder(int idOrder) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM ORDERS WHERE ID = ?",  new String[]{String.valueOf(idOrder)});

    }

    public Cursor getOrderbyIDUser(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ORDERS WHERE USERID = ? and STATUS = ?",
                new String[]{String.valueOf(id), String.valueOf(1)});
        return cursor;
    }
    public Cursor getIembyCate_Name(int idseach, String search) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(" select * from ITEMS where NAME like ? and IDCATEGORY = ?", new String[]{String.valueOf("%"+search+"%"), String.valueOf(idseach)});

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
    public void updatePassword(String password1) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        password1 = PasswordHelpers.passwordHash(password1);
        contentValues.put("PASSWORD", password1);
        contentValues.put("USERID", HomeActivity.user.getId());

        sqLiteDatabase.update("USERPW", contentValues, "USERID = ?", new String[]{String.valueOf(HomeActivity.user.getId())});
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
    public int insertNewCart(int iDuser, int i, int i1) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERID", iDuser);
        contentValues.put("TOTALPRICE", i);
        contentValues.put("STATUS", i1);
        contentValues.putNull("DATE");
        contentValues.putNull("ADDRESS");
        contentValues.putNull("PHONE");

        long id = sqLiteDatabase.insert("ORDERS", null, contentValues);
        sqLiteDatabase.close();
        return (int)id;
    }
    public void updateCart(Order order, int total) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERID", order.getUSERID());
        contentValues.put("TOTALPRICE", total);
        contentValues.put("STATUS", order.getSTATUS());
        contentValues.putNull("DATE");
        contentValues.putNull("ADDRESS");
        contentValues.putNull("PHONE");


        sqLiteDatabase.update("ORDERS", contentValues, "ID = ?", new String[]{String.valueOf(order.getID())});
        sqLiteDatabase.close();
    }
    public void updateCartPayment(Order order, String time, String phone, String address) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERID", order.getUSERID());
        contentValues.put("TOTALPRICE", order.getTOTALPRICE());
        contentValues.put("STATUS", 1);
        contentValues.put("DATE",time);
        contentValues.put("ADDRESS",address);
        contentValues.put("PHONE",phone);


        sqLiteDatabase.update("ORDERS", contentValues, "ID = ?", new String[]{String.valueOf(order.getID())});
        sqLiteDatabase.close();
    }
    public void insertNewDetailCart(int idOrder, int id, int i, int price) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDORDER", idOrder);
        contentValues.put("IDITEM", id);
        contentValues.put("AMOUNT", i);
        contentValues.put("TOTALPRICE", price);
        sqLiteDatabase.insert("DETAILORDERS", null, contentValues);
        sqLiteDatabase.close();

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
    public void updateDetailCart(DetailOrders detailOrders,Item item,int soluong) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDORDER", detailOrders.getIdorder());
        contentValues.put("IDITEM", detailOrders.getIditem());
        contentValues.put("AMOUNT", detailOrders.getMount()+soluong);
        contentValues.put("TOTALPRICE", (detailOrders.getMount()+soluong)*item.getPrice());

        sqLiteDatabase.update("DETAILORDERS", contentValues, "IDORDER = ? and IDITEM = ?",
        new String[]{String.valueOf(detailOrders.getIdorder()), String.valueOf(detailOrders.getIditem())});
        sqLiteDatabase.close();

    }




    public Cursor getItembyName(String search) {
        SQLiteDatabase db = this.getReadableDatabase();
        String qry = " select * from ITEMS where NAME like ?";
        Cursor cursor = db.rawQuery(qry,new String[]{"%"+search+"%"});
        return cursor;
    }


    public void deleteItem(int ProductID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM ITEMS where ID = ?", new String[]{String.valueOf(ProductID)});
    }
    public void deleteDetailOrder(int idorder, int iditem) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM DETAILORDERS where IDORDER = ? and IDITEM = ?", new String[]{String.valueOf(idorder), String.valueOf(iditem)});
    }
    public void deleteCart(int idOrder) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM ORDERS where ID = ?", new String[]{String.valueOf(idOrder)});
    }

    public int GetCartUser(int iDuser) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        int result= -1 ;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ORDERS WHERE USERID = ? and STATUS = ?",
                new String[]{String.valueOf(iDuser), String.valueOf(0)});
        cursor.moveToFirst();
        try {
            result = cursor.getInt(cursor.getColumnIndex("ID"));
        }catch (Exception e)
        {

        }
        cursor.close();
        return result;

    }
    public Cursor getbyCart(User user) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ORDERS WHERE USERID = ? and STATUS = ?",
                new String[]{String.valueOf(user.getId()), String.valueOf(0)});
        return cursor;
    }

    public int getExistIDItemCart(int idOrder, Item item) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DETAILORDERS WHERE IDORDER = ? and IDITEM = ?",
                new String[]{String.valueOf(idOrder), String.valueOf(item.getId())});
        cursor.moveToFirst();
        int result =-1;
        try {
            result = cursor.getInt(cursor.getColumnIndex("IDORDER"));
        }catch (Exception e)
        {

        }
        cursor.close();
        return result;
    }


    public Cursor getreportbyday() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT strftime('%d-%m-%Y', DATE) AS DAY,SUM(TOTALPRICE) AS VALUE FROM ORDERS where status =? group by  strftime('%d-%m-%Y', DATE)",
                new String[]{String.valueOf(1)});
        return cursor;
    }

    public Cursor getreportbymonth() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT strftime('%m-%Y', DATE) AS DAY,SUM(TOTALPRICE) AS VALUE FROM ORDERS where status =? group by  strftime('%m-%Y', DATE)",
                new String[]{String.valueOf(1)});
        return cursor;
    }

    public Cursor getreportbyyear() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT strftime('%Y', DATE) AS DAY,SUM(TOTALPRICE) AS VALUE FROM ORDERS where status =? group by  strftime('%Y', DATE)",
                new String[]{String.valueOf(1)});
        return cursor;
    }
}

