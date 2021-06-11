package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.Validation.Validator;
import hcmute.edu.vn.mssv18110278.database.DatabaseInsertHelper;

public class AddSubmitController implements View.OnClickListener {
    private Context appContext;

    public AddSubmitController(Context context) {
        appContext = context;
    }
    @Override
    public void onClick(View v) {
        EditText employee_insert_item_name = ((AddItemActivity) appContext).findViewById(R.id.employee_insert_item_name);
        EditText employee_insert_item_price = ((AddItemActivity) appContext).findViewById(R.id.employee_insert_item_price);
        EditText employee_insert_item_detail = ((AddItemActivity) appContext).findViewById(R.id.employee_insert_item_detail);
        EditText employee_insert_item_category = ((AddItemActivity) appContext).findViewById(R.id.employee_insert_item_category);
        ImageView employee_insert_item_image = ((AddItemActivity) appContext).findViewById(R.id.employee_insert_item_image);
        TextView error = ((AddItemActivity) appContext).findViewById(R.id.employee_insert_item_error);

        String parse_employee_insert_item_name = employee_insert_item_name.getText().toString();
        String parse_employee_insert_item_price = employee_insert_item_price.getText().toString();
        String parse_employee_insert_item_detail = employee_insert_item_detail.getText().toString();
        String parse_employee_insert_item_category = employee_insert_item_category.getText().toString();
        String tem = parse_employee_insert_item_name;
        tem.replaceAll("\\s+","");
        tem.toLowerCase();

        int idimage = appContext.getResources().getIdentifier("hcmute.edu.vn.mssv18110278:drawable/"+tem, null, null);

        employee_insert_item_image.setImageResource(idimage);


        boolean Iserror =false;
        if  (!Validator.validateItemName(parse_employee_insert_item_name)) {
            error.setText(R.string.error_existname);
            Iserror = true;
        } else if (!Validator.validatePriceItem(parse_employee_insert_item_price)) {
            error.setText(R.string.password_error_login);
            Iserror = true;
        } else if (!Validator.validateItemDetail(parse_employee_insert_item_detail)) {
            error.setText(R.string.password_error_login);
            Iserror = true;
        }
        if (!Iserror) {
                DatabaseInsertHelper.insertProduct(parse_employee_insert_item_category,parse_employee_insert_item_name, Integer.parseInt(parse_employee_insert_item_price), parse_employee_insert_item_detail,1 ,ConverttoArrayByte(employee_insert_item_image), appContext);
                employee_insert_item_name.setText("");
                employee_insert_item_price.setText("");
                employee_insert_item_detail.setText("");
                employee_insert_item_category.setText("");
                Toast.makeText(appContext,"Insert success",Toast.LENGTH_SHORT).show();
        }


    }
    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
