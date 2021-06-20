package hcmute.edu.vn.mssv18110278.Users.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;

import static java.security.AccessController.getContext;

public class DetailOrderActivity extends AppCompatActivity {
    private List<DetailOrders> detailOrders;
    private RecyclerView cyclerlbill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailorder);

        Intent intent = getIntent();
        int id = intent.getIntExtra("ID",0);

        cyclerlbill=(RecyclerView)  findViewById(R.id.user_detail_bill);
        detailOrders =DatabaseSelectHelper.getbyIDOrder(id,this);
        AdapterDetailOrder recyclerViewAdapter1= new AdapterDetailOrder(this,detailOrders);
        cyclerlbill.setLayoutManager(new LinearLayoutManager(DetailOrderActivity.this));
        cyclerlbill.setAdapter(recyclerViewAdapter1);

        TextView user_total=findViewById(R.id.user_total);
        String pattern = "###,###";
        String format=null;
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        int total =0;
        for (DetailOrders detailOrders1 : detailOrders) {
            total+=detailOrders1.getTotalprice();
        }
        format = decimalFormat.format(total);
        user_total.setText(format+" VND");


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

}
