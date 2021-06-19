package hcmute.edu.vn.mssv18110278.Users.User;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;

import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.R;

import hcmute.edu.vn.mssv18110278.Validation.Validator;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.database.DatabaseUpdateHelper;

public class PaymentActivity extends AppCompatActivity {
    private EditText edt_address_payment, edt_phone_payment;
    public TextView user_total_payment;
    private RecyclerView user_RecyclerCart_payment;
    private User user;
    private List<DetailOrders> detailOrders;
    private Button btn_payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        user = getIntent().getExtras().getParcelable("user");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();


        user_RecyclerCart_payment = (RecyclerView) findViewById(R.id.user_RecyclerCart_payment);
        user_total_payment = (TextView) findViewById(R.id.user_total_payment);
        btn_payment = (Button) findViewById(R.id.btn_payment);


        String parse_user_total_payment = user_total_payment.getText().toString();
        int IDOrder = -1;
        IDOrder = DatabaseSelectHelper.GetCartUser(HomeActivity.user.getId(), this);
        detailOrders = DatabaseSelectHelper.getbyIDOrder(IDOrder, this);
        AdapterProductCartPayment recyclerViewAdapter = new AdapterProductCartPayment(this, detailOrders);

        user_RecyclerCart_payment.setLayoutManager(new LinearLayoutManager(this));
        user_RecyclerCart_payment.setAdapter(recyclerViewAdapter);

        int total = 0;
        for (DetailOrders detailOrders1 : detailOrders) {
            total += detailOrders1.getTotalprice();
        }
        String pattern = "###,###";
        String format=null;
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        format = decimalFormat.format(total);
        user_total_payment.setText(format+ " VND");


        Order order = DatabaseSelectHelper.getCart(user, getBaseContext());


        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_address_payment = (EditText) findViewById(R.id.edt_address_payment);
                edt_phone_payment = (EditText) findViewById(R.id.edt_phone_payment);
                String parse_edt_address_payment = edt_address_payment.getText().toString();
                String parse_edt_phone_payment = edt_phone_payment.getText().toString();
                if(Validator.validateAddress(parse_edt_address_payment) && Validator.validatePhone(parse_edt_phone_payment)) {
                    AlertDialog dialog = new AlertDialog.Builder(PaymentActivity.this)
                            .setTitle("")
                            .setMessage("Bạn có chắc muốn đặt hàng")
                            .setPositiveButton("Ok", null)
                            .setNegativeButton("Cancel", null)
                            .show();

                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(getBaseContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                            DatabaseUpdateHelper.updatecartpayment(order, dtf.format(now), parse_edt_phone_payment, parse_edt_address_payment, getBaseContext());
                            dialog.dismiss();
                            finish();
                        }
                    });
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Chưa nhập địa chỉ và số điện thoại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
