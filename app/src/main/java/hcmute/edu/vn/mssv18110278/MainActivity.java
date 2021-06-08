package hcmute.edu.vn.mssv18110278;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import hcmute.edu.vn.mssv18110278.Signup.SignUpActivity;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.Entity.Roles;

public class MainActivity extends AppCompatActivity {
    Button forget_password_btn,login_btn,sign_up_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        forget_password_btn=(Button)findViewById(R.id.forget_password_btn);
        login_btn=(Button)findViewById(R.id.login_btn);
        sign_up_btn=(Button)findViewById(R.id.sign_up_btn);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        forget_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        login_btn.setOnClickListener(new LoginButtonController(this));
        startUp();

    }

    private void startUp() {
        int adminId = DatabaseSelectHelper.getRoleIdFromName(Roles.ADMIN.name(), this);
        int employeeId = DatabaseSelectHelper.getRoleIdFromName(Roles.EMPLOYEE.name(), this);
        if (adminId == -1) {
            Intent intent = new Intent(this, SignUpActivity.class);
            intent.putExtra("sign_up_display", getResources().getString(R.string.admin_sign_up));
            intent.putExtra("role", Roles.ADMIN.name());
            intent.putExtra("access", "init");
            intent.putExtra("backPress", "no");
            startActivity(intent);
        } else if (employeeId == -1) {
            Intent intent = new Intent(this, SignUpActivity.class);
            intent.putExtra("sign_up_display", getResources().getString(R.string.employee_sign_up));
            intent.putExtra("role", Roles.EMPLOYEE.name());
            intent.putExtra("access", "init");
            intent.putExtra("backPress", "no");
            startActivity(intent);
        } else {

        }
    }

}