package hcmute.edu.vn.mssv18110278.Users.User;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.text.DecimalFormat;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.database.DatabaseInsertHelper;

public class DetailProductActivity extends AppCompatActivity {


    public static User user;
    private Item item;
    private TextView user_name_product, status_product,price_product,detail_product;
    private Button btn_add_tocart;
    private ImageView image_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        item = getIntent().getExtras().getParcelable("item");

        user_name_product = findViewById(R.id.user_name_product);
        image_product= findViewById(R.id.image_product);
        status_product= findViewById(R.id.status_product);
        price_product= findViewById(R.id.price_product);
        detail_product= findViewById(R.id.detail_product);
        btn_add_tocart= findViewById(R.id.btn_add_tocart);

        user_name_product.setText(item.getName());
        Bitmap bitmap= BitmapFactory.decodeByteArray(item.getImage(), 0, item.getImage().length);
        image_product.setImageBitmap(bitmap);
        String pattern = "###,###";
        String format=null;
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        format = decimalFormat.format(item.getPrice());
        price_product.setText("Giá: " +format +" VND");

        if(item.getStatus()==1)
        {
            status_product.setText("Còn hàng");

        }
        else
        {
            status_product.setText("Hết hàng");
        }
        detail_product.setText(item.getDetail());
        btn_add_tocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HomeActivity.user.getId()!=-1) {
                    DatabaseInsertHelper.AddtoCart(HomeActivity.user, item, getBaseContext());
                    Toast.makeText(getBaseContext(), "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (HomeActivity.detailOrders.size()  < 1) {
                        HomeActivity.order = new Order(-1, -1, item.getPrice(), 0, null, null, null);
                        HomeActivity.detailOrders.add(new DetailOrders(-1, item.getId(), 1, item.getPrice()));
                    } else {
                        int IsExisted =0;
                        for (DetailOrders detailOrders : HomeActivity.detailOrders) {
                            if (detailOrders.getIditem() == item.getId()) {
                                detailOrders.setMount(detailOrders.getMount() + 1);
                                detailOrders.setTotalprice(detailOrders.getMount() * item.getPrice());
                                HomeActivity.order.setTOTALPRICE(HomeActivity.order.getTOTALPRICE()+item.getPrice());
                                IsExisted =1;
                                break;
                            }
                        }
                        if(IsExisted==0)
                        {
                            HomeActivity.detailOrders.add(new DetailOrders(-1, item.getId(), 1,item.getPrice()));
                            HomeActivity.order.setTOTALPRICE(HomeActivity.order.getTOTALPRICE()+item.getPrice());

                        }
                    }
                    Toast.makeText(getBaseContext(), "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();

                }

            }
        });




    }
}
