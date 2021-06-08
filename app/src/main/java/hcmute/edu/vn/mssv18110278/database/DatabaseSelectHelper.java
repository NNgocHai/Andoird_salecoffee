package hcmute.edu.vn.mssv18110278.database;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.User;

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
}