package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Category;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;

import static hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper.getItem;

public class UpdateItemActivity extends AppCompatActivity {
    Button submit_btn;
    int id;
    Item item;
    List<String> namecategories=new ArrayList<String>();
    ArrayList<String> status=new ArrayList<String>();;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_product);
        Intent intent = getIntent();
        int id = intent.getIntExtra("ID",0);

        status.add("Còn hàng");
        status.add("Hết hàng");

        item = DatabaseSelectHelper.getItem(id,this);
        namecategories= DatabaseSelectHelper.getAllCategory(this);

        Spinner employee_update_item_category = (Spinner) findViewById(R.id.employee_update_item_category);
        EditText employee_update_item_name = (EditText) findViewById(R.id.employee_update_item_name);
        EditText employee_update_item_price = (EditText) findViewById(R.id.employee_update_item_price);
        EditText employee_update_item_detail = (EditText)findViewById(R.id.employee_update_item_detail);
        Spinner employee_update_item_status = (Spinner) findViewById(R.id.employee_update_item_status);

        String pattern = "###,###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        employee_update_item_price.setText(String.valueOf(item.getPrice()));
        employee_update_item_detail.setText(item.getDetail());
        employee_update_item_name.setText(item.getName());


        ArrayAdapter<String> Adaptername = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, namecategories);
        Adaptername.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        employee_update_item_category.setAdapter(Adaptername);


        ArrayAdapter<String> Adapterstatus = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, status);
        Adapterstatus.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        employee_update_item_status.setAdapter(Adapterstatus);


        submit_btn = (Button)findViewById(R.id.employee_update_item_submit_button);
        submit_btn.setOnClickListener(new UpdateSubmitController(this,id));



    }

}
