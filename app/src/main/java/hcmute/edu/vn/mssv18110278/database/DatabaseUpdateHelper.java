package hcmute.edu.vn.mssv18110278.database;

import android.content.Context;

import javax.xml.validation.Validator;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;

public class DatabaseUpdateHelper {

    public static void updateItem(int id, int idcate, String parse_employee_update_item_name, int parse_employee_update_item_price, String parse_employee_update_item_detail, int status, Context appContext) {
        DatabaseDriverAndroid dbA = new DatabaseDriverAndroid(appContext);
        dbA.updateItem(id,idcate,parse_employee_update_item_name,parse_employee_update_item_price,parse_employee_update_item_detail,status);
        dbA.close();
    }

    public static void updatecart(DetailOrders detailOrders, Item finalItem, int i, Context mContext) {
        DatabaseDriverAndroid dbA = new DatabaseDriverAndroid(mContext);
        dbA.updateDetailCart(detailOrders,finalItem,i);
        dbA.close();
    }

    public static void updatecartpayment(Order order, String time, String parse_edt_phone_payment, String parse_edt_address_payment, Context baseContext) {
        DatabaseDriverAndroid dbA = new DatabaseDriverAndroid(baseContext);
        dbA.updateCartPayment(order,time,parse_edt_phone_payment,parse_edt_address_payment);
        dbA.close();
    }

    public static void updatepassword(String password1,Context context) {
        DatabaseDriverAndroid dbA = new DatabaseDriverAndroid(context);
        dbA.updatePassword(password1);
        dbA.close();
    }
}
