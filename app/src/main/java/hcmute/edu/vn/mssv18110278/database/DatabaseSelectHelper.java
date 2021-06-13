package hcmute.edu.vn.mssv18110278.database;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Category;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.Users.Admin.AdminActivity;

public class DatabaseSelectHelper {
    public static int getRoleIdFromName(String roleName, Context context) {
        for (int roleId : DatabaseSelectHelper.getRoleIds(context)) {
            if (roleName.contentEquals(DatabaseSelectHelper.getRoleName(roleId, context))) {
                return roleId;
            }
        }
        return -1;
    }
    public static List<Integer> getRoleIds(Context context) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(context);
        Cursor cursor = myDB.getRoles();
        List<Integer> ids = new ArrayList<>();

        while (cursor.moveToNext()) {
            ids.add(cursor.getInt(cursor.getColumnIndex("ID")));
        }
        cursor.close();
        myDB.close();
        return ids;
    }
    public static String getRoleName(int roleId, Context context) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(context);
        String role = null;
        role = myDB.getRole(roleId);
        myDB.close();
        return role;
    }
    public static String getPassword(int userId, Context context) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(context);

        String password = null;
        password = myDB.getPassword(userId);
        myDB.close();
        return password;
    }
    public static int getUserRoleId(String username, Context context) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(context);
        int roleId = -1;
        roleId = myDB.getUserRole(username);
        myDB.close();
        return roleId;
    }
    public static User getUserDetails(String username, Context context) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(context);

        Cursor cursor = null;
        User user = null;
        cursor = myDB.getUserDetails(username);

        if (cursor.moveToFirst()) {
            user = new User(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getInt(3),cursor.getBlob(4));
        }
        cursor.close();
        myDB.close();
        return user;
    }

    public static List<Category> getCategory(String cate,Context context) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(context);
        Cursor cursor = myDB.getNameCategory(cate);
        List<Category> cates = new ArrayList<>();

        while (cursor.moveToNext()) {
            cates.add(new Category(cursor.getInt(0),cursor.getString(1)));
        }
        cursor.close();
        myDB.close();
        return cates;
    }

    public static List<String> getUsers(Context context) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(context);
        Cursor cursor = myDB.getAllUer();
        List<String> names = new ArrayList<>();

        while (cursor.moveToNext()) {
            names.add(cursor.getString (cursor.getColumnIndex("USERNAME")));
        }
        cursor.close();
        myDB.close();
        return names;

    }

    public static List<Item> getAllItem(Context context) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(context);
        Cursor cursor = myDB.getAllItem();
        List<Item> items = new ArrayList<>();
        while (cursor.moveToNext()) {
            items.add(new Item(cursor.getInt(0),cursor.getInt(1), cursor.getString(2),
                    cursor.getInt(3), cursor.getString(4),cursor.getInt(5), cursor.getBlob(6)));
        }
        cursor.close();
        myDB.close();
        return items;
    }

    public static Item getItem(int id, Context context) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(context);
        Item item=null;
        Cursor cursor = myDB.getItem(id);
        if (cursor.moveToFirst()) {
            item= new Item(cursor.getInt(0),cursor.getInt(1), cursor.getString(2),
                    cursor.getInt(3), cursor.getString(4),cursor.getInt(5), cursor.getBlob(6));
        }
        cursor.close();
        myDB.close();
        return item;

    }

    public static List<String> getAllCategory(Context context) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(context);
        Cursor cursor = myDB.getAllCategory();
        List<String> namecates = new ArrayList<>();
        while (cursor.moveToNext()) {
            namecates.add(new String(cursor.getString(1)));
        }
        cursor.close();
        myDB.close();
        return namecates;
    }

    public static List<Item> getItembyname(String search, Context context) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(context);
        Cursor cursor = myDB.getItembyName(search);
        List<Item> items = new ArrayList<>();
        while (cursor.moveToNext()) {
            items.add(new Item(cursor.getInt(0),cursor.getInt(1), cursor.getString(2),
                    cursor.getInt(3), cursor.getString(4),cursor.getInt(5), cursor.getBlob(6)));

        }
        cursor.close();
        myDB.close();
        return items;
    }
}
