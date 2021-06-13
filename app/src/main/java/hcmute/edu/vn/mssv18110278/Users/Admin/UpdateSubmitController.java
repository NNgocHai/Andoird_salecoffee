package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Category;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.database.DatabaseInsertHelper;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.database.DatabaseUpdateHelper;

public class UpdateSubmitController implements View.OnClickListener {
    private Context appContext;
    private int id;
    Intent returnIntent;
    public UpdateSubmitController(Context appContext,int Id) {
        this.appContext = appContext;
        id=Id;
    }

    @Override
    public void onClick(View v) {
        int status =-1;
        int idcate =-1;
        Spinner employee_update_item_category = ((UpdateItemActivity) appContext).findViewById(R.id.employee_update_item_category);
        EditText employee_update_item_name = ((UpdateItemActivity) appContext).findViewById(R.id.employee_update_item_name);
        EditText employee_update_item_price = ((UpdateItemActivity) appContext).findViewById(R.id.employee_update_item_price);
        EditText employee_update_item_detail = ((UpdateItemActivity) appContext).findViewById(R.id.employee_update_item_detail);
        Spinner employee_update_item_status = ((UpdateItemActivity) appContext).findViewById(R.id.employee_update_item_status);
        String parse_employee_update_item_status = employee_update_item_status.getSelectedItem().toString();
        String parse_employee_update_item_category = employee_update_item_category.getSelectedItem().toString();
        String parse_employee_update_item_name = employee_update_item_name.getText().toString();
        String parse_employee_update_item_price = employee_update_item_price.getText().toString();
        String parse_employee_update_item_detail = employee_update_item_detail.getText().toString();
        if(parse_employee_update_item_status=="Hết hàng")
        {
            status = 0;
        }else
        {
            status = 1;
        }
        List<Category> cates = DatabaseSelectHelper.getCategory(parse_employee_update_item_category,appContext);
        for (Category cate : cates) {
            if (parse_employee_update_item_category.equals(cate.getName()))
            {
                idcate=cate.getId();
            }


        }
        try {

            DatabaseUpdateHelper.updateItem(id, idcate, parse_employee_update_item_name, Integer.parseInt(parse_employee_update_item_price), parse_employee_update_item_detail, status, appContext);
            Toast.makeText(appContext,"Update thành công", Toast.LENGTH_SHORT).show();
            returnIntent = new Intent();
            ((Activity)appContext).setResult(Activity.RESULT_OK, returnIntent);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(appContext,"Update thất bại", Toast.LENGTH_SHORT).show();
            returnIntent = new Intent();
            ((Activity)appContext).setResult(Activity.RESULT_CANCELED, returnIntent);
        }
    }
}
