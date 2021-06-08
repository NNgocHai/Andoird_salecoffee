package hcmute.edu.vn.mssv18110278.database;

import android.content.Context;

import javax.xml.validation.Validator;

public class DatabaseUpdateHelper {
    public static boolean updateUserRole(int roleId, int userId, Context context) {
        DatabaseDriverAndroid dbA = new DatabaseDriverAndroid(context);
        boolean complete = false;

//        if (Validator.validateUserId(userId, context) && Validator.validateRoleId(roleId, context)) {
//            complete = dbA.updateUserRole(roleId, userId);
//            if (!complete) {
//                System.out.println("Could not update user role in the database.");
//            }
//        }
        dbA.close();
        return complete;
    }

}
