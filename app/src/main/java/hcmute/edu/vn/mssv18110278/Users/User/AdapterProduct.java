package hcmute.edu.vn.mssv18110278.Users.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.Users.Admin.UpdateItemActivity;
import hcmute.edu.vn.mssv18110278.database.DatabaseDriverAndroid;
import hcmute.edu.vn.mssv18110278.database.DatabaseInsertHelper;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyViewHoder> {
    Context mContext;
    List<Item> mData;
    public AdapterProduct(Context context, List<Item> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
        MyViewHoder vHolder = new MyViewHoder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterProduct.MyViewHoder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());

        String pattern = "###,###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String format = decimalFormat.format(mData.get(position).getPrice());
        holder.tv_price.setText("Giá: " +format +" VND");
        if(mData.get(position).getStatus()==1)
        {
            holder.tv_status.setText("Còn hàng");
        }
        else
        {
            holder.tv_status.setText("Hết hàng");
        }
        Bitmap bitmap= BitmapFactory.decodeByteArray(mData.get(position).getImage(), 0, mData.get(position).getImage().length);
        holder.img.setImageBitmap(bitmap);
        holder.btn_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HomeActivity.user.getId() != -1) {
                    DatabaseInsertHelper.AddtoCart(HomeActivity.user, mData.get(position), mContext);
                    Toast.makeText(mContext, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                } else {
                    if (HomeActivity.detailOrders.size()  < 1) {
                        HomeActivity.order = new Order(-1, -1, mData.get(position).getPrice(), 0, null, null, null);
                        HomeActivity.detailOrders.add(new DetailOrders(-1, mData.get(position).getId(), 1, mData.get(position).getPrice()));
                    } else {
                        int IsExisted =0;
                        for (DetailOrders detailOrders : HomeActivity.detailOrders) {
                            if (detailOrders.getIditem() == mData.get(position).getId()) {
                                detailOrders.setMount(detailOrders.getMount() + 1);
                                detailOrders.setTotalprice(detailOrders.getMount() * mData.get(position).getPrice());
                                HomeActivity.order.setTOTALPRICE(HomeActivity.order.getTOTALPRICE()+mData.get(position).getPrice());
                                IsExisted =1;
                                break;
                            }
                        }
                        if(IsExisted==0)
                        {
                            HomeActivity.detailOrders.add(new DetailOrders(-1, mData.get(position).getId(), 1, mData.get(position).getPrice()));
                            HomeActivity.order.setTOTALPRICE(HomeActivity.order.getTOTALPRICE()+mData.get(position).getPrice());

                        }
                    }
                    Toast.makeText(mContext, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.lineritem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item =mData.get(position);
                Intent intent = new Intent(mContext, DetailProductActivity.class);
                intent.putExtra("item", (Parcelable) item);
                ((Activity) mContext).startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        try{
            return mData.size();
        } catch (Exception e) {
            return 0;
        }


    }

    public static class MyViewHoder extends RecyclerView.ViewHolder
    {
        private TextView tv_name;
        private TextView tv_status;
        private ImageView img;
        private TextView tv_price;
        private ImageButton btn_add_cart;
        private LinearLayout lineritem;


        public MyViewHoder(View itemview){
            super(itemview);

            tv_name=(TextView) itemview.findViewById(R.id.name_product_user);
            tv_status= (TextView) itemview.findViewById(R.id.status_product_user);
            img = (ImageView) itemview.findViewById(R.id.img_product_user);
            tv_price= (TextView) itemview.findViewById(R.id.price_product_user);
            btn_add_cart =(ImageButton )itemview.findViewById(R.id.add_cart_user);
            lineritem =itemview.findViewById(R.id.lineritem);
        }
    }
    public void changeItemsbyCate(List<Item> items){
        mData=items;
        notifyDataSetChanged();
    }





}
