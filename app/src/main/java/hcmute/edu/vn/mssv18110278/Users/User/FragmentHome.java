package hcmute.edu.vn.mssv18110278.Users.User;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.Users.Admin.AddItemActivity;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;

public class FragmentHome extends Fragment {
    Context context;
    View v;
    public RecyclerView cyclerproduct;
    private RecyclerView cyclercate;
    private List<Item> lstItem;
    private List<String> lstCate;
    private  ImageView home_gift;
    private EditText edtSearch;
    private Button btnSearch;
    static public int idseach = -1;

    public FragmentHome() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.user_home_fragment,container,false);
        context = container.getContext();

        cyclerproduct =(RecyclerView) v.findViewById(R.id.user_recycle_product);
        cyclercate =(RecyclerView) v.findViewById(R.id.user_recycle_cate);

        lstItem= DatabaseSelectHelper.getAllItem(context);
        AdapterProduct recyclerViewAdapter1= new AdapterProduct(getContext(),lstItem);
        cyclerproduct.setLayoutManager(new LinearLayoutManager(getActivity()));
        cyclerproduct.setAdapter(recyclerViewAdapter1);


        lstCate= DatabaseSelectHelper.getAllCategory(context);
        lstCate.add(0, "ALL");
        AdapterCate recyclerViewAdapter= new AdapterCate(getContext(),lstCate,recyclerViewAdapter1);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        cyclercate.setLayoutManager(layoutManager);
        cyclercate.setAdapter(recyclerViewAdapter);




        edtSearch=(EditText) v.findViewById(R.id.user_search_product);
        btnSearch=(Button)v.findViewById(R.id.user_btnSearch);
        String search = edtSearch.getText().toString();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = edtSearch.getText().toString();
                lstItem = new ArrayList<>();
                if(idseach ==-1) {
                    lstItem = DatabaseSelectHelper.getItembyname(search, context);

                }
                else {
                    lstItem =DatabaseSelectHelper.getIembyCate_Name(idseach,search,context);

                }
                recyclerViewAdapter1.changeItemsbyCate(lstItem);
            }
        });



        return v;
    }
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


}
