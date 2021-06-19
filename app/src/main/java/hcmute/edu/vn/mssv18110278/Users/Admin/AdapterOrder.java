package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.R;


import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.MyViewHoder> {
    Context mContext;
    List<Order> mData;
    static public final int LAUCH_DETAILBILL = 2;
    public AdapterOrder(Context context, List<Order> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.admin_order,parent,false);
        MyViewHoder vHolder = new MyViewHoder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterOrder.MyViewHoder holder, int position) {
        User user = DatabaseSelectHelper.getUserbyID(mData.get(position).getUSERID(),mContext);
        holder.tv_date.setText("Thời gian: "+mData.get(position).getDATE());
        holder.tv_name_user.setText("Username: "+user.getUsername());

        Bitmap bitmap= BitmapFactory.decodeByteArray(user.getImage(), 0, user.getImage().length);
        holder.tv_image_user.setImageBitmap(bitmap);
        holder.liner_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailOrderActivity.class);
                intent.putExtra("ID", mData.get(position).getID());
                ((Activity) mContext).startActivityForResult(intent,LAUCH_DETAILBILL);

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
        private TextView tv_name_user;
        private TextView tv_date;
        private ImageView tv_image_user;
        private LinearLayout liner_order;


        public MyViewHoder(View itemview){
            super(itemview);

            tv_name_user=(TextView) itemview.findViewById(R.id.tv_name_user);
            tv_date= (TextView) itemview.findViewById(R.id.tv_date);
            tv_image_user = (ImageView) itemview.findViewById(R.id.tv_image_user);
            liner_order = itemview.findViewById(R.id.liner_order);
        }
    }





}
