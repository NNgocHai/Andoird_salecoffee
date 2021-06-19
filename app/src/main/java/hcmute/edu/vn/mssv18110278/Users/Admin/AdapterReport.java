package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;

public class AdapterReport extends RecyclerView.Adapter<AdapterReport.MyViewHoder> {
    Context mContext;
    List<DetailOrders> mData;
    String pattern = "###,###";
    String format=null;
    DecimalFormat decimalFormat = new DecimalFormat(pattern);

    public AdapterReport(Context context, List<DetailOrders> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.user_cart_history,parent,false);
        MyViewHoder vHolder = new MyViewHoder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterReport.MyViewHoder holder, int position) {

        Item item = null;
        item = DatabaseSelectHelper.getItem(mData.get(position).getIditem(),mContext);

        holder.name_product_user.setText(item.getName());

        format = decimalFormat.format(item.getPrice());
        holder.price_product_user.setText("Giá: " +format +" VND");

        format = decimalFormat.format(mData.get(position).getTotalprice());
        holder.total_product_user.setText( format +" VND");

        holder.mount_product_user.setText(String.valueOf("Số lượng: "+mData.get(position).getMount()));

        Bitmap bitmap= BitmapFactory.decodeByteArray(item.getImage(), 0, item.getImage().length);
        holder.img_product_user.setImageBitmap(bitmap);

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

        private ImageView img_product_user;
        private TextView name_product_user,price_product_user,mount_product_user,total_product_user;
        private ImageButton uesr_btn_delete;
        private Button user_btnAdd,user_btnSub;

        public MyViewHoder(View itemview){
            super(itemview);

            img_product_user=(ImageView) itemview.findViewById(R.id.img_product_user);
            name_product_user= (TextView) itemview.findViewById(R.id.name_product_user);
            price_product_user = (TextView) itemview.findViewById(R.id.price_product_user);
            mount_product_user= (TextView) itemview.findViewById(R.id.mount_product_user);
            total_product_user =(TextView )itemview.findViewById(R.id.total_product_user);

        }
    }





}
