package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.MainActivity;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.Users.User.DialogChangePassword;
import hcmute.edu.vn.mssv18110278.Users.User.HomeActivity;
import hcmute.edu.vn.mssv18110278.Validation.Validator;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.database.DatabaseUpdateHelper;

public class AdminActivity extends AppCompatActivity implements DialogChangePassword.DialogListener{
    private ViewPager viewpager;
    private ViewPagerAdapter adapter;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        user = getIntent().getExtras().getParcelable("user");

        viewpager= (ViewPager)findViewById(R.id.viewpager);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        adapter= new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,user);


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
                        bottomNavigationView.getMenu().findItem(R.id.product_fragment).setChecked(true);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.order_fragment).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.report_fragment).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.infor_fragment).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        User user = getIntent().getExtras().getParcelable("user");

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.product_fragment:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.order_fragment:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.report_fragment:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.infor_fragment:
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
            Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            HomeActivity.user= null;
            HomeActivity.user.setId(-1);
            startActivity(new Intent(AdminActivity.this, MainActivity.class));
            finish();

        }
    }
}
