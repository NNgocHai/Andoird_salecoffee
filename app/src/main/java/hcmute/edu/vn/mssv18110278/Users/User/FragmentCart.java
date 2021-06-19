package hcmute.edu.vn.mssv18110278.Users.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.MainActivity;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.Users.Admin.UpdateItemActivity;
import hcmute.edu.vn.mssv18110278.Validation.Validator;
import hcmute.edu.vn.mssv18110278.database.DatabaseDriverAndroid;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;

public class FragmentCart extends Fragment {
    View v;
    Context context;

    private RecyclerView cyclerproduct;
    private List<DetailOrders> detailOrders;
    public TextView user_total;
    private Button btnPayment;
    public  static final int LAUCH_PAYMENT =1;
    int IDOrder= -1;
    public FragmentCart() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.user_cart_fragment,container,false);

        cyclerproduct =(RecyclerView) v.findViewById(R.id.user_RecyclerCart);

        context = container.getContext();
        if(HomeActivity.user.getId()!=-1) {
            IDOrder = DatabaseSelectHelper.GetCartUser(HomeActivity.user.getId(), context);
            detailOrders = DatabaseSelectHelper.getbyIDOrder(IDOrder, context);
        }
        else
        {
            detailOrders =HomeActivity.detailOrders;
        }

        AdapterProductCart recyclerViewAdapter1= new AdapterProductCart(getContext(),detailOrders,this);

        cyclerproduct.setLayoutManager(new LinearLayoutManager(getActivity()));
        cyclerproduct.setAdapter(recyclerViewAdapter1);
        user_total= (TextView) v.findViewById(R.id.user_total);
        int total =0;
        for (DetailOrders detailOrders1 : detailOrders) {
            total+=detailOrders1.getTotalprice();
        }
        String pattern = "###,###";
        String format=null;
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        format = decimalFormat.format(total);
        user_total.setText(format+" VND");
        btnPayment=(Button)v.findViewById(R.id.payment);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(detailOrders.size()>0) {
                    if(HomeActivity.user.getId()!= -1) {
                        detailOrders = DatabaseSelectHelper.getbyIDOrder(IDOrder, context);
                        String Available = "";
                        Available = Validator.validateItemAvailable(detailOrders, context);
                        if (Available == "ok") {

                            Intent intent = new Intent(getContext(), PaymentActivity.class);
                            intent.putExtra("user", HomeActivity.user);
                            startActivityForResult(intent, LAUCH_PAYMENT);
                        } else {
                            Toast.makeText(getContext(), "Sản phẩm " + Available + " đã  hết hàng", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        startActivity(new Intent(context,MainActivity.class));
                        Toast.makeText(getContext(), "Đăng nhập trước khi đặt hàng", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                     Toast.makeText(getContext(), "Bạn không có sản phẩm trong giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int IDOrder= -1;
        IDOrder= DatabaseSelectHelper.GetCartUser(HomeActivity.user.getId(),context);
        detailOrders = DatabaseSelectHelper.getbyIDOrder(IDOrder,context);
        AdapterProductCart recyclerViewAdapter1= new AdapterProductCart(getContext(),detailOrders,this);

        cyclerproduct.setLayoutManager(new LinearLayoutManager(getActivity()));
        cyclerproduct.setAdapter(recyclerViewAdapter1);
        user_total= (TextView) v.findViewById(R.id.user_total);
        int total =0;
        for (DetailOrders detailOrders1 : detailOrders) {
            total+=detailOrders1.getTotalprice();
        }
    }
}
