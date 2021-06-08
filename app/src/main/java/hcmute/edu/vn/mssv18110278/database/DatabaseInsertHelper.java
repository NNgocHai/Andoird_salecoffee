package hcmute.edu.vn.mssv18110278.database;

import android.content.Context;

import hcmute.edu.vn.mssv18110278.Validation.Validator;

public class DatabaseInsertHelper {
    public static void insertNewUser(String username, String email, String password,int roleID,byte[] image,
                                    Context context) {
        DatabaseDriverAndroid dbA = new DatabaseDriverAndroid(context);
        dbA.insertNewUser(username, email, password, image, roleID);
        dbA.close();

    }
    public static int insertRole(String name, Context context) {
        DatabaseDriverAndroid dbA = new DatabaseDriverAndroid(context);
        long roleId = -1;

        if (Validator.validateRoleName(name)) {
            if (Validator.validateRoleUnique(name, context)) {
                roleId = dbA.insertRole(name);
            } else {
                roleId = DatabaseSelectHelper.getRoleIdFromName(name, context);
            }
        } else {
            System.out.println("Ensure the method arguments are correct for insertRole.");
        }
        dbA.close();
        return Math.toIntExact(roleId);
    }
}
