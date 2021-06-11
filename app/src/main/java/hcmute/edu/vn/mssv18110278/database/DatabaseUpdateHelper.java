package hcmute.edu.vn.mssv18110278.database;

import android.content.Context;

import javax.xml.validation.Validator;

public class DatabaseUpdateHelper {
    public static boolean updateUserRole(int roleId, int userId, Context context) {
        DatabaseDriverAndroid dbA = new DatabaseDriverAndroid(context);
        boolean complete = false;
        dbA.close();
        return complete;
    }

}
