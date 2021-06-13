package hcmute.edu.vn.mssv18110278.Users.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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

import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.Users.Admin.UpdateItemActivity;
import hcmute.edu.vn.mssv18110278.database.DatabaseDriverAndroid;

public class AdapterCate extends RecyclerView.Adapter<AdapterCate.MyViewHoder> {
    Context mContext;
    List<String> mData;
    public AdapterCate(Context context, List<String> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.user_cate,parent,false);
        MyViewHoder vHolder = new MyViewHoder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterCate.MyViewHoder holder, int position) {
        holder.btn_cate.setText(mData.get(position));
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

        private Button btn_cate;


        public MyViewHoder(View itemview){
            super(itemview);

            btn_cate =(Button ) itemview.findViewById(R.id.user_cate_btn);

        }
    }





}
