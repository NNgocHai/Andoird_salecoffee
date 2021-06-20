package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.MainActivity;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.Users.User.DialogChangePassword;


public class FragmentInfo extends Fragment {
    Context context;
    View v;
    Button btnLogout, btn_Change_PassWord;
    ImageView header_cover_image;
    TextView user_name;
    User user;

    public FragmentInfo(User user1) {
        this.user=user1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.admin_info_fragment, container, false);
        context = container.getContext();
        user_name = v.findViewById(R.id.user_name);
        header_cover_image = (ImageView) v.findViewById(R.id.header_cover_image);
        btnLogout = v.findViewById(R.id.btnLogout);
        btn_Change_PassWord = v.findViewById(R.id.btn_Change_PassWord);


        user_name.setText(user.getUsername());
        Bitmap bitmap = BitmapFactory.decodeByteArray(user.getImage(), 0, user.getImage().length);
        header_cover_image.setImageBitmap(bitmap);


        btn_Change_PassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setId(-1);
                startActivity(new Intent(context, MainActivity.class));
            }
        });





        return v;
    }
    public void openDialog() {
        DialogChangePassword Dialog = new DialogChangePassword();
        Dialog.show(getActivity().getSupportFragmentManager(), "dialog change password");
    }
}
