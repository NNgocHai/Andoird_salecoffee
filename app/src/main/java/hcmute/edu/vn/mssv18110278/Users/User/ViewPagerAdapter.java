package hcmute.edu.vn.mssv18110278.Users.User;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
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
                return new FragmentHome();
            case 1:
                return new FragmentCart();
            case 2:
                return new FragmentHistory();
            case 3:
                return new FragmentInformation();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }


}
