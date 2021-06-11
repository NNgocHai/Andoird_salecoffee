package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHoder> {
    Context mContext;
    List<Item> mData;

    public RecyclerViewAdapter(Context context, List<Item> mData) {
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
    public void onBindViewHolder(@NonNull @NotNull RecyclerViewAdapter.MyViewHoder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_status.setText(String.valueOf(mData.get(position).getStatus()));
        Bitmap bitmap= BitmapFactory.decodeByteArray(mData.get(position).getImage(), 0, mData.get(position).getImage().length);
        holder.img.setImageBitmap(bitmap);
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

        public MyViewHoder(View itemview){
            super(itemview);

            tv_name=(TextView) itemview.findViewById(R.id.name_product_admin);
            tv_status= (TextView) itemview.findViewById(R.id.status_product_admin);
            img = (ImageView) itemview.findViewById(R.id.img_contact);
        }
    }

}
