package hcmute.edu.vn.mssv18110278.Users.User;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.database.DatabaseDriverAndroid;
import hcmute.edu.vn.mssv18110278.database.DatabaseInsertHelper;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.database.DatabaseUpdateHelper;

public class AdapterProductCart extends RecyclerView.Adapter<AdapterProductCart.MyViewHoder> {
    Context mContext;
    List<DetailOrders> mData;
    FragmentCart fragmentCart;
    String pattern = "###,###";
    String format=null;
    DecimalFormat decimalFormat = new DecimalFormat(pattern);
    public AdapterProductCart(Context context, List<DetailOrders> mData, FragmentCart fragmentCart) {
        this.mContext = context;
        this.mData = mData;
        this.fragmentCart=fragmentCart;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.user_cart_update,parent,false);
        MyViewHoder vHolder = new MyViewHoder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterProductCart.MyViewHoder holder, int position) {
        Item item = null;
        item = DatabaseSelectHelper.getItem(mData.get(position).getIditem(),mContext);

        holder.tv_TenSanPham.setText(item.getName());


        format = decimalFormat.format(item.getPrice());
        holder.tv_user_price_cart_update.setText("Giá: " +format +" VND");

        if(item.getStatus()==1)
        {
            holder.tv_user_status_cart_update.setText("Tình trạng: Còn hàng");

        }
        else
        {
            holder.tv_user_status_cart_update.setText("Trình trạng: Hết hàng");
            //holder.tv_user_status_cart_update.setTextColor(Integer.parseInt("444336"));
        }
        holder.tvsoluong.setText(String.valueOf(mData.get(position).getMount()));

        Bitmap bitmap= BitmapFactory.decodeByteArray(item.getImage(), 0, item.getImage().length);
        holder.img_product_cart_user.setImageBitmap(bitmap);
        Item finalItem = item;
        holder.user_btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HomeActivity.user.getId() !=-1) {
                    DatabaseUpdateHelper.updatecart(mData.get(position), finalItem, 1, mContext);
                    mData = DatabaseSelectHelper.getbyIDOrder(mData.get(position).getIdorder(), mContext);
                    notifyDataSetChanged();
                    int total = 0;
                    for (DetailOrders detailOrders1 : mData) {
                        total += detailOrders1.getTotalprice();
                    }
                    format = decimalFormat.format(total);
                    fragmentCart.user_total.setText(format + " VND");
                }
                else {
                    for (DetailOrders detailOrders : HomeActivity.detailOrders) {
                        if (detailOrders.getIditem() == mData.get(position).getIditem()) {
                            detailOrders.setMount(detailOrders.getMount() + 1);
                            detailOrders.setTotalprice(detailOrders.getMount() * finalItem.getPrice());
                            HomeActivity.order.setTOTALPRICE(HomeActivity.order.getTOTALPRICE() + finalItem.getPrice());
                        }
                    }
                    mData = HomeActivity.detailOrders;
                    notifyDataSetChanged();
                    int total = 0;
                    for (DetailOrders detailOrders1 : mData) {
                        total += detailOrders1.getTotalprice();
                    }
                    format = decimalFormat.format(total);
                    fragmentCart.user_total.setText(format + " VND");

                }


            }
        });

        holder.user_btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HomeActivity.user.getId()!=-1) {
                    DatabaseUpdateHelper.updatecart(mData.get(position), finalItem, -1, mContext);
                    mData = DatabaseSelectHelper.getbyIDOrder(mData.get(position).getIdorder(), mContext);
                    notifyDataSetChanged();
                    int total = 0;
                    for (DetailOrders detailOrders1 : mData) {
                        total += detailOrders1.getTotalprice();
                    }
                    format = decimalFormat.format(total);
                    fragmentCart.user_total.setText(format + " VND");
                }
                else {
                        for (DetailOrders detailOrders : HomeActivity.detailOrders) {
                            if (detailOrders.getIditem() == mData.get(position).getIditem()) {
                                detailOrders.setMount(detailOrders.getMount() - 1);
                                detailOrders.setTotalprice(detailOrders.getMount() * finalItem.getPrice());
                                HomeActivity.order.setTOTALPRICE(HomeActivity.order.getTOTALPRICE() - finalItem.getPrice());
                            }
                        }
                            mData = HomeActivity.detailOrders;
                            notifyDataSetChanged();
                            int total = 0;
                            for (DetailOrders detailOrders1 : mData) {
                                total += detailOrders1.getTotalprice();
                            }
                            format = decimalFormat.format(total);
                            fragmentCart.user_total.setText(format + " VND");

                }

            }
        });
        holder.uesr_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog dialog =new AlertDialog.Builder(mContext)
                        .setTitle("")
                        . setMessage("Bạn có chắc muôn xóa sản phẩm")
                        .setPositiveButton("Ok",null)
                        .setNegativeButton("Cancel",null)
                        .show();

                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(HomeActivity.user.getId()!=-1) {
                            Toast.makeText(mContext, "Delete sucess", Toast.LENGTH_SHORT).show();
                            DatabaseDriverAndroid db = new DatabaseDriverAndroid(mContext);
                            int IDOrder = mData.get(position).getIdorder();
                            db.deleteDetailOrder(mData.get(position).getIdorder(), mData.get(position).getIditem());
                            mData.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, mData.size());
                            int total = 0;
                            for (DetailOrders detailOrders1 : mData) {
                                total += detailOrders1.getTotalprice();
                            }
                            format = decimalFormat.format(total);
                            fragmentCart.user_total.setText(format + " VND");
                            if (mData.size() == 0) {
                                db.deleteCart(IDOrder);
                            }
                            db.close();
                        }else {
                            for (DetailOrders detailOrders : HomeActivity.detailOrders) {
                                if (detailOrders.getIditem() == mData.get(position).getIditem()) {
                                    HomeActivity.order.setTOTALPRICE(HomeActivity.order.getTOTALPRICE() - detailOrders.getTotalprice());
                                    HomeActivity.detailOrders.remove(detailOrders);
                                    break;
                                }
                            }
                            mData = HomeActivity.detailOrders;

                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, mData.size());
                            int total = 0;
                            for (DetailOrders detailOrders1 : mData) {
                                total += detailOrders1.getTotalprice();
                            }
                            format = decimalFormat.format(total);
                            fragmentCart.user_total.setText(format + " VND");

                        }
                        dialog.dismiss();

                    }
                });



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


        private ImageView img_product_cart_user;
        private TextView tv_TenSanPham,tvsoluong,tv_user_status_cart_update,tv_user_price_cart_update;
        private ImageButton uesr_btn_delete;
        private Button user_btnAdd,user_btnSub;

        public MyViewHoder(View itemview){
            super(itemview);

            tv_TenSanPham=(TextView) itemview.findViewById(R.id.tv_TenSanPham);
            uesr_btn_delete= (ImageButton) itemview.findViewById(R.id.user_btn_delete);
            user_btnAdd = (Button) itemview.findViewById(R.id.user_btnAdd);
            user_btnSub= (Button) itemview.findViewById(R.id.user_btnSub);

            tvsoluong =(TextView )itemview.findViewById(R.id.tvsoluong);
            tv_user_status_cart_update =(TextView )itemview.findViewById(R.id.tv_user_status_cart_update);
            tv_user_price_cart_update =(TextView )itemview.findViewById(R.id.tv_user_price_cart_update);
            img_product_cart_user =(ImageView )itemview.findViewById(R.id.img_product_cart_user);


        }
    }





}
