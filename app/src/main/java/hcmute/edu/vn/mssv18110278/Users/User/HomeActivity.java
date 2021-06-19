package hcmute.edu.vn.mssv18110278.Users.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.DetailOrders;
import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.Order;
import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.MainActivity;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.Validation.Validator;
import hcmute.edu.vn.mssv18110278.database.DatabaseDriverAndroid;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.database.DatabaseUpdateHelper;

public class HomeActivity extends AppCompatActivity implements DialogChangePassword.DialogListener{

    private ViewPager viewpager;
    public ViewPagerAdapter adapter;
    public static User user;
    public static Order order =null;
    public static List<DetailOrders> detailOrders=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        user = getIntent().getExtras().getParcelable("user");
        if(user.getId()!=-1)
        {
            int IDOrder=-1;
            IDOrder = DatabaseSelectHelper.GetCartUser(user.getId(),getBaseContext());
            if(IDOrder ==-1 && HomeActivity.detailOrders.size()>0)
            {
                DatabaseDriverAndroid dbA = new DatabaseDriverAndroid(getBaseContext());
                IDOrder = dbA.insertNewCart(user.getId(),HomeActivity.order.getTOTALPRICE(),0);
                for(DetailOrders detailOrders: HomeActivity.detailOrders) {
                    dbA.insertNewDetailCart(IDOrder, detailOrders.getIditem(), detailOrders.getMount(), detailOrders.getTotalprice());
                }
                order=null;
                detailOrders.clear();
                dbA.close();
            }

        }
        viewpager= (ViewPager)findViewById(R.id.user_viewpager1);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.user_bottomNavigationView);
        adapter= new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);


        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int a=1;
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.user_home_fragment).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.user_cart_fragment).setChecked(true);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.user_history_fragment).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.user_infor_fragment).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.user_home_fragment:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.user_cart_fragment:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.user_history_fragment:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.user_infor_fragment:
                        viewpager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });




    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            adapter.notifyDataSetChanged();

    }

    @Override
    public void applyTexts(String parse_edit_password, String parse_edit_passwordnew1, String parse_edit_passwordnew2) {
        if(!Validator.validateEmail(parse_edit_password)){
            Toast.makeText(this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();

        }
        else if(!Validator.validatePassword2(parse_edit_passwordnew1,parse_edit_passwordnew2)) {
            Toast.makeText(this, "Confirm mật khẩu không chính xác", Toast.LENGTH_SHORT).show();

        } else
        {
            DatabaseUpdateHelper.updatepassword(parse_edit_passwordnew1,getBaseContext());
            Toast.makeText(this, "Đổi mật khảu thành công", Toast.LENGTH_SHORT).show();
            HomeActivity.user= null;
            HomeActivity.user.setId(-1);
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
            finish();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        HomeActivity.user.setId(-1);
    }
}


