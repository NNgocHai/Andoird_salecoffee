package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;

public class FragmentAdminProduct extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    List<Item> lstItem;
    static public final int Request_FragmentProduct = 1;

    Context context;
    EditText edtSearch;
    Button btnSearch;
    public FragmentAdminProduct() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.admin_product_fragment,container,false);
        context = container.getContext();

        myrecyclerview =(RecyclerView) v.findViewById(R.id.contact_recycleview);
        FloatingActionButton myFab = (FloatingActionButton)v.findViewById(R.id.add_product_btn);
        myFab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddItemActivity.class);
                startActivityForResult(intent, Request_FragmentProduct);

            }
        });
        edtSearch=(EditText) v.findViewById(R.id.search);
        btnSearch=(Button)v.findViewById(R.id.btnSearch);
        String search = edtSearch.getText().toString();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = edtSearch.getText().toString();
                lstItem = new ArrayList<>();

                lstItem=DatabaseSelectHelper.getItembyname(search,context);
                AdapterProduct recyclerViewAdapter= new AdapterProduct(getContext(),lstItem);
                myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                myrecyclerview.setAdapter(recyclerViewAdapter);



            }
        });

        lstItem= DatabaseSelectHelper.getAllItem(context);
        AdapterProduct recyclerViewAdapter= new AdapterProduct(getContext(),lstItem);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerViewAdapter);

        return v;
    }
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//                lstItem= DatabaseSelectHelper.getAllItem(context);
//                AdapterProduct recyclerViewAdapter= new AdapterProduct(getContext(),lstItem);
//                myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//                myrecyclerview.setAdapter(recyclerViewAdapter);
//
//    }

}
