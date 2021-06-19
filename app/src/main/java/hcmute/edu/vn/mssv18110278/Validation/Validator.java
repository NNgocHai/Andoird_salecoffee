package hcmute.edu.vn.mssv18110278.Validation;

import android.content.Context;

import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.Security.PasswordHelpers;
import hcmute.edu.vn.mssv18110278.Entity.Roles;

import static hcmute.edu.vn.mssv18110278.Users.User.HomeActivity.user;

public class Validator {


  public static boolean validateRestockQuantity(int quantity) {
    return quantity > 0;
  }


  public static boolean validateNewItemQuantity(int quantity) {
    return quantity >= 0;
  }

  public static boolean validateRoleName(String roleName) {
    if (roleName != null) {
      for (Roles role : Roles.values()) {
        if (roleName.equals(role.name())) {
          return true;
        }
      }
    }
    return false;
  }
  public static boolean validateRoleUnique(String roleName, Context context) {
    for (int roleId : DatabaseSelectHelper.getRoleIds(context)) {
      if (roleName.equals(DatabaseSelectHelper.getRoleName(roleId, context))) {
        return false;
      }
    }
    return true;
  }
  public static boolean validateUserUnique(String user, Context context) {
    for (String name : DatabaseSelectHelper.getUsers(context)) {
      if (user.equals(name)) {
        return false;
      }
    }
    return true;
  }

  public static boolean validateUserName(String username) {
    if (username != null && !username.equals("")) {
      return true;
    } else {
      return false;
    }
  }
  public static boolean validatePassword_login(int userID,String password, Context context) {
    String dbPassword = DatabaseSelectHelper.getPassword(userID, context);
    return PasswordHelpers.comparePassword(dbPassword, password);
  }


  public static boolean validateEmail(String email){
    if (email != null && !email.equals("")) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean validatePassword(String password) {
    if (password != null && !password.equals("")) {
      return true;
    } else {
      return false;
    }
  }
  public static boolean validatePassword2(String password, String confirm_password) {
    if (password.equals(confirm_password)) {
      return true;
    } else {
      return false;
    }
  }

    public static boolean validateItemName(String parse_employee_insert_item_name) {
      if (parse_employee_insert_item_name != null && !parse_employee_insert_item_name.equals("")) {
        return true;
      }
      return false;

    }


  public static boolean validatePriceItem(String parse_employee_insert_item_price) {
    if (parse_employee_insert_item_price != null && !parse_employee_insert_item_price.equals("")) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean validateItemDetail(String parse_employee_insert_item_detail) {
    if (parse_employee_insert_item_detail != null && !parse_employee_insert_item_detail.equals("")) {
      return true;
    } else {
      return false;
    }
  }


    public static boolean validateExistIDItem(int IDOrder, Item item , Context context) {
      return DatabaseSelectHelper.getExistIDItemCart(IDOrder,item,context);
    }

    public static String validateItemAvailable(List<DetailOrders> detailOrders, Context context) {
      Item item=null;
      for (DetailOrders detailOrders1  : detailOrders) {
        item =DatabaseSelectHelper.getItem(detailOrders1.getIditem(),context);

        if (item.getStatus()==0) {
          return item.getName();
        }
      }
      return "ok";
    }

  public static boolean validateAddress(String parse_edt_address_payment) {
    if (parse_edt_address_payment != null && !parse_edt_address_payment.equals("")) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean validatePhone(String parse_edt_phone_payment) {
    if (parse_edt_phone_payment != null && !parse_edt_phone_payment.equals("")) {
      return true;
    } else {
      return false;
    }
  }
}
