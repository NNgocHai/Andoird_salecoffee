package hcmute.edu.vn.mssv18110278.Users.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.edu.vn.mssv18110278.MainActivity;
import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.Validation.Validator;
import hcmute.edu.vn.mssv18110278.database.DatabaseUpdateHelper;

public class FragmentInformation extends Fragment{
    Context context;
    View v;
    Button btnLogout,btn_Change_PassWord,btn_LoginPofile;
    ImageView header_cover_image;
    TextView user_name,error_login;
    public FragmentInformation() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.user_info_fragment,container,false);
        context = container.getContext();
        user_name = v.findViewById(R.id.user_name);
        header_cover_image = (ImageView) v.findViewById(R.id.header_cover_image);
        btnLogout = v.findViewById(R.id.btnLogout);
        btn_Change_PassWord = v.findViewById(R.id.btn_Change_PassWord);

        if(HomeActivity.user.getId()!=-1) {



            user_name.setText(HomeActivity.user.getUsername());
            Bitmap bitmap = BitmapFactory.decodeByteArray(HomeActivity.user.getImage(), 0, HomeActivity.user.getImage().length);
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
                    HomeActivity.user.setId(-1);
                    HomeActivity.detailOrders.clear();
                    HomeActivity.order =null;
                    startActivity(new Intent(context, MainActivity.class));
                }
            });
        }
        else
        {

            btn_Change_PassWord.setVisibility(View.INVISIBLE);
            btnLogout.setVisibility(View.INVISIBLE);
            user_name.setVisibility(View.INVISIBLE);
            header_cover_image.setVisibility(View.INVISIBLE);
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
    public void openDialog() {
        DialogChangePassword Dialog = new DialogChangePassword();
        Dialog.show(getActivity().getSupportFragmentManager(), "dialog change password");
    }

}
