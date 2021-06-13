package hcmute.edu.vn.mssv18110278.Users.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.R;

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);


        User user = getIntent().getExtras().getParcelable("user");
        int userID= user.getId();
        viewpager= (ViewPager)findViewById(R.id.user_viewpager);

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
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.user_cart_fragment).setChecked(true);
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

}


