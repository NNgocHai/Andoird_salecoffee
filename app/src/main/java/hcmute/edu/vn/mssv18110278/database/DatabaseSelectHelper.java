package hcmute.edu.vn.mssv18110278.database;

import android.content.Context;
import android.database.Cursor;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Category;
import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;
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

    public static int GetCartUser(int iDuser, Context mContext) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(mContext);

        int id = myDB.GetCartUser(iDuser);
        myDB.close();
        return id;
    }


    public static boolean getExistIDItemCart(int idOrder, Item item, Context context) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(context);
        int id=-1;
        id = myDB.getExistIDItemCart(idOrder,item);
        myDB.close();
        if(id == -1)
            return false;
        else
            return true;
    }


    public static List<DetailOrders> getbyIDOrder(int idOrder, Context context) {
        DatabaseDriverAndroid dbA =new DatabaseDriverAndroid(context);
        Cursor cursor = dbA.getbyIDOrder(idOrder);
        List<DetailOrders> detailOrders= new ArrayList<>();
        while (cursor.moveToNext()) {
            detailOrders.add(new DetailOrders(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                    cursor.getInt(3)));

        }
        cursor.close();
        dbA.close();
        return detailOrders;
    }

    public static Order getCart(User user, Context baseContext) {
        DatabaseDriverAndroid dbA =new DatabaseDriverAndroid(baseContext);
        Cursor cursor = dbA.getbyCart(user);
        Order order=null;
        if (cursor.moveToFirst()) {
            order = new Order(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                    cursor.getInt(3), cursor.getString(4),cursor.getString(5), cursor.getString(6));

        }
        cursor.close();
        dbA.close();
        return order;
    }

    public static List<Item> getIembyCate(int id, Context mContext) {
        DatabaseDriverAndroid dbA =new DatabaseDriverAndroid(mContext);
        Cursor cursor = dbA.getItembyCate(id);
        List<Item> items= new ArrayList<>();
        while (cursor.moveToNext()) {
            items.add(new Item(cursor.getInt(0),cursor.getInt(1), cursor.getString(2),
                    cursor.getInt(3), cursor.getString(4),cursor.getInt(5), cursor.getBlob(6)));

        }
        cursor.close();
        dbA.close();
        return items;
    }

    public static List<Item> getIembyCate_Name(int idseach, String search,Context mContext) {
        DatabaseDriverAndroid dbA =new DatabaseDriverAndroid(mContext);
        Cursor cursor = dbA.getIembyCate_Name(idseach,search);
        List<Item> items= new ArrayList<>();
        while (cursor.moveToNext()) {
            items.add(new Item(cursor.getInt(0),cursor.getInt(1), cursor.getString(2),
                    cursor.getInt(3), cursor.getString(4),cursor.getInt(5), cursor.getBlob(6)));

        }
        cursor.close();
        dbA.close();
        return items;

    }

    public static List<Order> getOrderbyIDUser(int id, Context context) {
        DatabaseDriverAndroid dbA =new DatabaseDriverAndroid(context);
        Cursor cursor = dbA.getOrderbyIDUser(id);
        List<Order> orders= new ArrayList<>();
        while (cursor.moveToNext()) {
            orders.add(new Order(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                    cursor.getInt(3), cursor.getString(4),cursor.getString(5), cursor.getString(6)));       }
        cursor.close();
        dbA.close();
        return orders;
    }


    public static List<Order> getallorder(Context context) {
        DatabaseDriverAndroid dbA =new DatabaseDriverAndroid(context);
        Cursor cursor = dbA.getallorder();
        List<Order> orders= new ArrayList<>();
        while (cursor.moveToNext()) {
            orders.add(new Order(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                    cursor.getInt(3), cursor.getString(4),cursor.getString(5), cursor.getString(6)));       }
        cursor.close();
        dbA.close();
        return orders;
    }

    public static User getUserbyID(int userid, Context mContext) {
        DatabaseDriverAndroid myDB = new DatabaseDriverAndroid(mContext);

        Cursor cursor = null;
        User user = null;
        cursor = myDB.getUserbyID(userid);

        if (cursor.moveToFirst()) {
            user = new User(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getInt(3),cursor.getBlob(4));
        }
        cursor.close();
        myDB.close();
        return user;
    }



    public static ArrayList<Integer> getreportbyday1(Context context) {
        DatabaseDriverAndroid dbA =new DatabaseDriverAndroid(context);
        Cursor cursor = dbA.getreportbyday();
        List<Integer> integers= new ArrayList<>();
        while (cursor.moveToNext()) {
            integers.add(new Integer(cursor.getInt(1)));       }
        cursor.close();
        dbA.close();

        return (ArrayList<Integer>) integers;


    }

    public static ArrayList<String> getreportbyday(Context context) {
        DatabaseDriverAndroid dbA =new DatabaseDriverAndroid(context);
        Cursor cursor = dbA.getreportbyday();
        List<String> strings= new ArrayList<>();
        while (cursor.moveToNext()) {
            strings.add(new String(cursor.getString(0)));       }
        cursor.close();
        dbA.close();

        return (ArrayList<String>) strings;
    }

    public static ArrayList<String> getreportbymonth(Context context) {
        DatabaseDriverAndroid dbA =new DatabaseDriverAndroid(context);
        Cursor cursor = dbA.getreportbymonth();
        List<String> strings= new ArrayList<>();
        while (cursor.moveToNext()) {
            strings.add(new String(cursor.getString(0)));       }
        cursor.close();
        dbA.close();

        return (ArrayList<String>) strings;
    }

    public static ArrayList<Integer> getreportbymonth1(Context context) {
        DatabaseDriverAndroid dbA =new DatabaseDriverAndroid(context);
        Cursor cursor = dbA.getreportbymonth();
        List<Integer> integers= new ArrayList<>();
        while (cursor.moveToNext()) {
            integers.add(new Integer(cursor.getInt(1)));       }
        cursor.close();
        dbA.close();

        return (ArrayList<Integer>) integers;
    }

    public static ArrayList<Integer> getreportbyyear1(Context context) {
        DatabaseDriverAndroid dbA =new DatabaseDriverAndroid(context);
        Cursor cursor = dbA.getreportbyyear();
        List<Integer> integers= new ArrayList<>();
        while (cursor.moveToNext()) {
            integers.add(new Integer(cursor.getInt(1)));       }
        cursor.close();
        dbA.close();

        return (ArrayList<Integer>) integers;
    }

    public static ArrayList<String> getreportbyyear(Context context) {
        DatabaseDriverAndroid dbA =new DatabaseDriverAndroid(context);
        Cursor cursor = dbA.getreportbyyear();
        List<String> strings= new ArrayList<>();
        while (cursor.moveToNext()) {
            strings.add(new String(cursor.getString(0)));       }
        cursor.close();
        dbA.close();

        return (ArrayList<String>) strings;
    }
}
