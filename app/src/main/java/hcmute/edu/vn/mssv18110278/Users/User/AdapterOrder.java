package hcmute.edu.vn.mssv18110278.Users.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.Users.Admin.UpdateItemActivity;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.database.DatabaseUpdateHelper;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.MyViewHoder> {
    Context mContext;
    List<Order> mData;
    int LAUCH_DETAILBILL=1;
    public AdapterOrder(Context context, List<Order> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.user_history,parent,false);
        MyViewHoder vHolder = new MyViewHoder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterOrder.MyViewHoder holder, int position) {
        holder.date_order_user.setText("Thời gian: "+String.valueOf(mData.get(position).getDATE()));
        holder.address_order_user.setText("Địa điểm: "+mData.get(position).getADDRESS());
        holder.phone_order_user.setText("Số điện thoại: "+mData.get(position).getPHONE());
        holder.Linear_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailOrderActivity.class);
                intent.putExtra("ID", mData.get(position).getID());
                ((Activity) mContext).startActivityForResult(intent,LAUCH_DETAILBILL);

            }
        });
        ArrayList<DetailOrders> detailOrders = (ArrayList<DetailOrders>) DatabaseSelectHelper.getbyIDOrder(mData.get(position).getID(),mContext);
        int total =0;
        for (DetailOrders detailOrders1 : detailOrders) {
            total+=detailOrders1.getTotalprice();
        }

        String pattern = "###,###";
        String format=null;
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        format = decimalFormat.format(total);
        holder.total_product_user.setText(format +" VND");



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

        private TextView date_order_user,address_order_user,phone_order_user,total_product_user;
        private LinearLayout Linear_order;
        public MyViewHoder(View itemview){
            super(itemview);
            Linear_order= itemview.findViewById(R.id.Linear_order);
            date_order_user= (TextView) itemview.findViewById(R.id.date_order_user);
            address_order_user = (TextView) itemview.findViewById(R.id.address_order_user);
            phone_order_user= (TextView) itemview.findViewById(R.id.phone_order_user);
            total_product_user= (TextView) itemview.findViewById(R.id.total_product_user);

        }
    }





}
