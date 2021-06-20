package hcmute.edu.vn.mssv18110278.Users.Admin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import hcmute.edu.vn.mssv18110278.Entity.User;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    User user;

    public ViewPagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior, User user1) {
        super(fm, behavior);
        this.user=user1;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentAdminProduct();
            case 1:
                return new FragmentAdminOrder();
            case 2:
                return new FragmentAdminReport();
            case 3:
                return new FragmentInfo(user);
            default:
                return new FragmentAdminProduct();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }


}
