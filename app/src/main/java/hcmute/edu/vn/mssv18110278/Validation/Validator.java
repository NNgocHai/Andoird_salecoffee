package hcmute.edu.vn.mssv18110278.Validation;

import android.content.Context;

import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.Security.PasswordHelpers;
import hcmute.edu.vn.mssv18110278.Entity.Roles;

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
//  public static boolean uniqueUserName(String username) {
//
//  }

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



//  public static boolean validateUserId(int userId, Context context) {
//    User user = DatabaseSelectHelper.getUserDetails(userId, context);
//    if (user != null && user.getId() == userId) {
//      return true;
//    } else {
//      return false;
//    }
//  }
//
//  /**
//   * Method to validate the roleId
//   *
//   * @param roleId the roleId
//   * @return true if the roleId is an id in the Role's table, false otherwise
//   */
//  public static boolean validateRoleId(int roleId, Context context) {
//    List<Integer> roleIds = DatabaseSelectHelper.getRoleIds(context);
//    return roleIds.contains(roleId);
//  }
//
//  /**
//   * Method to validate the item name
//   *
//   * @param itemName the item name
//   * @return true if the item name is not null, less than 64 characters and is a valid item type,
//   * false otherwise
//   */
//  public static boolean validateItemName(String itemName) {
//    if (itemName != null && itemName.length() < 64) {
//      for (ItemTypes i : ItemTypes.values()) {
//        if (i.name().equals(itemName)) {
//          return true;
//        }
//      }
//      for (MemberItemTypes i : MemberItemTypes.values()) {
//        if (i.name().equals(itemName)) {
//          return true;
//        }
//      }
//      return false;
//    } else {
//      return false;
//    }
//  }
//
//  /**
//   * Method to validate the price of an item
//   *
//   * @param price the item price
//   * @return true if the price is greater than 0 and has a scale of 2 decimals, false otherwise
//   */
//  public static boolean validatePrice(BigDecimal price) {
//    if (price.compareTo(BigDecimal.ZERO) == 1 && price.scale() == 2) {
//      return true;
//    } else {
//      return false;
//    }
//  }
//
//  /**
//   * Method to validate the itemId
//   *
//   * @param itemId the itemId
//   * @return true if the itemId is an id in the Items table, false otherwise
//   */
//  public static boolean validateItemId(int itemId, Context context) {
//    Item item = DatabaseSelectHelper.getItem(itemId, context);
//    if (item != null && item.getId() == itemId) {
//      return true;
//    } else {
//      return false;
//    }
//  }
//
//  /**
//   * Method to validate the saleId
//   *
//   * @param saleId the saleId
//   * @return true if the saleId is an id in the Sales table, false otherwise
//   */
//  public static boolean validateSaleId(int saleId, Context context) {
//    Sale sale = DatabaseSelectHelper.getSaleById(saleId, context);
//    if (sale != null && sale.getId() == saleId) {
//      return true;
//    } else {
//      return false;
//    }
//  }
//
//  /**
//   * Method to validate max items in the inventory
//   *
//   * @param maxItems max items
//   * @return true if the max items is greater than 0, false otherwise
//   */
//  public static boolean validateMaxItems(int maxItems) {
//    return maxItems > 0;
//  }
//
//  /**
//   * Method to validate that the total items is less than max items in the inventory
//   *
//   * @param totalItems the total amount of items
//   * @param maxItems the max amount of items
//   * @return true if totalItems is less than or equal to maxItems, false otherwise
//   */
//  public static boolean validateTotalLessThanMaxItems(int totalItems, int maxItems) {
//    return totalItems <= maxItems;
//  }
//
//  public static boolean validateEmpty(String string) {
//    return string.trim().equals("");
//  }
//
//  public static boolean validateStatus(int status) {
//    return status == 1 || status == 0;
//  }
//
//  public static boolean validateUniqueReturn(int saleId, Context context) {
//    return !DatabaseSelectHelper.getReturns(context).contains(saleId);
//  }
}
