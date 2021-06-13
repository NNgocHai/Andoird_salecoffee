package hcmute.edu.vn.mssv18110278.database;

import android.content.Context;

import javax.xml.validation.Validator;

public class DatabaseUpdateHelper {

    public static void updateItem(int id, int idcate, String parse_employee_update_item_name, int parse_employee_update_item_price, String parse_employee_update_item_detail, int status, Context appContext) {
        DatabaseDriverAndroid dbA = new DatabaseDriverAndroid(appContext);
        dbA.updateItem(id,idcate,parse_employee_update_item_name,parse_employee_update_item_price,parse_employee_update_item_detail,status);
        dbA.close();

    }
}
