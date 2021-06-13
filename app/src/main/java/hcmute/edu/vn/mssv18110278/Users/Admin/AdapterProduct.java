package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Roles;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.Signup.SignUpActivity;
import hcmute.edu.vn.mssv18110278.database.DatabaseDriverAndroid;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyViewHoder> {
    Context mContext;
    List<Item> mData;
    static public final int Request_AdapterProduct = 2;
    public AdapterProduct(Context context, List<Item> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.item_contact_admin,parent,false);
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
        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdateItemActivity.class);
                intent.putExtra("ID", mData.get(position).getId());
                ((Activity) mContext).startActivityForResult(intent,Request_AdapterProduct);

            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(mContext, "Delete sucess", Toast.LENGTH_SHORT).show();
                        DatabaseDriverAndroid db =new DatabaseDriverAndroid(mContext);
                        db.deleteItem(mData.get(position).getId());
                        mData.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,mData.size());
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
        private TextView tv_name;
        private TextView tv_status;
        private ImageView img;
        private TextView tv_price;
        private ImageButton btn_delete;
        private ImageButton btn_update;

        public MyViewHoder(View itemview){
            super(itemview);

            tv_name=(TextView) itemview.findViewById(R.id.name_product_admin);
            tv_status= (TextView) itemview.findViewById(R.id.status_product_admin);
            img = (ImageView) itemview.findViewById(R.id.img_contact);
            tv_price= (TextView) itemview.findViewById(R.id.price_product_admin);
            btn_delete =(ImageButton )itemview.findViewById(R.id.delete_admin);
            btn_update =(ImageButton )itemview.findViewById(R.id.update_admin);
        }
    }





}
