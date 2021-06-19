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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.MainActivity;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;

public class FragmentHistory extends Fragment {
    View v;
    Context context;
    private RecyclerView cyclerorder;
    private List<Order> lstOrder;
    private Button btn_LoginPofile;
    private TextView error_login;
    public FragmentHistory() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.user_history_fragment,container,false);
        context = container.getContext();
        if(HomeActivity.user.getId()!=-1) {


            cyclerorder = (RecyclerView) v.findViewById(R.id.recycleview_history);

            lstOrder = DatabaseSelectHelper.getOrderbyIDUser(HomeActivity.user.getId(), context);
            AdapterOrder recyclerViewAdapter1 = new AdapterOrder(getContext(), lstOrder);
            cyclerorder.setLayoutManager(new LinearLayoutManager(getActivity()));
            cyclerorder.setAdapter(recyclerViewAdapter1);


        }
        else
        {
            error_login=v.findViewById(R.id.error_login);
            btn_LoginPofile=v.findViewById(R.id.btn_LoginPofile);
            error_login.setText("Bạn chưa đăng nhập");
            btn_LoginPofile.setVisibility(View.VISIBLE);
            btn_LoginPofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomeActivity.user.setId(-1);
                    startActivity(new Intent(context, MainActivity.class));
                }
            });
        }
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
