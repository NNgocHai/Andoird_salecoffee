package hcmute.edu.vn.mssv18110278.database;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Category;
import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.Entity.User;
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

    public static void insertProduct(String category,String item_name, int item_price, String item_detail,int status ,byte[] image, Context context) {
        DatabaseDriverAndroid dbA = new DatabaseDriverAndroid(context);
        int id_category =-1;
        List<Category> cates = DatabaseSelectHelper.getCategory(category,context);
        if(cates.size()==0) {
            id_category = (int) dbA.insertNewCategory(category);
        }
        else
            id_category= cates.get(0).getId();
        dbA.insertNewProduct(id_category,item_name, item_price, item_detail,status ,image);
        dbA.close();
    }

    public static void AddtoCart(User user, Item item, Context mContext) {
        DatabaseDriverAndroid dbA = new DatabaseDriverAndroid(mContext);
        int IDuser =user.getId();
        int IDOrder =-1;
        List<DetailOrders> lstdetailOrders= new ArrayList<>() ;

        // chua co thong tin gio hang
        IDOrder = DatabaseSelectHelper.GetCartUser(IDuser,mContext);
        if(IDOrder==-1) {
            IDOrder = dbA.insertNewCart(IDuser,-1,0);
            dbA.insertNewDetailCart(IDOrder,item.getId(),1,item.getPrice());

            Order order =null;
            Cursor cursor = dbA.GetOrderIdOrder(IDOrder);
            if (cursor.moveToFirst()) {
                order= new Order(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                        cursor.getInt(3), cursor.getString(4),cursor.getString(5), cursor.getString(6));
            }
            cursor.close();
            dbA.updateCart(order,item.getPrice());

        }
        else{
            if( Validator.validateExistIDItem(IDOrder,item,mContext))
            {
                Cursor cursor= dbA.getDetailOrder(IDOrder,item);
                DetailOrders detailOrders = null;

                if (cursor.moveToFirst()) {
                    detailOrders = new DetailOrders(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
                }
                cursor.close();
                dbA.updateDetailCart(detailOrders,item,1);

                cursor = dbA.getbyIDOrder(IDOrder);

                while (cursor.moveToNext()) {
                    lstdetailOrders.add(new DetailOrders(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                            cursor.getInt(3)));

                }
                cursor.close();

                Order order =null;
                cursor = dbA.GetOrderIdOrder(IDOrder);
                if (cursor.moveToFirst()) {
                    order= new Order(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                            cursor.getInt(3), cursor.getString(4),cursor.getString(5), cursor.getString(6));
                }
                cursor.close();

                int total =0;
                for (DetailOrders detailOrders1 : lstdetailOrders) {
                    total+=detailOrders1.getTotalprice();
                }
                dbA.updateCart(order,total);

            }
            else
            {
                dbA.insertNewDetailCart(IDOrder,item.getId(),1,item.getPrice());
                ////
                Cursor cursor = dbA.getbyIDOrder(IDOrder);

                while (cursor.moveToNext()) {
                    lstdetailOrders.add(new DetailOrders(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                            cursor.getInt(3)));

                }
                cursor.close();

                Order order =null;
                cursor = dbA.GetOrderIdOrder(IDOrder);
                if (cursor.moveToFirst()) {
                    order= new Order(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                            cursor.getInt(3), cursor.getString(4),cursor.getString(5), cursor.getString(6));
                }
                cursor.close();

                int total =0;
                for (DetailOrders detailOrders1 : lstdetailOrders) {
                    total+=detailOrders1.getTotalprice();
                }
                dbA.updateCart(order,total);

            }
        }

        dbA.close();
    }
//    sql = "CREATE TABLE ORDERS "
//            + "(ID INTEGER PRIMARY KEY NOT NULL,"
//            + "USERID INTEGER NOT NULL,"
//            + "TOTALPRICE INTERGER NOT NULL,"
//            + "STATUS INTERGER NOT NULL,"
//            + "DATE STRING,"
//            + "ADDRESS STRING,"
//            + "PHONE STRING,"
//            + "FOREIGN KEY(USERID) REFERENCES USERS(ID))";
//        sqLiteDatabase.execSQL(sql);
//    sql = "CREATE TABLE DETAILORDERS "
//            + "(IDORDER INTEGER  NOT NULL,"
//            + "IDITEM INTEGER NOT NULL,"
//            + "AMOUNT INTEGER NOT NULL,"
//            + "TOTALPRICE INTERGER NOT NULL,"
//            + "PRIMARY KEY(IDORDER, IDITEM),"
//            + "FOREIGN KEY(IDITEM) REFERENCES ITEMS(ID),"
//            + "FOREIGN KEY(IDORDER) REFERENCES ORDERS(ID))";
//        sqLiteDatabase.execSQL(sql);
}
