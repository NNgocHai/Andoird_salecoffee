package hcmute.edu.vn.mssv18110278.Users.User;

import android.app.AlertDialog;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.database.DatabaseDriverAndroid;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.database.DatabaseUpdateHelper;

public class AdapterProductCartPayment extends RecyclerView.Adapter<AdapterProductCartPayment.MyViewHoder> {
    Context mContext;
    List<DetailOrders> mData;
    public AdapterProductCartPayment(Context context, List<DetailOrders> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.user_item_payment,parent,false);
        MyViewHoder vHolder = new MyViewHoder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterProductCartPayment.MyViewHoder holder, int position) {
        Item item = null;
        item = DatabaseSelectHelper.getItem(mData.get(position).getIditem(),mContext);

        holder.name_product_payment_user.setText(item.getName());

        String pattern = "###,###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String format = decimalFormat.format(item.getPrice());
        holder.price_product_payment_user.setText("Giá: " +format +" VND");


        holder.mount_product_payment_user.setText("Số lượng: "+String.valueOf(mData.get(position).getMount()));
        format = decimalFormat.format(mData.get(position).getTotalprice());
        holder.total_product_payment_user.setText(format+" VND");


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

        private TextView total_product_payment_user,mount_product_payment_user,
                price_product_payment_user,name_product_payment_user;
        public MyViewHoder(View itemview){
            super(itemview);

            name_product_payment_user=(TextView) itemview.findViewById(R.id.name_product_payment_user);
            price_product_payment_user= (TextView) itemview.findViewById(R.id.price_product_payment_user);
            mount_product_payment_user = (TextView) itemview.findViewById(R.id.mount_product_payment_user);
            total_product_payment_user= (TextView) itemview.findViewById(R.id.total_product_payment_user);







        }
    }





}
