package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import hcmute.edu.vn.mssv18110278.Entity.Item;
import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;

public class AdminActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        viewpager= (ViewPager)findViewById(R.id.viewpager);

        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        adapter= new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        List<Item> Items = DatabaseSelectHelper.getAllItem(this);
        adapter.AddFragment(new FragmentAdminProduct(Items),"");
        adapter.AddFragment(new FragmentAdminOrder(),"");
        adapter.AddFragment(new FragmentAdminReport(),"");

        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icon_product);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_order);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_report);


        User user = getIntent().getExtras().getParcelable("user");
        FloatingActionButton myFab = (FloatingActionButton)findViewById(R.id.add_product_btn);
        //login_btn.setOnClickListener(new LoginButtonController(this));
        myFab.setOnClickListener(new ButtonAddItemController(this,user));

    }





}
